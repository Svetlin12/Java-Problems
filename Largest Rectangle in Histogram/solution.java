import javafx.util.Pair;
import java.lang.*;
import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        int cnt = 0, maxArea = 0, currArea = 0;
        for (; cnt < heights.length; cnt++) {
            if (s.isEmpty()) {
                Pair<Integer, Integer> p = new Pair<>(cnt, heights[cnt]);
                s.add(p);
            }
            else {
                int lastIndex = cnt;
                while (!s.isEmpty() && s.peek().getValue() > heights[cnt]) {
                    lastIndex = s.peek().getKey();
                    currArea = (cnt - lastIndex) * s.peek().getValue();
                    if (maxArea < currArea)
                        maxArea = currArea;
                    s.pop();
                }

                Pair<Integer, Integer> p = new Pair<>(lastIndex, heights[cnt]);
                s.add(p);
            }
        }

        while (!s.empty()) {
            currArea = (cnt - s.peek().getKey()) * s.peek().getValue();
            if (maxArea < currArea)
                maxArea = currArea;
            s.pop();
        }

        return maxArea;
    }
}
