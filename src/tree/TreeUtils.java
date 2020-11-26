package tree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的类型题目
 */
public class TreeUtils {
    /**
     * =================================================================================================================
     */
    /**
     * lc-剑指offer 26
     * 树的子结构
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recurJudeg(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean recurJudeg(TreeNode A, TreeNode B){
        if(B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return recurJudeg(A.left, B.left) && recurJudeg(A.right, B.right);
    }
    /**
     * =================================================================================================================
     */
    /**
     * 剑指offer-07 [https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/]
     * 重建二叉树
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int val = preorder[0]; // 3
        TreeNode root = new TreeNode(val);
        int rootI = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                rootI = i;
                break;
            }
        }

        int leftSize = rootI;// 1
        int rightSize = inorder.length - 1 - rootI; // 5-1-1 = 3
        // [1, 1+leftSize] [1+leftSize,length-1]
        int[] leftPreorder = new int[leftSize];
        System.arraycopy(preorder, 1, leftPreorder, 0, leftSize);
        int[] rightPreorder = new int[rightSize];
        System.arraycopy(preorder, leftSize + 1, rightPreorder, 0, rightSize);
        // [0, 1-1] [1+1,5-1]
        // 左子树 [0...i-1] 右子树[i+1..length-1]
        int[] leftInorder = new int[leftSize];
        System.arraycopy(inorder, 0, leftInorder, 0, leftSize);
        int[] rightIneorder = new int[rightSize];
        System.arraycopy(inorder, leftSize + 1, rightIneorder, 0, rightSize);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightIneorder);

        return root;
    }
    /**
     * =================================================================================================================
     */
    /**
     * 297. 二叉树的序列化与反序列化 (https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)
     * @param root
     * @return
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return preorderTraversal(root, "");
    }

    public String preorderTraversal(TreeNode root, String ans){
        if (root != null) {
            ans += root.val + ",";
            ans = preorderTraversal(root.left, ans);
            ans = preorderTraversal(root.right, ans);
        } else {
            ans += "None,";
        }
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArr = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArr));
        return rdeserialize(dataList);
    }

    public TreeNode rdeserialize(List<String> dataList){
        if ("None".equals(dataList.get(0))){
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);
        return root;
    }
    /**
     * =================================================================================================================
     */
    /**
     * 剑指 28/ lc 101 (https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)
     * 对称的二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recurSymmetric(root.left, root.right);
    }

    public boolean recurSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return recurSymmetric(left.left, right.right) && recurSymmetric(left.right, right.left);
    }
    /**
     * =================================================================================================================
     */
}
