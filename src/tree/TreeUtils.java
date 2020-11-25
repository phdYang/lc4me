package tree;

/**
 * 树的类型题目
 */
public class TreeUtils {

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
}
