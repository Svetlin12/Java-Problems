import java.lang.*;
import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<>();

        // the critical points are both ends of each rectangle (each critical point stores its x location and height)
        List<int[]> criticalPts = new LinkedList<>();

        List<List<Integer>> ans = new LinkedList<>();

        // in order to differentiate the start of the rectangles from the ends, the starting critical points shall have
        // negative height value
        for (int[] building : buildings) {
            criticalPts.add(new int[] {building[0], -building[2]});
            criticalPts.add(new int[] {building[1], building[2]});
        }

        // sort the critical points so that we can go through them from left to right
        criticalPts.sort((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        int prevBuildingHeight = 0;

        // this priority queue will store the max height up until a point of time
        // basically it will tell us what the current max height is
        PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());

        // the heights.peek() down below might produce null pointer exception so we fill heights with a default value
        heights.add(0);
        
        for (int[] currPt : criticalPts) {
            if (currPt[1] < 0) // if currPt is a starting point
                heights.add(-currPt[1]);
            else // it is an ending point, so remove the height that was pushed by the starting point of the same rectangle
                heights.remove(currPt[1]);

            // get the max height
            int currHeight = heights.peek();
            
            // if the max height is not the same as the previous max height, add the critical point to the answer
            // if the max height is the same as the previous max height, this just means that we are on a point that
            // is overshadowed by a taller building that has not met its end yet
            if (!heights.peek().equals(prevBuildingHeight)) {
                List<Integer> l = new LinkedList<>();
                l.add(currPt[0]);
                l.add(currHeight);
                ans.add(l);
                prevBuildingHeight = currHeight;
            }
        }

        return ans;
    }
}
