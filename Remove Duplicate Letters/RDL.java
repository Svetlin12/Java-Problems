import java.lang.*;
import java.util.*;

class Solution {
    public static String removeDuplicateLetters(String s) {
        int[] charCnt = new int[26];
        char[] arr = s.toCharArray();
        for (char c : arr)
            charCnt[c - 'a']++;

        Stack<Character> st = new Stack<>();
        Map<Character, Boolean> m = new HashMap<>();
        for (char c : arr) {
            charCnt[c - 'a']--;

            if (m.containsKey(c)) continue;
            else m.put(c, true);

            if (st.empty())
                st.push(c);
            else {
                while (!st.empty() && st.peek() > c && charCnt[st.peek() - 'a'] != 0) {
                    m.remove(st.peek());
                    st.pop();
                }
                st.push(c);
            }
        }

        Character[] resChars = new Character[st.size()];
        st.copyInto(resChars);

        String res = "";
        for (char c : resChars) {
            res = res.concat(String.valueOf(c));
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
}
