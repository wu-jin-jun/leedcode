import java.util.*;

/**
 * @author shkstart
 * @create 2021-09-25 10:13
 */
public class JianDanSuanFa3 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public static TreeNode searchBST(TreeNode root, int val) {
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()){
            TreeNode poll = treeNodes.poll();
            if(poll.val == val){
                return poll;
            }
            if(poll.val > val && poll.left != null){
                treeNodes.add(poll.left);
            }else if(poll.val < val && poll.right != null){
                treeNodes.add(poll.right);
            }
        }
        return null;
    }
    public static int maxProfit(int[] prices) {
        int min= prices[0];
        for(int i = 0 ;i < prices.length;i++){
            if(prices[i] < min){
                min = prices[i];
                prices[i] = 0;
            }else{
                prices[i] -= min;
            }
        }
        int max = 0;
        for(int i = 0 ;i < prices.length;i++){
            if(prices[i] > max){
                max = prices[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String b = "abcaaabcaafaaabaaagabaefgaaaadefgabcdefgh";
        String d = "a" + "b" + "c" ;
        String e = b;
        String concat = b.concat(d);
        System.out.println(1^2^3^4);
    }
}
