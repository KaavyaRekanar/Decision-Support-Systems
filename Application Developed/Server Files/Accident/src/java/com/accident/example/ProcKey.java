/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accident.example;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tlc
 */
@Path("/ProcKey")

public class ProcKey {

    @GET
    @Path("/insert_key")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)

    public String doInsertKey(@QueryParam("Key") String key,@QueryParam("User_Name") String uName) throws SQLException {
        DBConnection.InsertKey(key,uName);
        String response = "";

        return response;
    }

    @GET
    @Path("/auth_key")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)

    public String doAuthKey(@QueryParam("Key") String key,@QueryParam("User_Name") String uName) {
        String response = "";
        
        if (checkCredentials(key, uName)){
            // generate JSON String 
            response=Utitlity.constructJSON("ProcIdCheck", true);
        }
        else response=Utitlity.constructJSON("ProcIdCheck", false);
            
        return response;
    }
    
    
    private boolean checkCredentials(String key, String uName) {
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if (Utitlity.isNotNull(uName) && Utitlity.isNotNull(key)) {
            try {
                result = DBConnection.checkProcKey(key, uName);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        } else {
            //System.out.println("Inside checkCredentials else");
            result = false;
        }

        return result;
    }

}
