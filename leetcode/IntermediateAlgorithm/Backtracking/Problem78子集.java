package leetcode.IntermediateAlgorithm.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 思路:
 *      1. 问题可以转化为从长度为n的数组中,求其x个元素的组合
 */
public class Problem78子集 {

    public static void main(String[] args){
        Problem78子集 problem78 = new Problem78子集();
        List<List<Integer>> lists = problem78.subsets(new int[]{4,1,0});
        System.out.println(lists);
    }

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        for(int i=1;i<=nums.length;i++){
            int[] result = new int[i];
            GetCombinate(0,nums,result,i);
        }
        lists.add(new ArrayList<>());
        return lists;
    }

    private List<List<Integer>> lists = new ArrayList<>();
    boolean[] visited;
    /**
     * 从nums数组中,取length个数出来求组合
     * @param nums
     * @param result
     * @param length
     */
    public void GetCombinate(int pos,int[] nums,int[] result,int length){

        // 递归的临界条件
        if(pos == length){

            List<Integer> list = new ArrayList<>(length);
            for(int data : result) list.add(data);
            lists.add(list);
            return;
        }

        for(int i=pos;i<nums.length;i++){
            if(!visited[i]){
                result[pos] = nums[i];
                visited[i] = true;
                if(pos==0 || result[pos] > result[pos-1])
                    // 填下一个数
                    GetCombinate(pos+1,nums,result,length);
                visited[i] = false;
            }
        }
    }
}
