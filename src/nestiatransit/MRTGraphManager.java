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
public class MRTGraphManager extends GraphManager{
    
    public void buildMRTGraph (MRTDistance mrtDistance, int[][] distanceData) {
        int source = mrtDistance.getSource();
        int destination = mrtDistance.getDestination();
        int duration = mrtDistance.getDuration();
        
        if (distanceData[source][destination] == GraphManager.NO_EDGE || distanceData[source][destination] > duration) {
            distanceData[source][destination] = duration;
        }
    }
}
