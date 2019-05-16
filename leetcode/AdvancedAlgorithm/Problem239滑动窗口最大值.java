package leetcode.AdvancedAlgorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 思路:
 *      1. 单调队列模板题.
 *      维护一个单调队列,当遍历的i大于等于k时,每次都输出一个数字
 */
public class Problem239滑动窗口最大值 {

    public static void main(String[] args){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int[] result = new Problem239滑动窗口最大值().maxSlidingWindow(nums,3);
        for(int i:result) System.out.print(i+" ");
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        // 维护单调递减队列,则队首就是该区间最大值
        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> indexDeque = new LinkedList<>();

        for(int i=0;i<nums.length;i++){
            int data = nums[i];

            // 如果当前的元素 大于 单调队列的队尾
            // 那么队尾出队,直到当前元素 小于等于 单调队列的队尾
            while (!deque.isEmpty() && data>deque.peekLast()){
                deque.pollLast();
                indexDeque.pollLast();
            }
            // 如果当前元素 小于 单调队列的队尾
            // 那么将该元素 加入 单调队列队尾,形成单调递减队列
            deque.add(data);
            indexDeque.add(i);

            // 将不符合当前区间的元素都去除
            while (indexDeque.peekFirst()<=i-k){
                deque.pollFirst();
                indexDeque.pollFirst();
            }

            if(i>=k-1)
                // 队首是最大值
                result.add(deque.peekFirst());
        }

        int[] r = new int[result.size()];
        for(int i=0;i<result.size();i++)
            r[i] = result.get(i);

        return r;
    }
}
