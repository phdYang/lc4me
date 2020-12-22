package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeCode {

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
}
