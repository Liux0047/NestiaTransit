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
public class BusStopDistance {
    
    private String busServiceNum;
    private int busStopId;
    private double distance;
    private int direction;
    private int gridIndex;
    
    public BusStopDistance(String busServiceNum, int busStopId, double distance, int direction, int gridIndex) {
        this.busServiceNum = busServiceNum;
        this.busStopId = busStopId;
        this.distance = distance;
        this.direction = direction;
        this.gridIndex = gridIndex;
    }

    public String getBusServiceNum() {
        return busServiceNum;
    }

    public void setBusServiceNum(String busServiceNum) {
        this.busServiceNum = busServiceNum;
    }

    public int getBusStopId() {
        return busStopId;
    }

    public void setBusStopId(int busStopId) {
        this.busStopId = busStopId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getGridIndex() {
        return gridIndex;
    }

    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }
    
    
    
    
}
