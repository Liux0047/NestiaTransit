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
public class MRTGraphManager extends GraphManager {

    private ArrayList<MRTDistance> prevDistances;
    private String prevLine;

    public MRTGraphManager() {
        this.prevDistances = new ArrayList<>();
        prevLine = "00";
    }

    public void buildMRTGraph(MRTDistance mrtDistance, int[][] distanceData) {
        String line = mrtDistance.getLine();

        if (this.prevLine.equals(line)) {    //check for the same line
            int duration = 0;
            int durationWithDelay;

            for (int i = prevDistances.size() - 1; i >= 0; i--) {
                MRTDistance prevDistance = prevDistances.get(i);
                duration += prevDistance.getDuration();
                durationWithDelay = duration + 2 * GraphManager.MRT_STOP_DELAY;

                if (distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] == GraphManager.NO_EDGE
                        || distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] > durationWithDelay) {
                    distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] = durationWithDelay;
                }
            }

        } else {    //if new service number
            this.prevDistances.clear();
        }

        this.prevDistances.add(mrtDistance);
        this.prevLine = line;
    }
}
