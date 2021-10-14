import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author shkstart
 * @create 2021-10-13 20:57
 */
public class 排序算法 {
    public static void MaoPao(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if(nums[j] < nums[j-1]){
                    int num = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = num;
                }
            }
        }
    }

    public static void ChaRu(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(nums[j] < nums[j-1]){
                    int num = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = num;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = 0; i < ints.length; i++) {
            double random = Math.random();
            int m = (int)(random * 99 + 1);
            ints[i] = m;
        }
        ChaRu(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            System.out.print(" ");
        }
    }
}
