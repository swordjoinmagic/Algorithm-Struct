package leetcode.StructPractice.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 思路：
 *      1. 思路和232类似。用双队列实现栈。
 *
 *      每次要出栈的时候，将队列1的除最后一个元素为全部
 *      push到队列2，
 *      这最后一个元素就是要出栈的元素
 */
public class Problem225用队列实现栈 {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public void MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(queue2.isEmpty())
            queue1.add(x);
        else
            queue2.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(!queue1.isEmpty()){

            while (queue1.size()>1){
                queue2.add(queue1.poll());
            }

            return queue1.poll();
        }else {

            while (queue2.size()>1){
                queue1.add(queue2.poll());
            }

            return queue2.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        if(!queue1.isEmpty()){

            while (queue1.size()>1){
                queue2.add(queue1.poll());
            }

            int top = queue1.peek();

            queue2.add(queue1.poll());

            return top;
        }else {

            while (queue2.size()>1){
                queue1.add(queue2.poll());
            }

            int top = queue2.peek();

            queue1.add(queue2.poll());

            return top;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
