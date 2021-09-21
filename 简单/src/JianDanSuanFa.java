import javax.swing.tree.TreeNode;
import java.util.*;

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
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
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
    /*
        给定一个链表，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
        如果链表中存在环，则返回 true 。 否则，返回 false 。
        进阶：你能用 O(1)（即，常量）内存解决此问题吗？
        示例 1：   输入：head = [3,2,0,-4], pos = 1   输出：true
        解释：链表中有一个环，其尾部连接到第二个节点。
        示例 2：   输入：head = [1,2], pos = 0        输出：true
        解释：链表中有一个环，其尾部连接到第一个节点。
        示例 3：   输入：head = [1], pos = -1         输出：false
        解释：链表中没有环。
     */
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        Boolean b = false;
        ListNode que = head;
        ListNode slo = head;
        while(que != null && que.next != null){
            que = que.next.next;
            slo = slo.next;
            if(que == slo){
                b = true;
                break;
            }
        }
        return b;
    }
    /*
        给你二叉树的根节点root ，返回它节点值的前序遍历。
        示例 1：输入：root = [1,null,2,3] 输出：[1,2,3]
        示例 2：输入：root = []           输出：[]
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        list.add(root.val);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);
        return list;
    }
    /*
        给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于⌊n/2⌋的元素。
        你可以假设数组是非空的，并且给定的数组总是存在多数元素。
        示例 1：输入：[3,2,3]         输出：3
        示例 2：输入：[2,2,1,1,1,2,2] 输出：2
     */
    public static int majorityElement1(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int m = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>();
        int y = 0;
        for (int i = 0; i < nums.length; i++) {
            Integer x = map.get(nums[i]);
            if(x != null){
                if(++x > m){
                    y = nums[i];
                    break;
                }
                map.put(nums[i],x);
            }else {
                map.put(nums[i],1);
            }
        }
        return y;
    }
    public static int majorityElement2(int[] nums) {
        int anc = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if(count == 0){
                anc = nums[i];
                count = 1;
            }else if(nums[i] != anc){
                count--;
            }else{
                count++;
            }
        }
        return anc;
    }
    /*
        编写一个算法来判断一个数 n 是不是快乐数。
        「快乐数」定义为：
        对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
        然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到1。
        如果 可以变为  1，那么这个数就是快乐数。
        如果 n 是快乐数就返回 true ；不是，则返回 false 。
        示例 1：输入：19      输出：true
        示例 2：输入：n = 2   输出：false
     */
    public static boolean isHappy(int n) {
        if(n == 1){
            return true;
        }
        Boolean b = false;
        Map<Integer,Integer> map= new HashMap<>();
        map.put(n,1);
        while(n>1){
            n = intForInt(n);
            if(map.containsKey(n)){
                break;
            }
            if(n == 1){
                b = true;
            }
            map.put(n,1);
        }
        return b;
    }
    /**
     * 输入一个大于0的正整数，返回各个位上数字的平方和
     * @param m
     * @return
     */
    public static int intForInt(int m){
        int total = 0;
        int n = m % 10;
        total += n * n;
        while (m>=10){
            m /= 10;
            total += (m%10) *(m%10);
        }
        return total;
    }
    /*
        给你一个链表的头节点head和一个整数val,请你删除链表中所有满足Node.val==val的节点,并返回新的头节点 。
        示例 1：输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
        示例 2：输入：head = [], val = 1              输出：[]
        示例 3：输入：head = [7,7,7,7], val = 7       输出：[]
     */
    public static ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        while (head != null && head.val == val){
            head = head.next;
        }
        ListNode listNode = head;
        ListNode pre = head;
        while (pre != null){
            ListNode next = pre;
            while (next != null && next.val == val){
                next = next.next;
            }
            pre.next = next;
            pre = next;
        }
        return listNode;
    }
    public static boolean isIsomorphic(String s, String t) {
        Boolean b = true;
        if(s.length() != t.length()){
            return !b;
        }
        Map<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i)) && !map.containsValue(t.charAt(i))){
                map.put(s.charAt(i),t.charAt(i));
            }else {
                Character character = map.get(s.charAt(i));
                if(character == null || character != t.charAt(i)){
                    b = false;
                    break;
                }
            }
        }
        return b;
    }
    /*
        给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
        示例 1：输入：head = [1,2,3,4,5]  输出：[5,4,3,2,1]
        示例 2：输入：head = [1,2]        输出：[2,1]
        示例 3：输入：head = []           输出：[]
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        while (next!=null){
            ListNode listNode = next.next;
            next.next = pre;
            pre = next;
            next = listNode;
        }
        return pre;
    }
    /*
        给定一个整数数组，判断是否存在重复元素。
        如果存在一值在数组中出现至少两次，函数返回true.如果数组中每个元素都不相同，则返回false 。
        示例 1:输入: [1,2,3,1]                  输出: true
        示例 2:输入: [1,2,3,4]                  输出: false
        示例 3:输入: [1,1,1,3,3,4,3,2,4,2]      输出: true
     */
    public static boolean containsDuplicate(int[] nums) {
        Boolean b = false ;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                b = true;
                break;
            }
            map.put(nums[i],1);
        }
        return b;
    }
    /*
        给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引i和j，使得nums[i]=nums[j]，并且i和j的差的绝对值至多为k。
        示例 1:输入: nums = [1,2,3,1], k = 3        输出: true
        示例 2:输入: nums = [1,0,1,1], k = 1        输出: true
        示例 3:输入: nums = [1,2,3,1,2,3], k = 2    输出: false
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Boolean b = false ;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)){
                b = true;
                break;
            }
            map.put(nums[i],i);
        }
        return b;
    }
    /*
        给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
        注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
        示例 1:输入: s = "anagram", t = "nagaram"   输出: true
        示例 2:输入: s = "rat", t = "car"           输出: false
     */
    public static boolean isAnagram(String s, String t) {
        Boolean b = true;
        if(s.length() != t.length()){
            return !b;
        }
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int m = 1;
            if(map.containsKey(s.charAt(i))){
                m += map.get(s.charAt(i));
            }
            map.put(s.charAt(i),m);
        }
        for (int i = 0; i < t.length(); i++) {
            if(!map.containsKey(t.charAt(i)) || map.get(t.charAt(i))==0){
                b = false;
                break;
            }
            int m = map.get(t.charAt(i));
            map.put(t.charAt(i),--m);
        }
        return b;
    }
    /*
        给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
        叶子节点 是指没有子节点的节点。
        示例 1：输入：root = [1,2,3,null,5]   输出：["1->2->5","1->3"]
        示例 2：输入：root = [1]              输出：["1"]
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.add(root);
        q2.add(Integer.toString(root.val));
        while (!q1.isEmpty()){
            TreeNode tl = q1.poll();
            String s = q2.poll();
            if(tl.left == null && tl.right == null){
                list.add(s);
            }
            if(tl.left != null){
                q1.add(tl.left);
                String s1 = s + "->" + tl.left.val;
                q2.add(s1);
            }
            if(tl.right != null){
                q1.add(tl.right);
                String s2 = s + "->" + tl.right.val;
                q2.add(s2);
            }
        }
        return list;
    }
    /*
        你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头。你们轮流进行自己的回合，你作为先手。每一回合，轮到的人拿掉 1 - 3 块石头。拿掉最后一块石头的人就是获胜者。
        假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
        示例 1：输入：n = 4   输出：false
        解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
        示例 2：输入：n = 1   输出：true
        示例 3：输入：n = 2   输出：true
     */
    /**
     * 题解：让我们考虑一些小例子。显而易见的是，如果石头堆中只有一块、两块、或是三块石头，
     * 那么在你的回合，你就可以把全部石子拿走，从而在游戏中取胜；如果堆中恰好有四块石头，
     * 你就会失败。因为在这种情况下不管你取走多少石头，总会为你的对手留下几块，他可以将
     * 剩余的石头全部取完，从而他可以在游戏中打败你。因此，要想获胜，在你的回合中，必须
     * 避免石头堆中的石子数为 4的情况。
     * 我们继续推理，假设当前堆里只剩下五块、六块、或是七块石头，你可以控制自己拿取的石头
     * 数，总是恰好给你的对手留下四块石头，使他输掉这场比赛。但是如果石头堆里有八块石头，
     * 你就不可避免地会输掉，因为不管你从一堆石头中挑出一块、两块还是三块，你的对手都可以
     * 选择三块、两块或一块，以确保在再一次轮到你的时候，你会面对四块石头。显然我们继续推
     * 理，可以看到它会以相同的模式不断重复 n = 4, 8, 12, 16,…，基本可以看出如果堆里的石
     * 头数目为 4的倍数时，你一定会输掉游戏。
     * 如果总的石头数目为 4的倍数时，因为无论你取多少石头，对方总有对应的取法，让剩余的石
     * 头的数目继续为 4的倍数。对于你或者你的对手取石头时，显然最优的选择是当前己方取完石
     * 头后，让剩余的石头的数目为 44 的倍数。假设当前的石头数目为 xx，如果 xx 为 44 的倍
     * 数时，则此时你必然会输掉游戏；如果 x 不为 4 的倍数时，则此时你只需要取走xmod4个石头时，则剩余的石头数目必然为4的倍数，从而对手会输掉游戏。
     * @param n
     * @return
     */
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
    /*
        给你一个整数n，对于0<=i<=n中的每个i，计算其二进制表示中1的个数 ，返回一个长度为n+1的数组ans作为答案。
        示例 1：
        输入：n = 2
        输出：[0,1,1]
        解释：0-->0  1-->1  2-->10
        示例 2：   输入：n = 5    输出：[0,1,1,2,1,2]
        解释：0-->0  1-->1  2-->10  3-->11  4-->100  5-->101
     */
    /**
     * 题解：
     * 方法一：Brian Kernighan 算法
     * 最直观的做法是对从0到 n的每个整数直接计算「一比特数」。每个int型的数都可以用32位二进制数表示，只要遍历其二进制表示的每一位即可得到1的数目。
     * 利用 Brian Kernighan算法，可以在一定程度上进一步提升计算速度。Brian Kernighan算法的原理是：对于任意整数x，令 x=x&(x-1)，该运算将x的二进制表示的最后一个1变成0。因此，对x重复该操作，直到x变成0，则操作次数即为x的「一比特数」。
     * 对于给定的n，计算从0到n的每个整数的「一比特数」的时间都不会超过O(logn)，因此总时间复杂度为O(nlogn)。
     * 方法二：动态规划——最高有效位
     * 方法一需要对每个整数使用O(logn)的时间计算「一比特数」。可以换一个思路，当计算i的「一比特数」时，如果存在0≤j<i，j的「一比特数」已知，且i和j相比，i的二进制表示只多了一个1，则可以快速得到i的「一比特数」。
     * 令bits[i]表示i的「一比特数」，则上述关系可以表示成：bits[i]=bits[j]+1。
     * 对于正整数xx，如果可以知道最大的正整数y，使得y≤x且y是2的整数次幂，则y的二进制表示中只有最高位是1，其余都是0，此时称y为x的「最高有效位」。令z=x−y，显然0≤z<x，则[x]=bits[z]+1。
     * 为了判断一个正整数是不是2的整数次幂，可以利用方法一中提到的按位与运算的性质。如果正整数y是2的整数次幂，则y的二进制表示中只有最高位是1，其余都是0，因此y&(y−1)=0。由此可见，正整数y是2的整数次幂，当且仅当y & (y−1)=0。
     * 显然，0的「一比特数」为0。使用highBit表示当前的最高有效位，遍历从1到n的每个正整数i，进行如下操作。
     * 如果i&(i−1)=0，则令 ihighBit=i，更新当前的最高有效位。
     * i比i−highBit的「一比特数」多1，由于是从小到大遍历每个整数，因此遍历到i时，i−highBit的「一比特数」已知，令bits[i]=bits[i−highBit]+1。
     * 最终得到的数组 bits即为答案。
     * 方法三：动态规划——最低有效位
     * 方法二需要实时维护最高有效位，当遍历到的数是2的整数次幂时，需要更新最高有效位。如果再换一个思路，可以使用「最低有效位」计算「一比特数」。
     * 对于正整数x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数是⌊x/2⌋。如果bits[⌊x/2⌋] 的值已知，则可以得到bits[x]的值：
     * 如果x是偶数，则bits[x]=bits[⌊x/2⌋]；如果x是奇数，则bits[x]=bits[⌊x/2⌋]+1。
     * 上述两种情况可以合并成：bits[x]的值等于bits[⌊x/2⌋] 的值加上x除以2的余数。
     * 由于⌊x/2⌋ 可以通过x>>1 得到，x除以2的余数可以通过x&1得到，因此有：bits[x]=bits[x>>1]+(x & 1)。
     * 遍历从1到n的每个正整数i，计算bits的值。最终得到的数组bits即为答案。
     * @param
     */
    public static int[] countBits1(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }
    public static int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }
    public static int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
    public static int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
    /*
        编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组char[]的形式给出。
        不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用O(1)的额外空间解决这一问题。
        你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
        示例 1：输入：["h","e","l","l","o"]输出：["o","l","l","e","h"]
        示例 2：输入：["H","a","n","n","a","h"]输出：["h","a","n","n","a","H"]
     */
    public static void reverseString(char[] s) {
        int m = s.length-1;
        for (int i = 0; i < s.length/2; i++) {
            char c = s[i];
            s[i] = s[m-i];
            s[m-i] = c;
        }
    }
    /*
        给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
        元音字母包括'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
        示例 1：输入：s = "hello"输出：      "holle"
        示例 2：输入：s = "leetcode"输出：   "leotcede"
     */
    public static String reverseVowels(String s) {
        /*Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');*/
        int left = 0;
        int right = s.length()-1;
        char[] chars = s.toCharArray();
        String isVOwel = "aeiouAEIOU";
        while (left < right){
            /*while(left < right && !set.contains(s.charAt(left))){
                left++;
            }
            while(left < right && !set.contains(s.charAt(right))){
                right--;
            }*/
            while(left < right && !(isVOwel.indexOf(chars[left])>=0)){
                left++;
            }
            while(left < right && !(isVOwel.indexOf(chars[right])>=0)){
                right--;
            }
            if(left<right){
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
        }
        return new String(chars);
    }
    public static void main(String[] args) {
        String hello = reverseVowels("leedcode");
        System.out.println(hello);
    }
}
