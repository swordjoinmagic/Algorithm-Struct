package leetcode.StructPractice.QueueAndStack;

import java.util.Stack;

/**
 * 思路：
 *      1. 用双栈来实现队列。
 *      一个入队栈用来入队，一个出队栈用于出队。
 *      当元素入队时，进入 入队栈。
 *      当元素出队时，判断出队栈是否为空，
 *          a.为空时，将入队栈顺序出栈push进出队栈
 *          b.不为空时，直接出栈出队栈的元素
 *      当入队栈和出队栈均为空时，判断该队列为空
 */
public class Problem232用栈实现队列 {

    // 入队栈
    Stack<Integer> inStack;
    // 出队栈
    Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public void MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(outStack.empty()){
            while (!inStack.empty())
                outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(outStack.empty()){
            while (!inStack.empty())
                outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return outStack.empty() && inStack.empty();
    }
}
