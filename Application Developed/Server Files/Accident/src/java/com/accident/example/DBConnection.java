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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    /**
     * Method to create DB Connection
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
            throw e;
        } finally {
            return con;
        }
    }

    /**
     * Method to check whether uname and pwd combination are correct
     *
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uname, String pwd) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM distributor WHERE User_Name = '" + uname
                    + "' AND Password=" + "'" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }

    /**
     * Method to check whether uname and pwd combination are correct
     *
     * @param uname
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uname) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM empmanagement WHERE Emp_Id = '" + uname
                    + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }

    /**
     * Method to insert uname and pwd in DB
     *
     * @param name
     * @param uname
     * @param pwd
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into user(name, username, password) values('" + name + "'," + "'"
                    + uname + "','" + pwd + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }

    /**
     * Method to get user details
     *
     * @param uid/mobile no
     * @return
     * @throws SQLException
     * @throws Exception
     */
    static List<String> getDetails(String uId) throws SQLException, Exception {
        List<String> details = new ArrayList<String>();
        Connection dbConn = null;
        System.out.println("in DB Connection of getDetails and value is" + uId);
        try {
            System.out.println("in 1st try DB Connection of getDetails and value is" + uId);
            try {
                System.out.println("in 2nd try DB Connection of getDetails and value is" + uId);
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();

            String query = "";
            if (uId.length() < 10) {
                query = "SELECT * FROM customer WHERE uId = '" + uId + "'";
            } else if (uId.length() >= 10) {
                query = "SELECT * FROM customer WHERE mobile_No = " + uId;
            }
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            List ls = new ArrayList();
            while (rs.next()) {
                List ls1 = new ArrayList();
                ls1.add(rs.getString("name"));
                ls.add(ls1);
                details.add(rs.getString("uId"));
                details.add(rs.getString("name"));
                details.add(rs.getString("outstanding"));
                details.add(rs.getString("prev1"));
                details.add(rs.getString("prev2"));
            }

            System.out.println(details.get(0) + "is the name and length is" + String.valueOf(details.size()));

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return details;
    }

    static List<String> payBill(String uId, String amount) throws SQLException, Exception {
        List<String> details = new ArrayList<String>();
        Connection dbConn = null;
        System.out.println("in  of paybill and amount is" + amount);
        try {
            System.out.println("in 1st  of paybill and value is" + amount);
            try {
                System.out.println("in 2nd paybill and value is" + amount);
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query;
            //query="update customer set outstanding=outstanding-100 where uId='1'";
            query = "UPDATE customer SET prev2=prev1,prev1=" + amount + ",outstanding=outstanding-" + amount + " where uId='" + uId + "'";
            stmt.executeUpdate(query);
            query = "SELECT * FROM customer WHERE uId ='" + uId + "'";

            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            List ls = new ArrayList();
            while (rs.next()) {
                List ls1 = new ArrayList();
                ls1.add(rs.getString("name"));
                ls.add(ls1);
                details.add(rs.getString("name"));
                details.add(rs.getString("outstanding"));
                details.add(rs.getString("prev1"));
                details.add(rs.getString("prev2"));
                details.add(rs.getString("mobile_No"));

            }

            System.out.println(details.get(0) + "is the name and length is" + String.valueOf(details.size()));

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return details;
    }

    static boolean SystemActivation(String userName, String pwd) throws SQLException {
        System.out.println("in insertion");
        boolean result = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "Update distributor SET packages=packages-1 Where User_Name='" + userName
                    + "' AND Packages>0";
            System.out.println(query);
            int records = stmt.executeUpdate(query);

            if (records > 0) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {

            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
                return result;
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return result;
    }

    static void InsertKey(String key, String uName) throws SQLException {
        System.out.println("in insertion");
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO cust_proc_key (Proc_Key,User_Name) "
                    + "VALUES('" + key + "','" + uName + "') ";
            stmt.executeUpdate(query);

        } catch (SQLException sqle) {

        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

    }

    /**
     * Method to check whether key and user name combination are correct
     *
     * @param uname
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean checkProcKey(String key, String uName) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM cust_proc_key WHERE User_Name = '" + uName
                    + "' AND Proc_Key=" + "'" + key + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }

    /**
     *
     * @param req_num
     * @param req_lat
     * @param req_lng
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertRecord(String req_num, String req_lat, String req_lng) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into backups(backups_request_num, backups_lat, backups_lng) "
                    + "values('" + req_num + "'," + "'"
                    + req_lat + "','" + req_lng + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }
}
