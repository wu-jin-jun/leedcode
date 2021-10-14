import java.util.*;

/**
 * @author shkstart
 * @create 2021-09-27 17:09
 */
public class KunNanSuanFa1 {
    /**
        n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击。
        给你一个整数n，返回所有不同的n皇后问题的解决方案。
        每一种解法包含一个不同的n皇后问题的棋子放置方案，该方案中'Q'和'.'分别代表了皇后和空位。
        示例 1：输入：n = 4   输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        解释：如上图所示，4 皇后问题存在两个不同的解法。
        示例 2：输入：n = 1   输出：[["Q"]]
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> lists= new ArrayList<>();    //用于返回的集合
        Set<Integer> set1 = new HashSet<>();            //用于存放行与列的差(后面用于判断第i行j列是否可以放入集合中)
        Set<Integer> set2 = new HashSet<>();            //用于存放行与列的和(后面用于判断第i行j列是否可以放入集合中)
        List<String> list = new ArrayList<>();          //用于存放满足条件的集合
        int[] ints = new int[n];                        //用于存放第j列是否被使用过(后面用于判断第i行j列是否可以放入集合中)
        solveNQueens(lists,ints,n,0,set1,set2,list);
        return lists;
    }
    /**
     * N皇后核心代码
     * @author 吴金俊
     * @param lists
     * @param ints
     * @param n
     * @param h
     * @param set1
     * @param set2
     * @param list
     */
    public static void solveNQueens(List<List<String>> lists,int[] ints,
                                    int n,int h,Set<Integer> set1,Set<Integer> set2,List<String> list) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < n; i++) {
            if(ints[i] == 0 && isAble(h,i,set1,set2)){
                s.append('Q');
                ints[i] = 1;
                set1.add(h-i);
                set2.add(h+i);
                for (int j = i + 1; j < n; j++) {
                    s.append(".");
                }
                list.add(s.toString());
                if(h == n-1){
                    List<String> l = new ArrayList<>();
                    l.addAll(list);
                    lists.add(l);
                    list.remove(s);
                }else {
                    solveNQueens(lists,ints,n,h+1,set1,set2,list);
                }
                list.remove(s.toString());
                set1.remove(h-i);
                set2.remove(h+i);
                ints[i] = 0;
                s.delete(i,n);
            }
            s.append('.');
        }
    }
    /**
     * 用于判断N皇后中第i行j列是否可以放置皇后
     * @author 吴金俊
     * @param m
     * @param n
     * @param set1
     * @param set2
     * @return
     */
    public static Boolean isAble(int m,int n,Set<Integer> set1,Set<Integer> set2){
        return !set1.contains(m-n) && !set2.contains(m+n);
    }
    /*
    给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
    k是一个正整数，它的值小于或等于链表的长度。
    如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
    进阶：你可以设计一个只使用常数额外空间的算法来解决此问题吗？
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    示例 1：输入：head = [1,2,3,4,5], k = 2   输出：[2,1,4,3,5]
    示例 2：输入：head = [1,2,3,4,5], k = 3   输出：[3,2,1,4,5]
    示例 3：输入：head = [1,2,3,4,5], k = 1   输出：[1,2,3,4,5]
    示例 4：输入：head = [1], k = 1           输出：[1]
 */
    public static ZhongDengSuanFa1.ListNode reverseKGroup(ZhongDengSuanFa1.ListNode head, int k) {
        ZhongDengSuanFa1.ListNode ans = new ZhongDengSuanFa1.ListNode(0);
        ans.next = head;
        ZhongDengSuanFa1.ListNode pre = ans;
        ZhongDengSuanFa1.ListNode slo = head;
        ZhongDengSuanFa1.ListNode fast = head;
        int m = k;
        while (--k > 0 && (fast = fast.next) != null);
        ZhongDengSuanFa1.ListNode last = fast;
        while (fast != null){
            int n = 0;
            ZhongDengSuanFa1.ListNode l = slo;
            pre.next = fast;
            pre = slo;
            while(n++ < m){
                if (n == 1) {
                    slo = slo.next;
                    fast = fast == null ? null : fast.next;
                    last = fast == null ? null : fast;
                }else {
                    ZhongDengSuanFa1.ListNode next = slo.next;
                    slo.next = l;
                    l = slo;
                    slo = next;
                    fast = fast == null ? null : fast.next;
                    last = fast == null ? last : fast;
                }

            }
        }
        pre.next = fast == null ? slo : last;
        return ans.next;
    }

    /**
     * 编写一个程序，通过填充空格来解决数独问题。数独的解法需遵循如下规则：数字1-9在每一行只能出现一次。数字1-9在每一列只能出现一
     * 次。数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。(请参考示例图）数独部分空格内已填入了数字，空白格用'.'表示。
     * 示例：
     * 输入：board = ["5","3",".",".","7",".",".",".","."]    输出：["5","3","4","6","7","8","9","1","2"]
     *              ["6",".",".","1","9","5",".",".","."]          ["6","7","2","1","9","5","3","4","8"]
     *              [".","9","8",".",".",".",".","6","."]          ["1","9","8","3","4","2","5","6","7"]
     *              ["8",".",".",".","6",".",".",".","3"]          ["8","5","9","7","6","1","4","2","3"]
     *              ["4",".",".","8",".","3",".",".","1"]          ["4","2","6","8","5","3","7","9","1"]
     *              ["7",".",".",".","2",".",".",".","6"]          ["7","1","3","9","2","4","8","5","6"]
     *              [".","6",".",".",".",".","2","8","."]          ["9","6","1","5","3","7","2","8","4"]
     *              [".",".",".","4","1","9",".",".","5"]          ["2","8","7","4","1","9","6","3","5"]
     *              [".",".",".",".","8",".",".","7","9"]          ["3","4","5","2","8","6","1","7","9"]
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        int l1 = board.length;
        int l2 = board[0].length;
        int[][] h = new int[l1][l2];        // 第l1行有没有数字l2+1
        int[][] l = new int[l1][l2];        // 第l1列有没有数字l2+1
        int[][] j = new int[l1][l2];        // 第l1个矩阵有没有数字l2+1
        int[][] ints = new int[l1][l2];     // 用来记录第l1行，l2列是否填充数字(填充用1表示，原始数据用2表示，没填充用0表示)
        int count = 0;
        int total = l1 * l2;
        for (int i = 0; i < l1; i++) {
            for (int k = 0; k < l2; k++) {
                char c = board[i][k];
                if(c != '.'){
                    int m = c - '1';
                    h[i][m] = 1;
                    l[k][m] = 1;
                    j[(i/3)*3+k/3][m] = 1;
                    ints[i][k] = 2;
                }
            }
        }
        solveSudokuAdd(board,ints,h,l,j,count,total);
    }

    /**
     * @author 吴金俊
     * @param board     需要填充的数独数组
     * @param ints      记录数组中对应位置是否存在原始数据
     * @param h         记录数组第i行是否出现数字j+1(0代表没出现，1代表出现)
     * @param l         记录数组第i列是否出现数字j+1(0代表没出现，1代表出现)
     * @param j         记录数组第i个矩阵是否出现数字j+1(0代表没出现，1代表出现)
     * @param count     记录当前要填充的是第几个数字
     * @param total     记录一共要填充的数字
     * @return
     */
    public static Boolean solveSudokuAdd(char[][] board,int[][] ints,int[][] h,
                                      int[][] l,int[][] j, int count,int total) {
        int z = count;
        while(z < total && CountIsExit(ints,z)){
            z++;
        }
        int m = z / 9;
        int n = z % 9;
        if(z == total){
            return true;
        }
        /*
                '5' '3' '.' '.' '7' '.' '.' '.' '.'
                '6' '.' '.' '1' '9' '5' '.' '.' '.'
                '.' '9' '8' '.' '.' '.' '.' '6' '.'
                '8' '.' '.' '.' '6' '.' '.' '.' '3'
                '4' '.' '.' '8' '.' '3' '.' '.' '1'
                '7' '.' '.' '.' '2' '.' '.' '.' '6'
                '.' '6' '.' '.' '.' '.' '2' '8' '.'
                '.' '.' '.' '4' '1' '9' '.' '.' '5'
                '.' '.' '.' '.' '8' '.' '.' '7' '9'
         */
        int i;
        int q;
        for ( i = 1; i < 10; i++) {
            Boolean b = true;
            if (isAbleFill(h, l, j, z, i)) {
                h[m][i - 1] = 1;
                l[n][i - 1] = 1;
                j[(m / 3) * 3 + n / 3][i - 1] = 1;
                board[m][n] = (char)(i + '0');
                if(z == total - 1){
                    return true;
                }else {
                    q = z;
                    while (++q < total && CountIsExit(ints,q));
                }
                if(q == total){
                    return true;
                }
                b = solveSudokuAdd(board,ints,h,l,j,q,total);
            }else {
                continue;
            }
            if(!b){
                h[m][i - 1] = 0;
                l[n][i - 1] = 0;
                j[(m / 3) * 3 + n / 3][i - 1] = 0;
                board[m][n] = '.';
            }else {
                return b;
            }
        }
        return i == 10 ? false : true;
    }
    /**
     * 数独辅助方法，用于判断当前位置(count)是否可以填充数字i;
     */
    public static Boolean isAbleFill(int[][] h, int[][] l,int[][] j, int count,int i){
        int m = count / 9;
        int n = count % 9;
        return h[m][i-1] == 0 && l[n][i-1] == 0 && j[(m/3)*3+n/3][i-1] == 0;
    }

    /**
     * 数独辅助方法，用于判断当前位置是否有原始数据
     * @param ints  记录对应位置上是否有
     * @param count 当前要填充的位置
     * @return
     */
    public static Boolean CountIsExit(int[][] ints,int count){
        int m = count / 9;
        int n = count % 9;
        return ints[m][n] == 2;
    }

    public static int reversePairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put((nums[0]-1)/2,1);
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int m = (num-1)/2;
            if(num<=0){
                m = num/2-1;
            }
            Set<Integer> set = map.keySet();
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()){
                Integer next = iterator.next();
                if(nums[i] <= next){
                    sum += map.get(next);
                }
            }
            if(!map.containsKey(m)){
                map.put(m,1);
            }else {
                map.put(m,map.get(m)+1);
            }
        }
        return sum;
    }
    /**
     * 分析:要判断阶乘结果后面有多少个0，就需要知道可以分解出多少个5出来，例如(1)10！:可以把它分解成2个5，其中一个本身是5，另一个
     * 把10分解成5*2，这里有多一个5;(2)如果是25！:可以把它分解成6个5，因为25可以分解成5*5;(3)如果是125！:125可以分解成5*5*5;
     * 通过观察可以知道每隔5个数就可以分解出1个5,每隔25个数就可以多分解出1个5,每隔125个数又可以多分解出1个5来.分析可以知道,假设25,
     * 125,625等这些5的指数幂只能分解成1个5,那么每隔5个数就可以使得结果后面多1个0,这样为使最终达到有k个0就往后面取5k个数就行了。
     * 然而25,125,625等这些5的指数幂打破了这种平衡导致中间出现断层，例如(5!有1个0  10!有2个0  15!有3个0  5!有4个0  25!有6个0)
     * 不存在有5个0的情况(从这里就可以看出答案只能是0或者5),后面就是数学推理方式了,想办法把那些不可能出现的(0的个数)找出来,剩下的
     * 就都可以通过了,并且达到目标个数的0的答案值能是5个刚好连在一起的数的阶乘,应为每隔5个数0的个数就会至少增加1个。
     * @author 吴金俊
     * @param k
     * @return
     */
    /*
        f(x)是x!末尾是0的数量。(回想一下x!=1*2*3*...*x,且0!=1)
        例如，f(3)=0,因为3!=6的末尾没有0；而f(11)=2,因为11!=39916800末端有2个0.给定K,找出多少个非负整数x,能满足f(x)=K。
        示例1：输入：K = 0    输出：5    解释：0!, 1!, 2!, 3!, and4!均符合 K = 0 的条件。
        示例2：输入：K = 5    输出：0    解释：没有匹配到这样的x!,符合K=5的条件。
     */
    public static int preimageSizeFZF(int k) {
        int m = 1;
        while (k / 5 >= m ){
            m = m * 5 + 1;
        }
        while (m >= 1){
            if(k > m){
                k = k % m;
            }
            if(k == m - 1){
                return 0;
            }
            m = (m-1)/5;
        }
        return 5;
    }
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if(sx == tx && sy == ty){
            return true;
        }
        int sum = sx + sy;
        if(sum > tx && sum > ty ){
            return false;
        }else if((sum == tx && sy == ty) || (sum == ty && sx == tx)){
            return true;
        }else {
            return reachingPoints(sum,sy,tx,ty) || reachingPoints(sx,sum,tx,ty);
        }
    }
    public static int shortestPathLength(int[][] graph) {
        Set<Integer> set = new HashSet<>();
        int sum = graph.length-1;
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            if(graph[i].length == 1){
                if(count >= 2){
                    sum++;
                } else if(set.contains(graph[i][0])){
                    sum++;
                }else {
                    set.add(graph[i][0]);
                }
                count++;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(reachingPoints(1,1,3,5));
    }
}
