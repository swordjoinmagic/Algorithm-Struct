package leetcode.IntermediateAlgorithm.array;

import java.util.*;

/**
 * 思路:
 *      1. 暴力,遍历两遍获得a和b,c=0-a-b,复杂度O(N2*(用来查重的复杂度))
 *      2. 先排序,然后定义三指针i,j,k,遍历i,问题转化为对i后面的
 *      数组进行二数求和(LeetCode-1),这里可以用双指针解法来求得j/k,
 *      双指针解法复杂度O(N),遍历i复杂度O(N),总时间复杂度O(N2)
 */
public class Problem15三数之和 {

    public static void main(String[] args){
        Problem15三数之和 problem15 = new Problem15三数之和();
        List<List<Integer>> lists = problem15.threeSum(new int[]{-1, 0, 1, 2, -1, -4});

        for(List<Integer> l : lists){
            for(int data : l){
                System.out.print(data+" ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // 先排序
        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){

            // 跳过重复
            if(i>0 && nums[i]==nums[i-1]) continue;

            int need = -nums[i];

            // 双指针求nums[j]+nums[k] = -nums[i]
            int j = i+1;
            int k = nums.length-1;

            while (j<k){
                int n = nums[j]+nums[k];
//                System.out.println("i:"+i+" j:"+j+" k:"+k+" nums[j]:"+nums[j]+" nums[k]:"+nums[k]+"  n:"+n+" need:"+need);
                if(n>need){
                    // 跳过重复
                    while (j<k && nums[k]==nums[k-1]) k--;

                    k--;
                }else if(n<need){

                    // 跳过重复
                    while (j<k && nums[j]==nums[j+1]) j++;

                    j++;
                }else {
                    // 相等的情况,得到一个三元组i,j,k之和为0
                    result.add(Arrays.asList(nums[i],nums[j],nums[k]));

                    while (j<k && nums[k]==nums[k-1]) k--;
                    while (j<k && nums[j]==nums[j+1]) j++;

                    k--;
                    j++;
                }
            }
        }

        return result;
    }

    /*
    //======================================================
    // 结论:暴力不可取

     * 暴力解法
    public List<List<Integer>> ViolentSolution(int[] nums){
        Set<Integer> set = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();

        for(int data:nums) set.add(data);

        for(int i=0;i<nums.length;i++){
            int a = nums[i];
            for(int j=i+1;j<nums.length;j++){
                int b = nums[j];
                int c = -a-b;
                if(set.contains(c)){

                    List<Integer> newlist = Arrays.asList(a,b,c);

                    // 三元组为a,b,c
                    // 对三元组进行判重
                    if(!isRepate(a,b,c, result)) {
                        System.out.println("a:"+a+" b:"+b+" c:"+c);
                        result.add(newlist);
                    }
                }
            }
        }

        return result;
    }

     * 判重
    public boolean isRepate(int a,int b,int c,List<List<Integer>>result){

        boolean isFindA = false;
        boolean isFindB = false;
        boolean isFindC = false;

        for(List<Integer> l : result){
            for(int data : l){
                if(data==a)
                    isFindA = true;
                if(data==b)
                    isFindB = true;
                if(data==c)
                    isFindC = true;
            }
            if(isFindA&&isFindB&&isFindC)
                return true;
            else {
                isFindA = false;
                isFindB = false;
                isFindC = false;
            }
        }
        return false;
    }
    //-------------------------------------------------------

    */
}
