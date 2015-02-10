/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestiatransit;

import java.util.ArrayList;

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
        System.out.println("Finished Reading walking data");
        DB.readBusData(distanceData);
        System.out.println("Finished Reading Bus data");
        DB.readMRTData(distanceData);
        System.out.println("Finished Reading MRT data");
        
        ArrayList<Integer> connectedVertices = getConnectedVertices(distanceData);

        System.out.println("Starting APSP");
        APSP(distanceData, connectedVertices);

        DB.insertDistance(distanceData, connectedVertices);
        //displayResult(distanceData);
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

    private static ArrayList<Integer> getConnectedVertices(int[][] distanceData) {

        int n = GraphManager.VERTEX_COUNT;

        //elimiate isolated vertex
        System.out.println("Elimiating isolated vertices");
        ArrayList<Integer> connectedVertices = new ArrayList<>();
        int numConnected = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distanceData[i][j] != GraphManager.NO_EDGE) {
                    connectedVertices.add(i);
                    numConnected++;
                    break;
                }
            }
        }

        System.out.println("Connected vertices: " + numConnected);
        return connectedVertices;
    }

    //Floyd's Algorithem, All pair shortest path
    private static void APSP(int[][] distanceData, ArrayList<Integer> connectedVertices) {

        for (int k : connectedVertices) {
            //for (int k = 0; k<3 ; k++) {
            System.out.println("In loop k = " + k);
            for (int i : connectedVertices) {
                for (int j : connectedVertices) {
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
