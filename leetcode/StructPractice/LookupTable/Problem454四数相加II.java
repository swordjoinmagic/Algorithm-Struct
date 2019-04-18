package leetcode.StructPractice.LookupTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 *      1. 暴力，同时遍历四个数组，时间复杂度O(n4)
 *      2. 使用Map对最后一层数组进行记录，时间复杂度O(n3)
 *      3. 参考大神。
 *      使用map记录A、B数组的两两之和(key)与其出现次数(value)。
 *      遍历C、D数组，在map中查找是否有value == -(C[i]+D[j])
 *      时间复杂度O(n2)
 *
 *
 */
public class Problem454四数相加II {

    public static void main(String[] args){
        int[] A = new int[]{1, 2};
        int[] B = new int[]{-2,-1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{0, 2};
        int result = new Problem454四数相加II().fourSumCount(A,B,C,D);
        System.out.println(result);
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> map = new HashMap<>();

        // 记录A、B数组的两两之和
        for(int a : A){
            for(int b : B){
                int total = a+b;
                map.put(total,map.getOrDefault(total,0)+1);
            }
        }

        // 四元组数量
        int result = 0;

        // 遍历C、D数组
        for(int c : C){
            for(int d : D){
                int value = -(c+d);
                if(map.containsKey(value)){
                    result += map.get(value);
                }
            }
        }

        return result;
    }
}
