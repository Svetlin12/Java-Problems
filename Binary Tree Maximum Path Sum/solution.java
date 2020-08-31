import java.lang.*;

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
    
    // stores the value of the max path sum
    private int maxSum = Integer.MIN_VALUE;

    // this is where we do the recursion
    private int helper(TreeNode node) {
        if (node == null)
            return 0;

        // get the sum of the left and right paths
        // note that leftSum and rightSum are always positive - this is because we don't want to add negative values
        // to maxSum. If the max path sum is in the left or right subtrees it would already have been noted in maxSum
        int leftSum = Math.max(helper(node.left), 0);
        int rightSum = Math.max(helper(node.right), 0);

        // try calculating the sum as if the current node was root and we can "fork" the path
        maxSum = Math.max(maxSum, node.val + leftSum + rightSum);

        // return the sum of the bigger path
        return node.val + Math.max(leftSum, rightSum);
    }

    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }
}
