import java.lang.*;

class Solution {
    private int[] xPositions = { 1, -1, 0, 0 };
    private int[] yPositions = { 0, 0, 1, -1 };
    private int[][] lengths;

    private boolean isSafe(int row, int col, int maxRow, int maxCol) {
        if (row < 0 || row >= maxRow || col < 0 || col >= maxCol)
            return false;
        return true;
    }

    private int dfs(int[][] matrix, int currRow, int currCol) {
        if(lengths[currRow][currCol] != 0)
            return lengths[currRow][currCol];

        int currLen = 1;
        for (int i = 0; i < 4; i++) {
            if (isSafe(currRow + xPositions[i], currCol + yPositions[i], matrix.length, matrix[0].length)
                    && matrix[currRow][currCol] < matrix[currRow + xPositions[i]][currCol + yPositions[i]])
                currLen = Math.max(currLen, dfs(matrix, currRow + xPositions[i], currCol + yPositions[i]) + 1);
        }

        return lengths[currRow][currCol] = currLen;
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        lengths = new int[matrix.length][matrix[0].length];

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j));
            }
        }

        return maxLen;
    }
}
