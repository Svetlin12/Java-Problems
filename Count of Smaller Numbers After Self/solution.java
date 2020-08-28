import java.lang.*;
import java.util.*;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();

        for (int num : nums)
            sorted.add(num);

        Collections.sort(sorted);

        for (int num : nums) {
            int index = sorted.indexOf(num);
            ans.add(index);
            sorted.remove(index);
        }

        return ans;
    }
}
