import java.lang.*;
import java.util.*;

class Solution {
    private List<List<Integer>> ans = new LinkedList<>();

    private void helper(int[] nums, int left, int right) {
        if (left == right) {
            List<Integer> l = new LinkedList<>();
            for (int num : nums)
                l.add(num);
            ans.add(l);
        }
        else {
            for (int i = left; i <= right; i++) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                helper(nums, left + 1, right);
                temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        helper(nums, 0, nums.length - 1);
        return ans;
    }
}
