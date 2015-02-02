/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestiatransit;

/**
 *
 * @author Allen
 */
public class NestiaTransit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBManager DB = new DBManager();

        int[][] distanceData = new int[GraphManager.VERTEX_COUNT][GraphManager.VERTEX_COUNT];

        DB.readBusData(distanceData);
        
        displayResult (distanceData);
    }

    private static void displayResult(int[][] distanceData) {
        for (int i = 0; i < distanceData.length; i++) {
            for (int j = 0; j < distanceData[i].length; j++) {
                
                if (distanceData[i][j] != 0 ){
                    System.out.println("Grid " + i + " - Grid " + j + ": " + distanceData[i][j]);
                }

            }
        }
    }

}
