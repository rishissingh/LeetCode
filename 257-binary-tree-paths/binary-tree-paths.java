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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        StringBuilder path = new StringBuilder();
      
        dfs(root, path, result);
        return result;
    }

    private void dfs(TreeNode node, StringBuilder path, List<String> result) {
        if (node == null) {
            return;
        }
        int len = path.length();

        path.append(node.val);
        if (node.left == null && node.right == null) {
            result.add(path.toString());
        } else {
            path.append("->");
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

        path.setLength(len);
    
    }
}