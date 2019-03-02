package leetcode.PrimaryAlgorithm.array;

import java.util.HashMap;
import java.util.Map;

public class Problem217 {

    public static void main(String[] args){

        int[] nums = new int[]{1,2,3,4};
        boolean a =new Problem217().containsDuplicate(nums);
        System.out.println(a);
    }

    public boolean containsDuplicate(int[] nums) {

        Map<Integer,Integer>map = new HashMap<Integer,Integer>();

        for(int i=0;i<nums.length;i++){
            if(map.getOrDefault(nums[i],0)!=0){
                return true;
            }
            map.put(nums[i],1);
        }
        return false;
    }
}
