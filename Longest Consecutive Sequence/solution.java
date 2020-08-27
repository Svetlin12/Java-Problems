public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        HashMap<Integer, Integer> lengths = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (lengths.containsKey(num))
                continue;

            int leftLen = lengths.getOrDefault(num - 1, 0);
            int rightLen = lengths.getOrDefault(num + 1, 0);
            int len = leftLen + rightLen + 1;
            lengths.put(num, len);
            lengths.put(num - leftLen, len);
            lengths.put(num + rightLen, len);
            max = Math.max(len, max);
        }

        return max;
}

public void main(String[] args) {
        int[] nums = {1,2,0,1};
        System.out.println(longestConsecutive(nums));
}
