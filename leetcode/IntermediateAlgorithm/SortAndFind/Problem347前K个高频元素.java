package leetcode.IntermediateAlgorithm.SortAndFind;

import java.util.*;

/**
 * 思路:
 *      1. 遍历一遍数组,记录数组各元素使用频率
 */
public class Problem347前K个高频元素 {

    public static void main(String[] args){
        Problem347前K个高频元素 problem347 = new Problem347前K个高频元素();
        List<Integer> list = problem347.topKFrequent(new int[]{1,1,1,2,2,3},2);
        System.out.println(list);
    }

    class Number{
        int number;
        int frequency;

        public Number(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if(nums.length==0) return result;
        if(nums.length==1) { result.add(nums[0]);return result; }

        Map<Integer,Integer>map = new HashMap<>();

        for(int data : nums){
            map.put(data,map.getOrDefault(data,0)+1);
        }


        Queue<Number>queue = new PriorityQueue<>(new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                // 从大到小排序
                if(o1.frequency>o2.frequency)
                    return -1;
                else if(o1.frequency<o2.frequency)
                    return 1;
                else
                    return 0;
            }
        });

        for(int key : map.keySet()){
            queue.add(new Number(key,map.get(key)));
        }

        for(int i=0;i<k;i++){
            result.add(queue.poll().number);
        }

        return result;
    }
}
