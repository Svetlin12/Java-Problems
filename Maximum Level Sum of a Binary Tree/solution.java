import java.lang.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static int bfs(TreeNode root) {
        TreeNode next = root, traverse;
        int maxSum = 0, currSum = 0, maxRow = 1, currRow = 1, currSize = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(next);
        while (!q.isEmpty()) {
            for (int i = 0; i < currSize; i++) {
                traverse = q.remove();
                currSum += traverse.val;

                if (traverse.left != null) {
                    q.add(traverse.left);
                }

                if (traverse.right != null) {
                    q.add(traverse.right);
                }
            }

            if (maxSum < currSum) {
                maxSum = currSum;
                maxRow = currRow;
            }
            currSize = q.size();
            currSum = 0;
            currRow++;
        }

        return maxRow;
    }

    public static int maxLevelSum(TreeNode root) {
        return bfs(root);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(0);
        t.right = new TreeNode(7);
        TreeNode next = t.right;
        next.left = new TreeNode(7);
        next.right = new TreeNode(-8);
        System.out.println(maxLevelSum(t));
    }
}
