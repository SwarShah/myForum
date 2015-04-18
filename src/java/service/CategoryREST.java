/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import static credentials.dbConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public Response get(){
        return Response.ok(getResults("SELECT * FROM CATEGORY")).build();
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
                System.out.println(rs.getInt("c_id"));
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
