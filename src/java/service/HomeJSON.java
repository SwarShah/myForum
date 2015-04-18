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
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0647456
 */
@Path("home")
public class HomeJSON {
    @GET
    @Produces("application/json")
    public String get(){
        return getJSON();
    }
    public static String getJSON(){
        JsonArray json = null;
        try {
            JsonArrayBuilder array = Json.createArrayBuilder();
            Connection cn = getConnection();
            String query = "select count(t.t_id) AS \"topics\", c.name, c.c_id from category c LEFT JOIN thread t ON  (c.c_id = t.c_id) GROUP BY(c.c_id)";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            String jsonString = "";
            while (rs.next()) {
                array.add(Json.createObjectBuilder()
                        .add("c_id", rs.getInt("c_id"))
                        .add("category", rs.getString("name"))
                        .add("topics", rs.getString("topics"))
                        .build());
                
            }
            json = array.build();
            cn.close();
            return json.toString();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadREST.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
