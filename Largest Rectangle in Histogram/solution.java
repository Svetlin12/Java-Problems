import javafx.util.Pair;
import java.lang.*;
import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        // the stack will hold start indices and heights of the rectangles as we go through the array
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        int cnt = 0, maxArea = 0, currArea = 0;
        
        for (; cnt < heights.length; cnt++) {
            if (s.isEmpty()) {
                Pair<Integer, Integer> p = new Pair<>(cnt, heights[cnt]);
                s.add(p);
            }
            else {
                // if the current height is smaller than the top of the stack, then get each index we pop so that we can
                // extend the current rectangle in the left direction
                int lastIndex = cnt;
                
                while (!s.isEmpty() && s.peek().getValue() > heights[cnt]) {
                    // get starting index of what we are about to pop
                    lastIndex = s.peek().getKey();
                    
                    // compute the area of the rectangle that we got on top of the stack
                    currArea = (cnt - lastIndex) * s.peek().getValue();
                    if (maxArea < currArea)
                        maxArea = currArea;
                    s.pop();
                }

                Pair<Integer, Integer> p = new Pair<>(lastIndex, heights[cnt]);
                s.add(p);
            }
        }

        // after the traversal there might be rectangles left in the stack which extend to the end of the array
        while (!s.empty()) {
            currArea = (cnt - s.peek().getKey()) * s.peek().getValue();
            if (maxArea < currArea)
                maxArea = currArea;
            s.pop();
        }

        return maxArea;
    }
}
