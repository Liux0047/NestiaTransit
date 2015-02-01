/*
 * To change this license header, choose License HeaderesultSet in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestiatransit;

import java.sql.*;

/**
 *
 * @author Allen
 */
public class DBManager {

    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/crawler";

    //  Database credentials
    public static final String USER = "root";
    public static final String PASS = "password";

    public DBManager() {

    }

    public void connect() {

        Connection conn = null;
        Statement statement = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM bus_stop_distance";
            //STEP 5: Extract data from result set
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (resultSet.next()) {
                    //Retrieve by column name
                    String serviceNum = resultSet.getString("bus_service_no");
                    int stopId = resultSet.getInt("bus_stop_id");
                    double distance = resultSet.getDouble("distance_km");
                    int direction = resultSet.getInt("direction");
                    
                    //Display values
                    System.out.print("serviceNum: " + serviceNum);
                    System.out.print(", stopId: " + stopId);
                    System.out.print(", distance: " + distance);
                    System.out.println(", direction: " + direction);
                }
                //STEP 6: Clean-up environment
            }
            statement.close();
            conn.close();
        } catch (SQLException se) {
            //Handle erroresultSet for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle erroresultSet for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

}
