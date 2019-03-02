package leetcode.PrimaryAlgorithm.array;

import java.util.HashMap;
import java.util.Map;

/*
    两数之和
 */
public class Problem1两数之和 {

    public static void main(String[] args){
        int[] n = {4,2,2,3,4};
        int[] result = new Problem1两数之和().slove2(n,8);
        for(int num : result){
            System.out.print(num+" ");
        }
    }

    public int[] slove2(int[] nums,int target){
        Map<Integer,Integer>map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int diff = target-nums[i];
            int j = map.getOrDefault(diff,-1);
            if(j != -1 && j!=i){
                int[] r = {i,j};
                return r;
            }
        }

        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            int result = target-nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]==result){
                    int[] r = {i,j};
                    return r;
                }
            }
        }
        return null;
    }
}
