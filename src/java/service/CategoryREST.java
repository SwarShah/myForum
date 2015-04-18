/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author c0647456
 */
@Path("category")
public class CategoryREST {
    @GET
    @Produces("application/json")
    public String get(){
        return "{\"name\" : \"Hello World\"}";
    }
}
