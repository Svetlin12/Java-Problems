class Solution {
    public static int longestValidParentheses(String s) {
        char[] arr = s.toCharArray();
        int[] substrLen = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ')' && i - 1 >= 0 && arr[i - 1] == '(') {
                if (i - 2 < 0)
                    substrLen[i] = 2;
                else
                    substrLen[i] = substrLen[i - 2] + 2;
            }
            else if (arr[i] == ')' && i - 1 >= 0 && arr[i - 1] == ')') {
                if (i - substrLen[i - 1] - 1 >= 0 && arr[i - substrLen[i - 1] - 1] == '(') {
                    int prevInd = i - substrLen[i - 1] - 2;
                    if (prevInd > 0)
                        substrLen[i] = substrLen[i - 1] + substrLen[prevInd] + 2;
                    else
                        substrLen[i] = substrLen[i - 1] + 2;
                }
            }
        }

        int max = 0;
        for (int curr : substrLen) {
            if (curr > max)
                max = curr;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("())"));
    }
}
