/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] dia = new int[1];
        dia[0] =0;
        height(root,dia);
        return dia[0];
    }
    private int height(TreeNode root, int[] dia){
        if(root == null) return 0;
        int[] lh = new int[1];
        int[] rh = new int[1];
        lh[0] = height(root.left, dia);
        rh[0] = height(root.right, dia);
        dia[0] = Math.max(dia[0], lh[0]+rh[0]);
        return 1+Math.max(lh[0], rh[0]);
    }
}

/*
BRUTEFORCE
int diameter =0;

if (node == null) {
            return 0;
        }

        // Recursively calculate the
        // height of left and right subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Calculate the diameter at the current
        // node and update the global variable
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height
        // of the current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Function to find the
    // diameter of a binary tree
    int diameterOfBinaryTree(Node root) {
        // Start the recursive
        // traversal from the root
        calculateHeight(root);

        // Return the maximum diameter
        // found during traversal
        return diameter;
 */