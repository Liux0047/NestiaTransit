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
        
        //update this single distance source-desitination
        int durationSingle = mrtDistance.getDuration() + 2 * GraphManager.GRID_TO_MRT_STOP;
        if (distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] == GraphManager.NO_EDGE
                || distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] > durationSingle) {
            distanceData[mrtDistance.getSource()][mrtDistance.getDestination()] = durationSingle;
            distanceData[mrtDistance.getDestination()][mrtDistance.getSource()] = durationSingle;
        }

        //compute for all previous station
        if (this.prevLine.equals(line)) {    //check for the same line
            int duration = mrtDistance.getDuration();
            int durationWithDelay;
            

            for (int i = prevDistances.size() - 1; i >= 0; i--) {
                MRTDistance prevDistance = prevDistances.get(i);
                duration += prevDistance.getDuration();
                durationWithDelay = duration + 2 * GraphManager.GRID_TO_MRT_STOP;

                if (distanceData[prevDistance.getSource()][mrtDistance.getDestination()] == GraphManager.NO_EDGE
                        || distanceData[prevDistance.getSource()][mrtDistance.getDestination()] > durationWithDelay) {
                    distanceData[prevDistance.getSource()][mrtDistance.getDestination()] = durationWithDelay;
                    distanceData[mrtDistance.getDestination()][prevDistance.getSource()] = durationWithDelay;
                }
            }

        } else {    //if new service number
            this.prevDistances.clear();
        }

        this.prevDistances.add(mrtDistance);
        this.prevLine = line;
    }
}
