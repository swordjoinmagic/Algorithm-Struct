package luogu.Stack;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 思路：
 *
 */
public class LuoGuP3871中位数 {

    // 序列长度
    int N;

    // 操作数
    int M;

    // 小顶堆
    PriorityQueue<Integer> minHeap;
    // 大顶堆
    PriorityQueue<Integer> maxHeap;

    // 当前中位数
    int mid;

    // 在当前序列加上一个数
    public void Add(int n){
        if(maxHeap.isEmpty()){
            maxHeap.add(n);
            // 设置该数为中位数
            mid = n;
        }else if(n>mid){

        }
    }


    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
    }
}
