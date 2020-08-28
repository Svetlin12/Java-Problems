import java.lang.*;
import java.util.*;

class SmartDeque {
    private Deque<Integer> max;
    private Queue<Integer> nums;

    SmartDeque(int[] initial) {
        max = new LinkedList<>();
        nums = new LinkedList<>();
        for (int num : initial) {
            nums.add(num);
            while (max.size() > 0 && max.getLast() < num)
                max.removeLast();
            max.add(num);
        }
    }

    public void add(int num) {
        nums.add(num);
        int removed = nums.remove();

        if (max.getFirst() == removed)
            max.removeFirst();

        while (max.size() > 0 && max.getLast() < num)
            max.removeLast();

        max.add(num);
    }

    public int getMax() {
        if (max.size() == 0)
            return -1;
        else return max.getFirst();
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k > nums.length)
            return null;

        int[] ans = new int[Math.max(1, 1 + nums.length - k)];
        int cnt = 0;
        int[] firstKNums = new int[k];

        if (k >= 0) System.arraycopy(nums, 0, firstKNums, 0, k);

        SmartDeque s = new SmartDeque(firstKNums);
        ans[cnt++] = s.getMax();

        for (int i = k; i < nums.length; i++) {
            s.add(nums[i]);
            ans[cnt++] = s.getMax();
        }

        return ans;
    }
}
