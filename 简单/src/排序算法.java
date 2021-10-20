import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author shkstart
 * @create 2021-10-13 20:57
 */
public class 排序算法 {

    /**
     * 冒泡排序
     * @param nums
     */
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

    /**
     * 插入排序
     * @param nums
     */
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

    /**
     * 桶排序
     * @param nums
     */
    public static void TongPai(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            lists.add(list);
        }
        int max =nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > max){
                max = nums[i];
            }
        }
        int i = 0;
        while (max / 10 > 0){
            i++;
            max = max / 10;
        }
        int p = 1;
        for (; i >= 0 ; i--) {
            for (int j = 0; j < nums.length; j++) {
                int i1 = (nums[j] / p) % 10;
                lists.get(i1).add(nums[j]);
            }
            int q = 0;
            for (int j = 0; j < 10; j++) {
                List<Integer> list = lists.get(j);
                for (int k = 0; k < list.size(); k++) {
                     nums[q++] = list.get(k);
                }
                list.clear();
            }
            p *= 10;
        }
    }

    /**
     * 归并排序
     * @param nums
     */
    public static void GuiBing(int[] nums){
        GuiBing1(nums,0,nums.length-1);
    }

    /**
     * 归并排序辅助方法1
     * @param nums          需要排序的数组
     * @param startIndex    需要排序的数组的开始索引
     * @param endIndex      需要排序的数组的结束索引
     */
    public static void GuiBing1(int[] nums,int startIndex,int endIndex){
        if(startIndex == endIndex){
            return;
        }
        int mid = startIndex + (endIndex-startIndex)/2;
        if (startIndex < endIndex){
            GuiBing1(nums,startIndex,mid);
            GuiBing1(nums,mid+1,endIndex);
            GuiBing2(nums,startIndex,mid,endIndex);
        }
    }

    /**
     * 归并排序辅助方法2
     * @param nums          需要排序的数组
     * @param startIndex    需要排序的数组第一个区域的起始索引
     * @param midIndex      需要排序的数组的第一个区域的结束索引
     * @param endIndex      需要排序的数组的第二个结束索引
     */
    public static void GuiBing2(int[] nums,int startIndex,int midIndex,int endIndex){
        int start1 = startIndex;
        int start2 = midIndex+1;
        int index = 0;
        int[] ints = new int[endIndex-startIndex+1];
        while (start1 < midIndex + 1 || start2 < endIndex + 1){
            if(start1 == midIndex + 1){
                for (int i = start2; i < endIndex + 1; i++) {
                    ints[index++] = nums[start2++];
                }
                break;
            }
            if(start2 == endIndex + 1){
                for (int i = start1; i < midIndex + 1; i++) {
                    ints[index++] = nums[start1++];
                }
                break;
            }
            if(nums[start1] > nums[start2])
                ints[index++] = nums[start2++];
            else{
                ints[index++] = nums[start1++];
            }
        }
        for (int i = 0; i < ints.length; i++) {
            nums[startIndex+i] = ints[i];
        }
    }
    public static void main(String[] args) {
        int[] ints = new int[20000000];
        for (int i = 0; i < ints.length; i++) {
            int v = (int)(Math.random()*20000000);
            ints[i] = v;
        }
        long l = System.currentTimeMillis();
        TongPai(ints);
        System.out.println(System.currentTimeMillis()-l);
    }
}
