package leetcode.IntermediateAlgorithm.Backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 思路:
 *      1. 填坑法生成全排列,生成一个具有N个坑的数组,
 *      每次递归地向里面填之前没有填过的数,即可生成全排列
 */
public class Problem46全排列 {

    public static void main(String[] args){
        Problem46全排列 problem46 = new Problem46全排列();
        List<List<Integer>> l = problem46.permute(new int[]{1,2,3});
        System.out.println(l);
    }

    public List<List<Integer>> permute(int[] nums) {
        int[] result = new int[nums.length];
        lists = new ArrayList<>();
        visited = new boolean[nums.length];
        GetPermutation(0,nums,result);
        return lists;
    }

    private List<List<Integer>> lists;

    // 表示下标为i的数是否访问过
    private boolean[] visited;

    /**
     *
     * @param pos 当前要填的坑的下标
     * @param nums 要从这个数组里面选数进行全排列
     * @param result 结果数组,即要填坑的数组
     */
    public void GetPermutation(int pos,int[] nums,int[] result){

        // 临界条件,表示坑已经填完了
        if(pos==nums.length){
            List<Integer> list = new ArrayList<>();
            for(int data : result){
                list.add(data);
            }
            lists.add(list);
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!visited[i]){

                // 标记已访问
                visited[i] = true;

                result[pos] = nums[i];
                // 填下一个坑
                GetPermutation(pos+1,nums,result);

                // 取消标记
                visited[i] = false;
            }
        }
    }
}
