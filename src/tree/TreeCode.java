package tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeCode {

    /**
     * 654. 最大二叉树
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public TreeNode dfs(int[] nums, int start, int end) {
        if ( start > end) return null;
        int maxi = start;
        int max = nums[start];
        for(int i = start;i<=end;i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxi = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxi]);
        root.left = dfs(nums, start, maxi-1);
        root.right = dfs(nums, maxi+1, end);

        return root;

    }

    /**
     * 114. 二叉树展开为链表
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, ans);
        for (int i = 1; i < ans.size(); i++) {
            TreeNode cur = ans.get(i-1);
            TreeNode post = ans.get(i);
            cur.left = null;
            cur.right = post;
        }
    }

    public void dfs(TreeNode root, List<TreeNode> ans) {
        if (root == null) return;
        ans.add(root);
        dfs(root.left, ans);
        dfs(root.right, ans);
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针 解法一 BFS
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null)return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        root.next = null;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size ; i++) {
                Node p = q.poll();

                if (i<size-1)p.next = q.peek();

                if (p.left != null)q.offer(p.left);
                if (p.right != null)q.offer(p.right);
            }

        }
        return root;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针 解法二 dfs 递归
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if (root == null) return null;
        connectTwo(root.left, root.right);
        return root;
    }
    public void connectTwo(Node left, Node right){
        if (left == null || right == null)  return;
        left.next = right;
        connectTwo(left.left, left.right);
        connectTwo(right.left, right.right);
        connectTwo(left.right, right.left);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
