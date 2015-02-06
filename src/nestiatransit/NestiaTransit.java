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
        initData(distanceData);

        DB.readWalkingData(distanceData);
        DB.readBusData(distanceData);
        DB.readMRTData(distanceData);

        APSP(distanceData);

        displayResult(distanceData);
    }

    private static void initData(int[][] distanceData) {
        for (int[] distanceData1 : distanceData) {
            for (int j = 0; j < distanceData1.length; j++) {
                distanceData1[j] = GraphManager.NO_EDGE;
            }
        }
    }

    private static void displayResult(int[][] distanceData) {
        for (int i = 0; i < distanceData.length; i++) {
            for (int j = 0; j < distanceData[i].length; j++) {

                if (distanceData[i][j] != GraphManager.NO_EDGE) {
                    System.out.println("Grid " + i + " - Grid " + j + ": " + distanceData[i][j]);
                }

            }
        }
    }

    //Floyd's Algorithem, All pair shortest path
    private static void APSP(int[][] distanceData) {
        int n = GraphManager.VERTEX_COUNT;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distanceData[i][k] != GraphManager.NO_EDGE && distanceData[k][j] != GraphManager.NO_EDGE) {
                        int sum = (distanceData[i][k] + distanceData[k][j]);
                        if (distanceData[i][j] == GraphManager.NO_EDGE || sum < distanceData[i][j]) {
                            distanceData[i][j] = sum;
                        }
                    }
                }
            }
        }
    }

}
