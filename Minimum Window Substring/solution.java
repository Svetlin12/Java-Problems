import java.lang.*;
import java.util.*;

class Solution {
    public String minWindow(String s, String t) {

        // if either string does not exist, then there is nothing to solve
        if (s.length() == 0 || t.length() == 0)
            return "";

        // left and right will be used for pointers to the string, ans is the minimum window length up until a point of time
        // leftAns and rightAns will hold the start and end of the substring that contains the minimum window
        int left = 0, right = 0, ans = Integer.MAX_VALUE, leftAns = 0, rightAns = 0;

        // tChars holds all the unique characters in t and their count in t
        // matched will hold all the unique characters in the current window and their count
        Map<Character, Integer> tChars = new HashMap<>(), matched = new HashMap<>();

        // go through t and store the results in tChars
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            int cnt = tChars.getOrDefault(ch, 0);
            tChars.put(ch, cnt + 1);
        }

        // matchedCnt will keep track of how many unique characters have been matched up until a point of time
        // for instance: if t is AAAB, then we will have to match 2 unique characters (A and B)
        // this means that when matched['A'] == 3, matchedCnt will be increased because A's count corresponds to the target count
        int uniqeCharsCnt = tChars.size(), matchedCnt = 0;

        while (right < s.length()) {
            char current = s.charAt(right);
            int cnt = matched.getOrDefault(current, 0);
            matched.put(current, cnt + 1);

            if (matched.get(current).equals(tChars.get(current)))
                matchedCnt++;

            while (matchedCnt == uniqeCharsCnt) {
                char leftMost = s.charAt(left);

                matched.put(leftMost, matched.get(leftMost) - 1);
                if (tChars.containsKey(leftMost) && matched.get(leftMost) < tChars.get(leftMost))
                    matchedCnt--;

                if (ans > right - left + 1) {
                    ans = right - left + 1;
                    leftAns = left;
                    rightAns = right;
                }

                left++;
            }
            right++;
        }
        
        if (ans == Integer.MAX_VALUE)
            return "";

        return s.substring(leftAns, rightAns + 1);
    }
}
