public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        HashMap<Integer, Integer> lengths = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (lengths.containsKey(num))
                continue;

            // get left number's stored length 
            int leftLen = lengths.getOrDefault(num - 1, 0);
                
            // get right number's stored length
            int rightLen = lengths.getOrDefault(num + 1, 0);
                
            // compute actual length (combine left and right length and add 1 for the current number)
            int len = leftLen + rightLen + 1;
                
            // update the hash map's values
            lengths.put(num, len);
               
            // update left end of interval
            lengths.put(num - leftLen, len);
                
            // update right end of interval
            lengths.put(num + rightLen, len);
                
            max = Math.max(len, max);
        }

        return max;
}

public void main(String[] args) {
        int[] nums = {1,2,0,1};
        System.out.println(longestConsecutive(nums));
}
