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
public class BusGraphManager extends GraphManager {

    private ArrayList<BusStopDistance> prevBusStopDistances;
    private String prevServiceNum;

    public BusGraphManager() {
        this.prevBusStopDistances = new ArrayList<>();
        prevServiceNum = "";
    }

    public void buildBusGraph(BusStopDistance busStopDistance, int[][] busData) {

        if (this.prevServiceNum.equals(busStopDistance.getBusServiceNum())) {

            for (BusStopDistance prevStopDistance : this.prevBusStopDistances) {
                double distance = (busStopDistance.getDistance() - prevStopDistance.getDistance());
                int stopDelay = (prevBusStopDistances.size() - prevBusStopDistances.indexOf(prevStopDistance) - 1) * GraphManager.BUS_STOP_DELAY;
                int duration = GraphManager.GRID_TO_BUS_STOP
                        + +stopDelay
                        + (int) (distance / GraphManager.BUS_SPEED_KMPS)
                        + GraphManager.GRID_TO_BUS_STOP;

                if (busData[prevStopDistance.getGridIndex()][busStopDistance.getGridIndex()] == 0
                        || busData[prevStopDistance.getGridIndex()][busStopDistance.getGridIndex()] > duration) {
                    busData[prevStopDistance.getGridIndex()][busStopDistance.getGridIndex()] = duration;
                }

            }

        } else {    //if new service number
            this.prevBusStopDistances.clear();
        }

        this.prevBusStopDistances.add(busStopDistance);
        this.prevServiceNum = busStopDistance.getBusServiceNum();
    }
}
