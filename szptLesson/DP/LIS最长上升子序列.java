package szptLesson.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS最长上升子序列 {

    public static void main(String[] args){
        LIS最长上升子序列 lis最长上升子序列 = new LIS最长上升子序列();
        int result = lis最长上升子序列.LIS2(new int[]{65,158,170,299,300,155,207,389},6);
        System.out.println(result);
    }

    public int LIS1(int[] nums){
        // f[i]为以i下标为结束位置的最长不降子序列
        int[] f = new int[nums.length+1];
        Arrays.fill(f,1);

        int max = 0;

        for(int i=1;i<nums.length;i++){

            for(int j=0;j<i;j++){
                if(nums[j]<nums[i])
                    f[i] = Math.max(f[i],f[j]+1);
                max = Math.max(max, f[i]);
            }

        }
        return max;
    }

    /**
     * LIS变体1，要求包含第k个元素的最长上升子序列
     * @param nums
     * @param k
     * @return
     */
    public int LIS2(int[] nums,int k){

        k -= 1;

        // 设f[i]为下标i为结束位置的最长上升子序列
        int[] f = new int[nums.length];

        Arrays.fill(f,1);

        // 包含第k个元素的最长上升子序列的长度
        int max = 0;

        // 先算到位置k处
        for(int i=1;i<=k;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    f[i] = Math.max(f[i],f[j]+1);
                }
            }
        }

        max = f[k];

        List<Integer> list = new ArrayList<>();
        list.add(k);
        for(int i=k+1;i<nums.length;i++){
            int len = list.size();
            for(int a=0;a<len;a+=1) {
                int j = list.get(a);
                if(nums[j]<nums[i]){
                    f[i] = Math.max(f[i],f[j]+1);
                    list.add(i);
                    max = Math.max(max,f[i]);
                }
            }
        }

        for(int data : f){
            System.out.print(data+"  ");
        }
        System.out.println();

        return max;
    }
}
