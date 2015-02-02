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

    public void readWalkingData(int[][] walkingData) {

    }

    public void readBusData(int[][] busData) {

        Connection conn = null;
        Statement statement = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a querys
            statement = conn.createStatement();
            String sql;
            sql = "SELECT bus_stop_distance.*, area.grid_index FROM bus_stop_distance "
                    + "INNER JOIN bus_stop ON bus_stop_distance.bus_stop_id = bus_stop.bus_stop_id "
                    + "INNER JOIN area ON bus_stop.area_id = area.area_id";

            //STEP 5: Extract data from result set
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                this.buildBusGraph(resultSet, busData);
                //STEP 6: Clean-up environment
            }
            statement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException se) {
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
            }//end finally try
        }//end try
    }

    public void readMRTData(int[][] MRTData) {

    }

    public void executeSQL() {

        Connection conn = null;
        Statement statement = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a querys
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
        } catch (SQLException | ClassNotFoundException se) {
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
            }//end finally try
        }//end try
    }

    private void buildBusGraph(ResultSet resultSet, int[][] busData) throws SQLException {
        
        BusGraphManager graphMgr = new BusGraphManager();
        
        while (resultSet.next()) {
            String serviceNum = resultSet.getString("bus_service_no");
            //Retrieve by column name
            BusStopDistance busStopDistance = new BusStopDistance(
                    serviceNum,
                    resultSet.getInt("bus_stop_id"),
                    resultSet.getDouble("distance_km"),
                    resultSet.getInt("direction"),
                    resultSet.getInt("grid_index"));

            graphMgr.buildBusGraph(busStopDistance, busData);

        }

    }

}
