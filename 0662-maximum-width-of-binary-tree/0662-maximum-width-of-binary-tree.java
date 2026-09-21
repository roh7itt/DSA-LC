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
    static class Pair {
        TreeNode node;
        int val;        // ← the name here is what .val refers to
        Pair(TreeNode node, int val) {
            this.node = node;
            this.val = val;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Queue<Pair> q= new LinkedList<>();
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()){
            int size = q.size();
            int imin = q.peek().val;
            int first = 0, last = 0;
            for(int i = 0; i < size; i++){
                int curId =q.peek().val-imin;
                TreeNode node = q.peek().node;
                q.poll();
                if(i==0) first = curId;
                if(i==size-1) last = curId;
                if(node.left != null)
                    q.offer(new Pair(node.left, curId*2+1));
                if(node.right != null)
                    q.offer(new Pair(node.right, curId*2+2));

            }
            ans = Math.max(ans, last-first+1);   
        }
        return ans;
    }
}