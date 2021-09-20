import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-20 16:56
 */
public class JianDanSuanFa {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /*
    给你两个二进制字符串，返回它们的和（用二进制表示）。
    输入为 非空 字符串且只包含数字 1 和 0。
    示例 1: 输入: a = "11", b = "1"         输出: "100"
    示例 2: 输入: a = "1010", b = "1011"    输出: "10101"
     */
    public static String addBinary(String a, String b) {
        int m = a.length() - 1;
        int n = b.length() - 1;
        if(m < n){
            return addBinary(b,a);
        }
        int p = 0;
        String s = "";
        while(m >= 0){
            int x = Integer.parseInt(a.substring(m,m+1));
            m--;
            int y = 0;
            if(n >= 0){
                y = Integer.parseInt(b.substring(m,m+1));
                n--;
            }
            int z = p + x + y;
            if(z >= 2){
                p = 1;
                s = z % 2 + s;
            }else{
                p = 0;
                s = z + s;
            }
            if(m == -1 && p == 1){
                s = 1 + s;
            }
        }
        return s;
    }
    /*
        假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
        注意：给定 n 是一个正整数。
        示例 1：输入2    输出2
        解释：有两种方法可以爬到楼顶。1. 1阶+1阶 2. 2阶
        示例2：输入3     输出3
        解释：有三种方法可以爬到楼顶。1. 1阶+1阶+1阶   2. 1阶+2阶  3. 2阶+1阶
     */
    public static int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int[] i = new int[n];
        i[0] = 1;
        i[1] = 2;
        for (int j = 2; j < i.length; j++) {
            i[j] = i[j-1] + i[j-2];
        }
        return i[n-1];
    }
    /*
        给定一个二叉树，检查它是否是镜像对称的。
        例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
            1
           / \
          2   2
         / \ / \
        3  4 4  3
        但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
            1
           / \
          2   2
           \   \
           3    3
     */
    public static boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left,root.right);
    }
    public static boolean isSymmetric(TreeNode root1,TreeNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }else if(root1 == null || root2 == null){
            return false;
        }
        return root1.val == root2.val && isSymmetric(root1.left,root2.right) && isSymmetric(root1.right,root2.left);
    }
    /*
        给定一个二叉树，找出其最大深度。
        二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
        说明: 叶子节点是指没有子节点的节点。
        示例：
        给定二叉树 [3,9,20,null,null,15,7]，
            3
           / \
          9  20
            /  \
           15   7
        返回它的最大深度 3
     */
    public static int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else {
            return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
        }
    }
    /*
        给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
        高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
        示例 1：输入：nums = [-10,-3,0,5,9]   输出：[0,-3,9,-10,null,5]
        解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
        示例 2：输入：nums = [1,3]            输出：[3,1]
        解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        Arrays.sort(nums);
        return sortedArrayToBST(nums,0,nums.length-1);
    }
    public static TreeNode sortedArrayToBST(int[] nums,int start,int end) {
        TreeNode t = new TreeNode();
        if(start == end){
            t.val = nums[end];
        }
        if(end - start == 1){
            t.val = nums[start];
            TreeNode t2 = new TreeNode(nums[end]);
            t.right = t2;
        }
        if(end - start == 2){
            TreeNode t1 = new TreeNode(nums[start]);
            TreeNode t2 = new TreeNode(nums[end]);
            t.val = nums[(end+start)/2];
            t.left = t1;
            t.right = t2;
        }
        if(end - start > 2){
            int mid = (end + start)/2;
            t.val = nums[mid];
            t.left = sortedArrayToBST(nums,start,mid-1);
            t.right = sortedArrayToBST(nums,mid+1,end);
        }
        return t;
    }
    /*
        给定一个二叉树，判断它是否是高度平衡的二叉树。
        本题中，一棵高度平衡二叉树定义为：
        一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
        示例 1：输入：root = [3,9,20,null,null,15,7]  输出：true
        示例 2：输入：root = [1,2,2,3,3,null,null,4,4]    输出：false
        示例 3：输入：root = []   输出：true
     */
    public static boolean isBalanced(TreeNode root) {
        int m = treeNodeHeight(root.left) - treeNodeHeight(root.right);
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(m)<=1);
    }
    public static int treeNodeHeight(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            return Math.max(treeNodeHeight(root.right),treeNodeHeight(root.left)) + 1;
        }
    }
    /*
        给定一个二叉树，找出其最小深度。
        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
        说明：叶子节点是指没有子节点的节点。
        示例 1：输入：root = [3,9,20,null,null,15,7]  输出：2
        示例 2：输入：root = [2,null,3,null,4,null,5,null,6]  输出：5
     */
    public static int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null){
            return minDepth(root.right)+1;
        }
        if(root.right == null){
            return minDepth(root.left)+1;
        }
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }
    /*
        给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
        说明：本题中，我们将空字符串定义为有效的回文串。
        示例 1:输入: "A man, a plan, a canal: Panama"   输出: true    解释："amanaplanacanalpanama" 是回文串
        示例 2:输入: "race a car"                       输出: false   解释："raceacar" 不是回文串
     */
    public static boolean isPalindrome(String s) {
        Boolean b = true;
        if(s == null || s.length() == 0){
            return b;
        }
        int m = 0;
        int n = s.length()-1;
        while (m <= n ){
            while(m <= n && !((65 <= s.charAt(m) && s.charAt(m)<=90)||(97 <= s.charAt(m) && s.charAt(m)<=122) || (48 <= s.charAt(m) && s.charAt(m)<=57))){
                m++;
            }
            while(m <= n && !((65 <= s.charAt(n) && s.charAt(n)<=90)||(97 <= s.charAt(n) && s.charAt(n)<=122) || (48 <= s.charAt(n) && s.charAt(n)<=57))){
                n--;
            }
            char c1 = s.charAt(m);
            char c2 = s.charAt(n);
            if(((48 <= c1 && c1 <= 57) && !(48 <= c2 && c2 <= 57)) || (!(48 <= c1 && c1 <= 57) && (48 <= c2 && c2 <= 57))){
                b = false;
                break;
            }
            if(97 <= c1 && c1<=122){
                c1 -= 32;
            }

            if(97 <= c2 && c2<=122) {
                c2 -= 32;
            }
            if(c1 != c2){
                b = false;
                break;
            }else {
                m++;
                n--;
            }
        }
        return b;
    }
    public static void main(String[] args) {
        boolean op = isPalindrome("ab2a");
        System.out.println(op);
    }
}
