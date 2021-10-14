import java.util.*;

/**
 * @author shkstart
 * @create 2021-09-22 9:19
 */
public class JianDanSuanFa2 {
    static class TreeNode {
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
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    /*
        给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回true ；否则返回 false。
        (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
        示例 1：输入：ransomNote = "a", magazine = "b"        输出：false
        示例 2：输入：ransomNote = "aa", magazine = "ab"      输出：false
        示例 3：输入：ransomNote = "aa", magazine = "aab"     输出：true
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] ints = new int[26];
        String s1 = ransomNote.toLowerCase();
        String s2 = magazine.toLowerCase();
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            ints[c-65]++;
        }
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if(--ints[c-65] < 0 ){
                return false;
            }
        }
        return true;
    }
    /*
        给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
        示例：s = "leetcode"   返回 0       s = "loveleetcode"   返回 2
     */
    public static int firstUniqChar(String s) {
        int[] ints = new int[26];
        String s1 = s.toLowerCase();
        for (int i = 0; i < s1.length(); i++) {
            ints[s1.charAt(i)-97]++;
        }
        for (int i = 0; i < s1.length(); i++) {
            if(ints[s1.charAt(i)-97] == 1){
                return i;
            }
        }
        return -1;
    }
    /*
        给定两个字符串 s 和 t，它们只包含小写字母。
        字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
        请找出在 t 中被添加的字母。
        示例 1：输入：s = "abcd", t = "abcde" 输出："e"  解释：'e' 是那个被添加的字母。
        示例 2：输入：s = "", t = "y"         输出："y"
        示例 3：输入：s = "a", t = "aa"       输出："a"
        示例 4：输入：s = "ae", t = "aea"     输出："a"
     */
    public static char findTheDifference(String s, String t) {
        int[] ints = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ints[s.charAt(i)-97]++;
        }
        char c = ' ';
        for (int i = 0; i < t.length(); i++) {
            if(ints[t.charAt(i)-97]-- == 0){
                c = t.charAt(i);
            }
        }
        return c;
    }
    /*
        给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
        字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
        进阶：如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
        示例 1：输入：s = "abc", t = "ahbgdc"     输出：true
        示例 2：输入：s = "axc", t = "ahbgdc"     输出：false
     */
    public static boolean isSubsequence(String s, String t) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            queue.add(s.charAt(i));
        }
        int i = 0;
        while(i < t.length() && queue.size() > 0){
            Character poll = queue.peek();
            while (i < t.length() && t.charAt(i)!=poll){
                i++;
            }
            if(i < t.length()){
                queue.poll();
                i++;
            }

        }
        return queue.size() == 0;
    }
    public static int sumOfLeftLeaves(TreeNode root){
        if(root == null){
            return 0;
        }
        if(isYeZi(root)){
            return root.val;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size()>0){
            TreeNode poll = queue.poll();
            if(poll.left != null && isYeZi(poll.left)){
                sum+=poll.left.val;
            }else if(poll.left != null){
                queue.add(poll.left);
            }
            if(poll.right!=null && !isYeZi(poll.right)){
                queue.add(poll.right);
            }
        }
        return sum;
    }
    public static Boolean isYeZi(TreeNode treeNode){
        return treeNode.left == null && treeNode.right==null;
    }
    /*
        给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
        注意:假设字符串的长度不会超过 1010。
        示例 1:输入:"abccccdd"  输出:7  解释:我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     */
    public static int longestPalindrome(String s) {
        int sum = 0;
        Map<Character,Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i)) || map.get(s.charAt(i))==0){
                map.put(s.charAt(i),1);
            }else{
                Integer integer = map.get(s.charAt(i));
                sum+=2;
                map.put(s.charAt(i),--integer);
            }
        }

        return sum < s.length() ? ++sum : sum;
    }
    /*
        统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
        请注意，你可以假定字符串里不包括任何不可打印的字符。
        示例: 输入: "Hello, my name is John"    输出: 5
        解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
     */
    public static int countSegments(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int sum = 0;
        int panduan = 0;
        char c = ' ';
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != c && panduan == 0){
                sum++;
                panduan = 1;
            }
            if(s.charAt(i) == c){
                panduan = 0;
            }
        }
        return sum;
    }
    /*
        假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
        对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。 
        示例 1:输入: g = [1,2,3], s = [1,1]输出: 1
        解释: 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。所以你应该输出1。
        示例 2:输入: g = [1,2], s = [1,2,3]输出: 2
        解释: 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。你拥有的饼干数量和尺寸都足以让所有孩子满足。所以你应该输出2.
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int g1 = 0;
        int s1 = 0;
        while(g1 < g.length && s1 < s.length){
            int m = g[g1];
            while(s1 < s.length){
                if(s[s1] < m){
                    s1++;
                }else {
                    break;
                }
            }
            if(s1 < s.length){
                count++;
            }
            g1++;
            s1++;
        }
        return count;
    }
    /*
        给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。 
        示例 1：输入：num = 5     输出：2
        解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
        示例 2：输入：num = 1     输出：0
        解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     */
    public static int findComplement(int num) {
        int ans = 0;
        int weijinzhi = 1;
        while (num > 1){
            if(num % 2 == 0){
                ans += weijinzhi;
            }
            num /= 2;
            weijinzhi *= 2;
        }
        return ans;
    }
    /*
        给定一个 N 叉树，返回其节点值的 前序遍历 。
        N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
        进阶：递归法很简单，你可以使用迭代法完成此题吗?
        示例 1：输入：root = [1,null,3,2,4,null,5,6]输出：[1,3,5,6,2,4]
        示例 2：输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
                输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     */
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        list.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            List<Integer> preorder = preorder(root.children.get(i));
            list.addAll(preorder);
        }
        return list;
    }
    /*
        给定一个 N 叉树，找到其最大深度。
        最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
        N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
        示例 1：输入：root = [1,null,3,2,4,null,5,6]      输出：3
        示例 2：输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
                输出：5
     */
    public static int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        if(root.children.size() == 0){
            return 1;
        }
        int max = 0;
        for (int i = 0; i < root.children.size(); i++) {
            int i1 = maxDepth(root.children.get(i));
            max = max >= i1 ? max : i1;
        }
        return ++max;
    }
    /*
        给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
        示例：输入："Let's take LeetCode contest"     输出："s'teL ekat edoCteeL tsetnoc"
     */
    public static String reverseWords(String s) {
        String ans = "";
        String s1 = "";
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i) != ' '){
                s1 = s.charAt(i) + s1;
            }else{
                ans = ans + s1 + " ";
                s1 = "";
            }
            if(i == s.length() - 1 && s.charAt(i) != ' '){
                ans = ans + s1;
            }
        }
        return ans;
    }
    /*
        给定一个 N 叉树，返回其节点值的 后序遍历 。
        N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
        进阶：递归法很简单，你可以使用迭代法完成此题吗?
        示例 1：输入：root = [1,null,3,2,4,null,5,6]输出：[5,6,3,2,4,1]
        示例 2：输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
                输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
     */
    public static List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        for (int i = 0; i < root.children.size(); i++) {
            List<Integer> preorder = postorder(root.children.get(i));
            list.addAll(preorder);
        }
        list.add(root.val);
        return list;
    }

    public static double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(i < k){
                maxSum += nums[i];
                if(i == k-1){
                    ints[i] = maxSum;
                }
            }else {
                ints[i] = ints[i-1] + nums[i] - nums[i-k];
                maxSum = ints[i] > maxSum ? ints[i] : maxSum;
            }
        }
        double m = maxSum;
        double n = k;
        return m/n;
    }
    /*
        给定一个初始元素全部为0，大小为m*n的矩阵M以及在M上的一系列更新操作。操作用二维数组表示，其中的每
        个操作用一个含有两个正整数a和b的数组表示，含义是将所有符合0<=i<a以及0<=j<b的元素M[i][j]的值都增加1。
        在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
        示例 1:
        输入: m = 3, n = 3    operations = [[2,2],[3,3]]  输出: 4
        解释: 初始状态, M =
        [[0, 0, 0],
         [0, 0, 0],
         [0, 0, 0]]
        执行完操作 [2,2] 后, M =
        [[1, 1, 0],
         [1, 1, 0],
         [0, 0, 0]]
        执行完操作 [3,3] 后, M =
        [[2, 2, 1],
         [2, 2, 1],
         [1, 1, 1]]
        M中最大的整数是2, 而且M中有4个值为2的元素。因此返回4。
     */
    public static int maxCount(int m, int n, int[][] ops) {
        if(ops.length == 0){
            return m*n;
        }
        int min1 = ops[0][0];
        int min2 = ops[0][1];
        for (int i = 1; i < ops.length; i++) {
            min1 = min1 < ops[i][0] ? min1 : ops[i][0];
            min2 = min2 < ops[i][1] ? min2 : ops[i][1];
        }
        return min1 * min2;
    }
    /*
        给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
        你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
        那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode node = new TreeNode(root1.val+root2.val);
        node.left = mergeTrees(root1.left,root2.left);
        node.right = mergeTrees(root1.right,root2.right);
        return node;
    }
    /*
        包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入)，
        平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
        示例 1:
        输入:[[1,1,1],[1,0,1],[1,1,1]]
        输出:[[0, 0, 0],[0, 0, 0],[0, 0, 0]]
        解释:对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
        对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
        对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     */
    public static int[][] imageSmoother(int[][] img) {
        int l = img.length;
        int w = img[0].length;
        int[][] ans = new int[l][w];
        if(img == null || l == 0 || w == 0){
            return ans;
        }
        if(l == 1 && w == 1){
            return img;
        }else if(l == 1){
            for (int i = 0; i < w; i++) {
                if (i == 0){
                    ans[0][i] = (img[0][i]+img[0][i+1])/2;
                }else if(i == w - 1){
                    ans[0][i] = (img[0][i]+img[0][i-1])/2;
                }else {
                    ans[0][i] = (img[0][i]+img[0][i-1]+img[0][i+1])/3;
                }
            }
            return ans;
        }else if(w == 1) {
            for (int i = 0; i < l; i++) {
                if (i == 0) {
                    ans[i][0] = (img[i][0] + img[i+1][0]) / 2;
                } else if (i == l - 1) {
                    ans[i][0] = (img[i][0] + img[i-1][0]) / 2;
                } else {
                    ans[i][0] = (img[i][0] + img[i+1][0] + img[i-1][0]) / 3;
                }
            }
            return ans;
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                int sum = 0;
                if(0 < i && i < l - 1 && 0 < j && j < w - 1){
                    sum += img[i][j]+img[i][j-1]+img[i][j+1]+img[i-1][j-1]
                            +img[i-1][j]+img[i-1][j+1]+img[i+1][j-1]+img[i+1][j]+img[i+1][j+1];
                    ans[i][j] = sum / 9;
                }else if(i == 0){
                    sum += img[i][j]+img[i+1][j];
                    if(j == 0){
                        sum += img[i][j+1]+img[i+1][j+1];
                        ans[i][j] = sum / 4;
                    }else if(j == w-1){
                        sum += img[i][j-1]+img[i+1][j-1];
                        ans[i][j] = sum / 4;
                    }else {
                        sum += img[i][j+1]+img[i+1][j+1]+img[i][j-1]+img[i+1][j-1];
                        ans[i][j] = sum / 6;
                    }
                }else if(i == l - 1){
                    sum += img[i][j]+img[i-1][j];
                    if(j == 0){
                        sum += img[i][j+1]+img[i-1][j+1];
                        ans[i][j] = sum / 4;
                    }else if(j == w-1){
                        sum += img[i][j-1]+img[i-1][j-1];
                        ans[i][j] = sum / 4;
                    }else {
                        sum += img[i][j+1]+img[i-1][j+1]+img[i][j-1]+img[i-1][j-1];
                        ans[i][j] = sum / 6;
                    }
                }else {
                    sum += img[i][j]+img[i-1][j]+img[i+1][j];
                    if(j == 0) {
                        sum += img[i][j+1]+img[i-1][j+1]+img[i+1][j+1];
                        ans[i][j] = sum / 6;
                    }else {
                        sum += img[i][j-1]+img[i-1][j-1]+img[i+1][j-1];
                        ans[i][j] = sum / 6;
                    }
                }
            }
        }
        return ans;
    }
    /*
        给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
        示例 1：输入：
            3
           / \
          9  20
            /  \
           15   7   输出：[3,14.5,11]
        解释：第0层的平均值是3,第1层是14.5,第2层是11。因此返回[3,14.5,11] 。
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(root);
        List<Double> ans = new ArrayList<>();
        while (!q1.isEmpty() || !q2.isEmpty()){
            Double sum = 0.0;
            int count = 0;
            while (!q1.isEmpty()){
                TreeNode poll = q1.poll();
                count++;
                sum += poll.val;
                if(poll.left!=null){
                    q2.add(poll.left);
                }
                if(poll.right!=null){
                    q2.add(poll.right);
                }
            }
            ans.add(sum/count);
            sum = 0.0;
            count = 0;
            while (!q2.isEmpty()){
                TreeNode poll = q2.poll();
                count++;
                sum += poll.val;
                if(poll.left!=null){
                    q1.add(poll.left);
                }
                if(poll.right!=null){
                    q1.add(poll.right);
                }
            }
            if(count != 0){
                ans.add(sum/count);
            }
        }
        return ans;
    }
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map = new HashMap<>();
        Set<String> l = new HashSet<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i],i);
        }
        int minIndexSum = list1.length+list2.length;
        for (int i = 0; i < list2.length; i++) {
            if(map.containsKey(list2[i])){
                int index = map.get(list2[i]);
                if(index + i < minIndexSum){
                    l.clear();
                    l.add(list2[i]);
                    minIndexSum = index + i;
                }
                if(index + i == minIndexSum){
                    l.add(list2[i]);
                }
            }
        }
        String[] s = new String[l.size()];
        int count = 0;
        Iterator<String> iterator = l.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            s[count++] = next;

        }
        return s;
    }
    /*
        给定一个单词，你需要判断单词的大写使用是否正确。
        我们定义，在以下情况时，单词的大写用法是正确的：
        全部字母都是大写，比如"USA"。
        单词中所有字母都不是大写，比如"leetcode"。
        如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
        否则，我们定义这个单词没有正确使用大写字母。
        示例 1:   输入: "USA"   输出: True
        示例 2:   输入: "FlaG"  输出: False
     */
    public static boolean detectCapitalUse(String word) {
        int m = word.charAt(0) <= 'Z' ? 1 : 0;
        for (int i = 1; i < word.length(); i++) {
            if(i == 1){
                if( m == 1 && word.charAt(i) >= 'a'){
                    m = 0;
                }else if(m == 0 && word.charAt(i) <= 'Z'){
                    return false;
                }
            }else {
                if(m == 0 && word.charAt(i) <= 'Z'){
                    return false;
                }
                if( m == 1 && word.charAt(i) >= 'a'){
                    return false;
                }

            }
        }
        return true;
    }
    public static boolean findTarget(TreeNode root, int k) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> treeNodes = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        treeNodes.add(root);
        while(!treeNodes.isEmpty()){
            TreeNode poll = treeNodes.poll();
            int a = k - poll.val;
            if(set.contains(a)){
                return true;
            }else {
                set.add(poll.val);
            }
            if(poll.left!=null){
                treeNodes.add(poll.left);
            }
            if(poll.right!=null){
                treeNodes.add(poll.right);
            }
        }
        return false;
    }
    /*
        集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了
        成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
        给定一个数组 nums 代表了集合 S 发生错误后的结果。
        请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
        示例 1：输入：nums = [1,2,2,4]    输出：[2,3]
        示例 2：输入：nums = [1,1]        输出：[1,2]
     */
    public static int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int[] ints = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            if(ints[nums[i]] == 0){
                ints[nums[i]] =nums[i];
            }else {
                ans[0] = nums[i];
            }
        }
        for (int i = 1; i < ints.length; i++) {
            if(ints[i] == 0){
                ans[1] = i;
                break;
            }
        }
        return ans;
    }
    /*
        给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
        如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
        更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
        给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
        示例 1：输入：root = [2,2,5,null,null,5,7]输出：5    解释：最小的值是 2 ，第二小的值是 5 。
        示例 2：输入：root = [2,2,2]              输出：-1   解释：最小的值是 2, 但是不存在第二小的值。
     */
    public static int findSecondMinimumValue(TreeNode root) {
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        int count = 0;
        int min = root.val;
        int secoudMin = -1;
        while (!treeNodes.isEmpty()){
            TreeNode poll = treeNodes.poll();
            if(count == 0 && poll.val > min){
                secoudMin = poll.val;
                count++;
            }
            TreeNode left = poll.left;
            TreeNode right = poll.right;
            if(left != null){
                if(left.val > min && left.val<secoudMin){
                    secoudMin = left.val;
                }
                treeNodes.add(left);
            }
            if(right != null){
                if(right.val > min && right.val<secoudMin){
                    secoudMin = right.val;
                }
                treeNodes.add(right);
            }
        }
        return secoudMin;
    }
    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
    /*
        给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度和直系下属的id 。
        比如，员工1是员工2的领导,员工2是员工3的领导。他们相应的重要度为15,10,5。
        那么员工1的数据结构是[1,15,[2]]，员工2的数据结构是[2,10,[3]]，员工3的数据结构是[3,5,[]]。
        注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。
        现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
        示例：输入：[[1,5,[2,3]],[2,3,[]],[3,3,[]]], 1    输出：11
        解释：员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是5+3+3=11 。
     */
    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map = new HashMap<>();
        Employee e = null;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            map.put(employee.id,employee);
            if(employee.id == id){
                e = employee;
            }
        }
        Queue<Employee> queue = new LinkedList<>();
        queue.add(e);
        int sum = 0;
        while (!queue.isEmpty()){
            Employee poll = queue.poll();
            sum += poll.importance;
            map.remove(poll.id);
            List<Integer> subordinates = poll.subordinates;
            for (int i = 0; i < subordinates.size(); i++) {
                Employee employee = map.get(subordinates.get(i));
                if(employee != null){
                    queue.add(employee);
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
    }
}
