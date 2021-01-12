package tree;




import java.util.*;

public class TreeCode {




    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode p1 = new TreeNode(0);
        TreeNode p2 = new TreeNode(1);
        TreeNode p3 = new TreeNode(0);
        TreeNode p4 = new TreeNode(1);
        TreeNode p5 = new TreeNode(0);
        TreeNode p6 = new TreeNode(1);
        root.left = p1;
        root.right = p2;

        p1.left = p3;
        p1.right = p4;

        p2.left = p5;
        p2.right = p6;

        System.out.println(sumRootToLeaf(root));
    }


    static int lAns = 0;

    public static int sumRootToLeaf(TreeNode root) {
        travel(root, 0);
        return lAns;
    }

    public static void travel(TreeNode root, int ans) {
        if (root == null) return;
        ans = ans * 2 + root.val;
        if (root.left == null && root.right == null) {
            lAns += ans;
        }

        travel(root.left, ans);
        travel(root.right, ans);
    }


    int res = 0;

    /**
     * 1530. 好叶子节点对的数量
     * @param root
     * @param distance
     * @return
     */
    public int countPairs(TreeNode root, int distance) {
       dfs(root, distance);
       return res;
    }

    public List<Integer> dfs(TreeNode root, int distance) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        if (root.left == null && root.right == null) {
            ans.add(1);
            return ans;
        }
        List<Integer> left = dfs(root.left, distance);
        for (int item:left) {
            if (++item > distance) {
                continue;
            }
            ans.add(item);
        }
        List<Integer> right = dfs(root.right, distance);
        for (int item:right) {
            if (++item > distance) {
                continue;
            }
            ans.add(item);
        }

        for (int l:left) {
            for (int r:right) {
                res += (l+r) <= distance ? 1 : 0;
            }
        }
        return ans;
    }

    /**
     * 222. 完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int hl = 0, hr = 0;
        TreeNode l = root, r = root;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }
        if (hl == hr)
            return (int)Math.pow(2, hl) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 437. 路径总和 III
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,1);
        return recursionPathSum(root, prefixSum, sum, 0);
    }

    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSum, int sum, int curSum) {
        if (node == null)return 0;
        int res = 0;
        curSum += node.val;
        res += prefixSum.getOrDefault(curSum - sum, 0);
        prefixSum.put(curSum, prefixSum.getOrDefault(curSum, 0) + 1);

        res += recursionPathSum(node.left, prefixSum, sum, curSum);
        res += recursionPathSum(node.right, prefixSum, sum, curSum);

        prefixSum.put(curSum, prefixSum.get(curSum) - 1);
        return res;
    }

    /**
     * 112. 路径总和
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum = sum - root.val;
        if (root.left == null && root.right == null && sum == 0) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);

    }

    /**
     * 998. 最大二叉树 II
     * 1. 如果根为空，则说明val最小，放在左子树
     * 2. 如果根小于val，则将根挂在val的左子树
     * 3. 剩下的情况就是 右子树的情况----？
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || val > root.val) {
            TreeNode p = new TreeNode(val);
            p.left = root;
            return p;
        } else {
            root.right = insertIntoMaxTree(root.right, val);

            return root;
        }
    }


    /**
     * 701. 二叉搜索树中的插入操作
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)return new TreeNode(val);
        if (root.val > val) { //
            root.left = insertIntoBST(root.left, val);
        }else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        }else if (root.val > val) {
            return searchBST(root.left, val);
        }else if (root.val < val) {
            return searchBST(root.right, val);
        }
        return root;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && min.val >= root.val) return false;
        if (max != null && max.val <= root.val) return false;

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode t = root;
            TreeNode node = t.right;
            while(node.left != null) node = node.left;
            // node is min node
            root = node;
            root.right = deleteMin(t.right);
            root.left = t.left;

        }else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public TreeNode deleteMin(TreeNode root) {
        if(root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * @param root
     * @param k
     * @return
     */
    int kth = 0;
    int kthAns = 0;
    public int kthSmallest(TreeNode root, int k) {
        traversel(root, k);
        return kthAns;
    }
    public void traversel(TreeNode root, int k) {
        if (root == null) return;
        traversel(root.left, k);
        kth++;
        if (kth == k) kthAns = root.val;
        traversel(root.right, k);
    }

    int rs = 0;

    /**
     * 563. 二叉树的坡度
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        getNodeVal(root);
        return rs;

    }

    public int getNodeVal(TreeNode root) {
        if (root == null) return 0;
        int left = getNodeVal(root.left);
        int right = getNodeVal(root.right);
        rs += Math.abs(left - right);
        return left + right + root.val;
    }


    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> ans = new LinkedList<>();

    /**
     * 652. 寻找重复的子树
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    public String dfs(TreeNode root) {
        if (root == null) return "#";
        String left = dfs(root.left);
        String right = dfs(root.right);
        String subTree = left + "," + right + "," + root.val;
        int freq = map.getOrDefault(subTree, 0);
        if (freq == 1) ans.add(root);
        map.put(subTree, freq + 1);
        return subTree;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        // 1 找到根
        int val = postorder[postorder.length - 1];
        // 2 中序 & 后序 -> 左 & 右
        int index = 0;
        for(int i=0;i<inorder.length;i++) {
            if (inorder[i] == val) {
                index = i;
                break;
            }
        }
        int[] inorderLeft = new int[index];
        int[] inorderRight = new int[inorder.length - index - 1];

        int[] postorderLeft = new int[index];
        int[] postorderRight = new int[inorder.length - index - 1];

        for(int i=0;i<inorderLeft.length;i++) {
            inorderLeft[i] = inorder[i];
            postorderLeft[i] = postorder[i];
        }
        for(int i=0;i<inorderRight.length;i++) {
            inorderRight[i] = inorder[index + 1 + i];
            postorderRight[i] = postorder[index + i];
        }
        // 3
        TreeNode root = new TreeNode(val);
        root.left = buildTree(inorderLeft, postorderLeft);
        root.right = buildTree(inorderRight, postorderRight);

        return root;
    }

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
