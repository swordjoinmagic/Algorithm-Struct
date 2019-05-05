package leetcode.StructPractice.QueueAndStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 思路：
 *      1. 每次入栈两个元素，
 *      第一个元素是要入栈的数，
 *      第二个元素是入栈该数后，栈目前的最小值
 *
 *      此时查询栈内最小值的复杂度为O(1)
 */
public class Problem155最小栈 {

    public static void main(String[] args){
        Problem155最小栈 minStack  = new Problem155最小栈();

        minStack.MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();   //--> 返回 -3.
        System.out.println(min);
        minStack.pop();
        int top = minStack.top();     // --> 返回 0.
        System.out.println(top);
        min = minStack.getMin();   //--> 返回 -2.
        System.out.println(min);
    }

    Stack<Integer> stack;

    /** initialize your data structure here. */
    public void MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {

        if(stack.isEmpty()){
            stack.push(x);
            stack.push(x);
        }else {
            // 目前栈维护的最小值
            int tempMin = getMin();
            stack.push(x);
            stack.push(x < tempMin ? x : tempMin);
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        return stack.get(stack.size()-2);
    }

    public int getMin() {
        return stack.peek();
    }
}
