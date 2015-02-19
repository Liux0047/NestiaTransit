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
public abstract class GraphManager {

    public static final int VERTEX_COUNT = 15713;
    public static final int GRID_TO_BUS_STOP = 100;
    public static final int BUS_STOP_DELAY = 30;
    public static final int GRID_TO_MRT_STOP = 100;
    public static final int MRT_STOP_DELAY = 30;

    public static final double BUS_SPEED_KMPS = 0.006;
    public static final double MRT_SPEED_KMPS = 0.02;
    
    public static final int NUM_GIRD_PER_ROW = 168;
    
    public static final int NO_EDGE = -1;
    
    public static final int MAX_TRANSIT_TIME = 3600;

    
}
