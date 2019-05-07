package luogu.MonotonicQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 思路：
 *      1. 单调队列维护最大值最小值。
 *      维护一个单调递增/递减队列，
 *      则每次队首/队尾元素即为最小/最大值
 */
public class P1886滑动窗口 {

    public static void main(String[] args){
        P1886滑动窗口 p1886 = new P1886滑动窗口();
        p1886.Input();
        p1886.QueryMin();
        p1886.QueryMax();
    }

    // 单调队列
    private Deque<Integer> deque;
    // 维护单调队列的序号
    private Deque<Integer> indexDeque;

    int n,k;
    int[] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        array = new int[n+1];

        deque = new LinkedList<>();
        indexDeque = new LinkedList<>();

        for(int i=1;i<=n;i++)
            array[i] = in.nextInt();
    }

    // 求解每次滑动窗口的最大值
    // 这里要维护一个单调递减队列
    public void QueryMax(){

        // 清空队列
        deque.clear(); indexDeque.clear();

        for(int i=1;i<=n;i++){

            int data = array[i];

            if(deque.isEmpty()){
                // 如果队列为空，直接入队
                deque.add(data);
                indexDeque.add(i);
            }else {

                // 队列不为空的情况下，
                // 如果当前元素比队首大，那么单调队列
                // 队尾出队，直到当前元素比队尾小或队列为空
                while (!deque.isEmpty() && data>deque.getLast()){
                    deque.pollLast();
                    indexDeque.pollLast();
                }
                // 当前元素入队
                deque.add(data);
                indexDeque.add(i);
            }

            // 此时队首就是最大值，因为这是一个单调递减队列
            // 对于不在滑动窗口范围内的值,队首出队
            while (indexDeque.getFirst() <= i-k){
                indexDeque.pollFirst();
                deque.pollFirst();
            }

            if(i>=k) System.out.print(deque.peekFirst()+" ");
        }
        System.out.println();

    }

    // 维护一个单调递增队列
    public void QueryMin(){

        // 清空队列
        deque.clear(); indexDeque.clear();

        for(int i=1;i<=n;i++){

            int data = array[i];

            if(deque.isEmpty()){
                // 如果队列为空，直接入队
                deque.add(data);
                indexDeque.add(i);
            }else {

                // 队列不为空的情况下，
                // 如果当前元素比队首小，那么单调队列
                // 队尾出队，直到当前元素比队尾大或队列为空
                while (!deque.isEmpty() && data<deque.getLast()){
                    deque.pollLast();
                    indexDeque.pollLast();
                }
                // 当前元素入队
                deque.add(data);
                indexDeque.add(i);
            }

            // 此时队首就是最小值，因为这是一个单调递减队列
            // 对于不在滑动窗口范围内的值,队首出队
            while (indexDeque.getFirst() <= i-k){
                indexDeque.pollFirst();
                deque.pollFirst();
            }

            if(i>=k) System.out.print(deque.peekFirst()+" ");
        }
        System.out.println();

    }
}
