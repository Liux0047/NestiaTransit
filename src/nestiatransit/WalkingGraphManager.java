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
public class WalkingGraphManager extends GraphManager{
    
        
    public void buildWalkingGraph (WalkingDistance walkingDistance, int[][] distanceData) {
        
        int source = walkingDistance.getSource();
        int destination = walkingDistance.getDestination();
        
        //check if source and destination falls in a 3 by 3 grid block
        int diff = Math.abs(walkingDistance.getSource() - walkingDistance.getDistance());
        if ( diff <= 1 || diff == GraphManager.NUM_GIRD_PER_ROW 
                || diff == GraphManager.NUM_GIRD_PER_ROW -1 
                || diff == GraphManager.NUM_GIRD_PER_ROW + 1){
            
            int duration = walkingDistance.getDuration();
            
            if (distanceData[source][destination] == GraphManager.NO_EDGE || distanceData[source][destination] > duration) {
                distanceData[source][destination] = duration;
            }
        }
        
    }
    
}
