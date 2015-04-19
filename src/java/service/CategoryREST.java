/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static credentials.dbConnection.getConnection;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0647456
 */
@Path("category")
public class CategoryREST {

    @GET
    @Produces("application/json")
    public Response get() {
        return Response.ok(getResults("SELECT * FROM CATEGORY")).build();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getSingle(@PathParam("id") int id) {
        return Response.ok(getResults("SELECT * FROM CATEGORY WHERE C_ID = ?", String.valueOf(id))).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(String str) {
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String name = json.getString("name");
        if(doUpdate("INSERT INTO CATEGORY (NAME) VALUES (?)", name) == 0){
            return Response.status(500).build();
        } else {
            return Response.ok(getResults("SELECT * FROM CATEGORY")).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response put(String str, @PathParam("id") int id){
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String name = json.getString("name");
        if(doUpdate("UPDATE CATEGORY SET NAME = ? WHERE C_ID = ?", name, String.valueOf(id)) == 0){
            return Response.status(500).build();
        }
        else{
            return Response.ok(getResults("SELECT * FROM CATEGORY")).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        if (doUpdate("DELETE FROM CATEGORY WHERE C_ID = ?", String.valueOf(id)) == 0){
             return Response.status(500).build();
        } else {
            return Response.ok(getResults("SELECT * FROM CATEGORY")).build();
        }             
    }
    

    public static JsonArray getResults(String sql, String... params) {
        JsonArray json = null;
        try {
            JsonArrayBuilder array = Json.createArrayBuilder();
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                array.add(Json.createObjectBuilder()
                        .add("c_id", rs.getInt("c_id"))
                        .add("name", rs.getString("name"))
                        .build());
            }
            conn.close();
            json = array.build();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static int doUpdate(String sql, String... params) {
        int result = -1;
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
