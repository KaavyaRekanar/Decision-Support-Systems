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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utitlity {

    /**
     * Null check Method
     *
     * @param txt
     * @return
     */
    public static boolean isNotNull(String txt) {
        // System.out.println("Inside isNotNull");
        return txt != null && txt.trim().length() >= 0 ? true : false;
    }

    /**
     * Method to construct JSON
     *
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }

    /**
     * Method to construct JSON with Error Msg
     *
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
    public static String constructJSON(String tag, boolean status, String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }

    /**
     * Method to construct JSON
     *
     * @param tag
     * @param outstanding amount
     * @param prev1 amount
     * @param prev2 amount
     * @return
     */
    public static String constructJSON(String tag, String id, String name, String outStanding, String prev1, String prev2) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("id", id);
            obj.put("name", name);
            obj.put("outstanding", outStanding);
            obj.put("prev1", prev1);
            obj.put("prev2", prev2);

            //obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }

    static String constructJSON(String billpay,String uId, String name, String outStanding, String prev1, String prev2, String mobile) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("billpay",billpay);
            obj.put("uId", uId);
            obj.put("name", name);
            obj.put("outstanding", outStanding);
            obj.put("prev1", prev1);
            obj.put("prev2", prev2);
            obj.put("mobile", mobile);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }

}
