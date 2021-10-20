import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-27 17:58
 */
public class ZhongDengSuanFa1 {
    static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /*
        格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
        给定一个代表编码总位数的非负整数n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
        格雷编码序列必须以 0 开头。
        示例 1:输入: 2  输出: [0,1,3,2]
        解释:00 - 0   01 - 1  11 - 3  10 - 2
        对于给定的 n，其格雷编码序列并不唯一。
        例如，[0,2,3,1] 也是一个有效的格雷编码序列。00 - 0   10 - 2  11 - 3  01 - 1
        示例 2:输入: 0  输出: [0]
        解释: 我们定义格雷编码序列必须以0开头。给定编码总位数为n的格雷编码序列，
        其长度为2n。当n=0时，长度为1<<0=1。因此，当n=0时，其格雷编码序列为[0]。
     */
    public static List<Integer> grayCode(int n) {
        if(n == 0){
            List<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        List<Integer> list = grayCode(n - 1);
        int m = 2 << n - 1;
        for (int i = list.size() - 1; i >= 0 ; i--) {
            Integer integer = list.get(i);
            integer += m;
            list.add(integer);
        }
        return list;
    }
    /*
        给定两个字符串s和p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
        异位词 指字母相同，但排列不同的字符串。
        示例 1:   输入:s="cbaebabacd", p="abc"     输出:[0,6]
        解释:起始索引等于0的子串是"cba", 它是"abc"的异位词。起始索引等于6的子串是"bac", 它是"abc"的异位词。
        示例 2:输入: s="abab", p="ab"       输出:[0,1,2]
        解释:起始索引等于0的子串是"ab", 它是"ab"的异位词。起始索引等于1的子串是"ba",它是"ab"的异位词。
        起始索引等于2的子串是"ab",它是"ab"的异位词。
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(s.length() < p.length()){
            return list;
        }
        int length = p.length();
        int[] sInt = new int[length];
        int[] pInt = new int[length];
        for (int i = 0; i < p.length(); i++) {
            ++sInt[s.charAt(i) - 'a'];
            ++pInt[p.charAt(i) - 'a'];
        }
        if(Arrays.equals(sInt,pInt)){
            list.add(0);
        }
        for (int i = length; i < s.length(); i++) {
            ++sInt[s.charAt(i) - 'a'];
            --sInt[s.charAt(i-length) - 'a'];
            if(Arrays.equals(sInt,pInt)){
                list.add(0);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "abc";
        StringBuffer stringBuffer = new StringBuffer("abc");
        System.out.println(s.equals(stringBuffer));

    }
}
