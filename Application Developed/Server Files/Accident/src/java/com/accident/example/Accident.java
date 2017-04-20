/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accident.example;

/**
 *
 * @author tlc
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/login

@Path("/accident")
public class Accident {

    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/doinsert_records")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String insert_records(@QueryParam("req_num") String req_num, @QueryParam("req_lat") String req_lat,
            @QueryParam("req_lng") String req_lng) throws SQLException {
        String response = "";
        {
            if (Utitlity.isNotNull(req_num)
                    && Utitlity.isNotNull(req_lat)
                    && Utitlity.isNotNull(req_lng)) {
                try {
                    if (DBConnection.insertRecord(req_num, req_lat, req_lng)) {
                        response = Utitlity.constructJSON("insert", true);
                    } else {
                        response = Utitlity.constructJSON("insert", false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Accident.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response = Utitlity.constructJSON("insert", false);
            }

            // decrement the package for specific distributor by 1
            response = Utitlity.constructJSON("login", true);
        }
        return response;
    }

    /**
     * Method to check whether the entered credential is valid
     *
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String uname, String pwd) {
        System.out.println("Inside checkCredentials" + uname + pwd);
        boolean result = false;
        if (Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)) {
            try {

                result = DBConnection.checkLogin(uname, pwd);
                System.out.println("Inside checkCredentials try " + result);
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

    @GET

    // Path: http://localhost/<appln-folder-name>/login/do_dist_login?username=test&password=test
    @Path("/do_dist_login")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String doDist_Login(@QueryParam("UserName") String uname, @QueryParam("Password") String password) throws SQLException {
        String response = "";
        if (checkCredentials(uname, password)) {
            if (DBConnection.SystemActivation(uname, password)) {
                response = Utitlity.constructJSON("login", true);
            } else {
                response = Utitlity.constructJSON("login", false, "Invalid Distributor ID Or Password");
            }

        } else {
            response = Utitlity.constructJSON("login", false, "Invalid Distributor ID Or Password");
        }
        return response;
    }

    /**
     * Method to check whether the entered credential is valid
     *
     * @param uname
     * @return
     */
    private boolean checkCredentials(String uname) {
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if (Utitlity.isNotNull(uname)) {
            try {
                result = DBConnection.checkLogin(uname);
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
