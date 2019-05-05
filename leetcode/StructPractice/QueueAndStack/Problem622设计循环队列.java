package leetcode.StructPractice.QueueAndStack;

import java.util.Arrays;

/**
 * 思路:
 *      1. 难点在于如何判断队列是空的还是已满状态
 *
 *      空状态的判断：
 *          当头指针和尾指针指向位置一致时，队列为空，即：
 *          head == tail
 *      满状态的判断：
 *          当尾指针的下一个位置是头指针时，队列已满，即：
 *          tail + 1 == head
 *      入队操作：
 *          if(!Full())
 *              _value[tail] = value;
 *              tail++ %= N;
 *      出队操作：
 *          if(!Empty())
 *              value[head] = 0;
 *              head ++ %= N;
 *      初始状态:
 *          head = tail = 0;
 */
public class Problem622设计循环队列 {

    public static void main(String[] arg){
        Problem622设计循环队列 circularQueue = new Problem622设计循环队列();

        circularQueue.MyCircularQueue(6);

        boolean result = circularQueue.enQueue(6);
        System.out.println(result);

        int last = circularQueue.Rear();
        System.out.println(last);

        last = circularQueue.Rear();
        System.out.println(last);

        result = circularQueue.deQueue();
        System.out.println(result);


        result = circularQueue.enQueue(5);
        System.out.println(result);

    }

    // 循环队列的值
    int[] _value;

    // 头指针
    int head = 0;

    // 尾指针
    int tail = 0;

    // 数组长度
    int length;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public void MyCircularQueue(int k) {
        // 预留一个空位置处理
        _value = new int[k+1];

        // 初始化数组长度
        length = k+1;

        // 初始化头尾指针
        head = 0; tail = 0;

        Arrays.fill(_value,-1);
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        _value[tail] = value;
        tail = (tail+1)%length;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        head = (head+1)%length;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return _value[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return _value[tail==0?length-1:(tail-1)];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head==tail;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (tail+1)%length == head;
    }
}
