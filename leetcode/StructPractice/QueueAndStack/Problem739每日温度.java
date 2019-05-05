package leetcode.StructPractice.QueueAndStack;

import java.util.Arrays;
import java.util.Stack;


/**
 * 思路：
 *      1. 暴力。时间复杂度O(n2)
 *      2. 从后往前，利用已有信息（即下一个温度需要多久温度才会升高天数）剪枝。
 *      最坏情况时间复杂度O(n2)
 *      3. 参考评论区大佬。
 *      维护递减栈，后入栈的元素总比栈顶元素小。
 *      比对当前元素与栈顶元素的大小
 *          若当前元素 < 栈顶元素：入栈
 *          若当前元素 > 栈顶元素：弹出栈顶元素，记录两者下标差值即为所求天数
 *
 */
public class Problem739每日温度 {

    public static void main(String[] args){
        int[] t = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = new Problem739每日温度().dailyTemperatures(t);
        for(int i:result) System.out.print(i+" ");
    }

    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<length;i++){
            if(!stack.isEmpty()){
                // 维护递减栈
                // 当栈顶元素小于当前元素时,
                // 弹出栈顶,计算两个元素之间的下标差
                while (!stack.isEmpty() && T[stack.peek()]<T[i]){
                    result[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
            }
            stack.add(i);
        }

        return result;
    }
}
