package tree;

import sun.reflect.generics.tree.Tree;
import sun.rmi.log.LogInputStream;

import java.util.*;

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
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
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
    /**
     * 剑指 Offer 27. 二叉树的镜像 / lc 226 (https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/)
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return  root;
    }
    /**
     * =================================================================================================================
     */
    /**
     * LCP 10. 二叉树任务调度
     * @param root
     * @return
     */
    public double minimalExecTime(TreeNode root) {

        return recurExec(root)[0];
    }
    public double[] recurExec(TreeNode root) {
        if (root == null) {
            return new double[]{0.0D, 0.0D};
        }
        double[] leftTimes = recurExec(root.left);
        double[] rightTimes = recurExec(root.right);
        double sum = leftTimes[1] + rightTimes[1];
        double minTime = Math.max(Math.max(leftTimes[0], rightTimes[0]), sum/2) + root.val;
        return new double[]{minTime, sum + root.val};
    }

    /**
     * =================================================================================================================
     */
    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表 / lc 426 (https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)
     * * 什么是二叉搜索树
     */
    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    void dfs(TreeNode cur){
        if (cur == null) return;
        dfs(cur.left);
        if(pre != null) {
            pre.right = cur;
        }else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     */

    int ans, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs4kthLargest(root);
        return ans;
    }

    public void dfs4kthLargest(TreeNode root) {
        if (root != null) {
            dfs4kthLargest(root.right);
            if (--k == 0) {
               ans = root.val;
            }
            dfs4kthLargest(root.left);
        }
    }
    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     */
    // 解法一 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null){
            return left;
        }
        return root;
    }
    // 二叉搜索树
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor3(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor3(root.left, p, q);
        }
        return root;
    }

    /**
     * [剑指 Offer 33. 二叉搜索树的后序遍历序列](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return dfs(postorder, 0, postorder.length - 1);
    }

    public boolean dfs(int[] postorder, int i, int j) {
        if (i >= j )return true;
        int p = i;
        while (postorder[p] < postorder[j])p++;
        int m = p;
        while (postorder[p] > postorder[j])p++;
        return p == j && dfs(postorder,i, m - 1) && dfs(postorder, m, j - 1);
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径 / LC 113
     * @param root
     * @param sum
     * @return
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return res;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) return;
        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right ==null){
            res.add(new LinkedList<>(path));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        path.removeLast();
    }

    /**
     * 572. 另一个树的子树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)return false;
        return dfs(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean dfs(TreeNode s, TreeNode t){
        if (s == null && t == null) return true;
        if (s == null || t == null || s.val != t.val) return false;
        return dfs(s.left, t.left) && dfs(s.right, t.right);
    }

    /**
     * 面试题 04.10. 检查子树
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        return (t1 != null && t2 != null) &&
                (check(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2));
    }

    public boolean check(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null || t1.val != t2.val)return false;
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }


    /**
     * 1609. 奇偶树
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int preVal = -1;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null)queue.offer(tmp.left);
                if (tmp.right != null)queue.offer(tmp.right);
                int val = tmp.val;
                if (level % 2 == 0) { // 偶数
                    if (val % 2 == 0) return false;
                    if (preVal != -1) {
                        if (val <= preVal) return false;
                    }
                }else { // 奇数
                    if (val % 2 != 0) return false;
                    if (preVal != -1) {
                        if (val >= preVal) return false;
                    }
                }
                preVal = val;
            }
            level++;
        }
        return true;
    }

    /**
     * 100. 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * @param root
     * @return
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null)return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    /**
     * 617. 合并二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    /**
     * 310. 最小高度树
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0); // 0 is last node
            return res;
        }
        // 构造度和邻接矩阵
        int[] degree = new int[n];
        List<List<Integer>> mList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            mList.get(edge[0]).add(edge[1]);
            mList.get(edge[1]).add(edge[0]);
        }
        // bfs遍历
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i]==1)q.offer(i);
        }
        
        while (!q.isEmpty()) {
            res = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                res.add(cur);

                List<Integer> neighbors = mList.get(cur);
                for (int neighbor:neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) q.offer(neighbor);
                }
            }
        }
        return res;
    }

    /**
     * 897. 递增顺序查找树
     * @param root
     * @return
     */
    List<Integer> rs = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        inOrder(root);
        if (rs.size() == 0)return null;
        TreeNode ret = new TreeNode(rs.get(0));
        TreeNode p = ret;
        for (int i = 1; i < rs.size(); i++) {
            TreeNode tmp = new TreeNode(rs.get(i));
            p.left = null;
            p.right = tmp;
            p = p.right;
        }
        return ret;
    }

    public void inOrder(TreeNode root){
        if (root != null) {
            inOrder(root.left);
            rs.add(root.val);
            inOrder(root.right);
        }
    }

    /**
     * 655. 输出二叉树
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        int n = getHeight(root);
        String[][] res = new String[n][(1 << n) - 1];
        for(String[] arr : res)
            Arrays.fill(arr,"");
        List<List<String>> ans = new ArrayList<>();
        fill(res, root, 0, 0, res[0].length - 1);
        for(String[] arr:res)
            ans.add(Arrays.asList(arr));
        return ans;
    }

    public int getHeight(TreeNode root){
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public void fill(String[][] res, TreeNode root, int i, int start, int end) {
        if (root == null) {
            return;
        }
        res[i][(start + end) / 2] = "" + root.val;
        fill(res, root.left, i + 1, start, (start + end)/2 - 1);
        fill(res, root.right, i + 1,(start + end)/2 + 1, end);
    }

    /**
     * 814. 二叉树剪枝
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
       if (root == null) return null;
       root.left = pruneTree(root.left);
       root.right = pruneTree(root.right);
       if (root.left == null && root.right == null && root.val == 0)return null;
       return root;
    }

    /**
     * 968. 监控二叉树
     * @param root
     * @return
     */
    int result = 0;
    public int minCameraCover(TreeNode root) {
        if (root == null)return result;
        if (trav(root) == 0) {
            result++;
        }
        return result;
    }

    public int trav(TreeNode root){
        if (root == null) return 2;
        int left = trav(root.left);
        int right = trav(root.right);

        if (left == 2 && right == 2) return 0;
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }

        if (left == 1 || right == 1) return 2;

        return -1;

    }
}
