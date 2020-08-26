import java.util.*;
import java.lang.*;

public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

public static class Codec {

        public static StringBuilder serializeHelper(TreeNode node, StringBuilder serialized) {
            if (node == null) {
               serialized.append("null,");
               return serialized;
            }
            else {
                serialized.append(node.val + ",");
                serialized = serializeHelper(node.left, serialized);
                return serializeHelper(node.right, serialized);
            }
        }

        // Encodes a tree to a single string.
        public static String serialize(TreeNode root) {
            StringBuilder serialized = new StringBuilder();
            serialized = serializeHelper(root, serialized);
            serialized.deleteCharAt(serialized.length() - 1);
            return serialized.toString();
        }

        public static TreeNode deserializeHelper(Queue<String> vals, TreeNode node) {
            if (vals.isEmpty())
                return null;

            String currVal = vals.remove();
            if (currVal.equals("null"))
                return null;

            node = new TreeNode(Integer.parseInt(currVal));
            node.left = deserializeHelper(vals, node.left);
            node.right = deserializeHelper(vals, node.right);
            return node;
        }

        // Decodes your encoded data to tree.
        public static TreeNode deserialize(String data) {
            String[] nodeVals = data.split(",");
            Queue<String> q = new LinkedList<>();
            Collections.addAll(q, nodeVals);

            if (nodeVals.length == 0)
                return null;

            TreeNode root = null;
            root = deserializeHelper(q, root);
            return root;
        }
}

public static void printTree(TreeNode t) {
    System.out.print(t.val + " "); 
    if (t.left != null)
        printTree(t.left);

    if (t.right != null)
        printTree(t.right);
}

public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    root.right.right.right = new TreeNode(6);

    printTree(root);
    TreeNode t = Codec.deserialize(Codec.serialize(root));
    System.out.println();
    printTree(t);
}
