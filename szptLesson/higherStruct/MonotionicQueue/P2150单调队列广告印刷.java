package szptLesson.higherStruct.MonotionicQueue;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 思路:
 *      1. 使用单调队列解决.
 *      从左向右扫一遍,获得建筑物i左边 高度大于等于h[i] 的建筑物的数量
 *      从右向左扫一遍,获得建筑物i右边 高度大于等于h[i] 的建筑物的数量
 *
 *      最后,根据建筑物i 左边和右边 高度大于等于h[i]的建筑物数量,
 *      得到最大面积
 *
 *      时间复杂度O(N)
 */
public class P2150单调队列广告印刷 {

    public static void main(String[] args) throws IOException {
        P2150单调队列广告印刷 p2150 = new P2150单调队列广告印刷();
        p2150.Input();
        p2150.Slove();
    }

    // Lcount[i]=x表示建筑物i左边有x个建筑物高度大于等于h[i]
    int[] Lcount;
    int[] Rcount;
    int[] h;
    int n;

    public void Input() throws IOException {
//        Scanner in = new Scanner(System.in);

        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        in.nextToken();
        n = (int)in.nval;
        h = new int[n];
        Lcount = new int[n];
        Rcount = new int[n];
        for(int i=0;i<n;i++) {
            in.nextToken();
            h[i] = (int)in.nval;
        }
    }

    // 从左往右扫一遍,获得建筑物i左边高度比他大的建筑物数量
    // 即构造单调递增队列
    public void LQuery(){
        Deque<Integer> deque = new LinkedList<>();

        int index = 0;

        for(int i=0;i<n;i++){
            int data = h[i];
            if(deque.isEmpty()){
                deque.add(i);
                Lcount[index++] = 0;
            }else {

                int count = 0;
                // 如果当前元素比队尾元素小,那么队尾出队
                // 直到当前元素比队尾大或队列为空
                while (!deque.isEmpty() && data<=h[deque.peekLast()]){
                    count += Lcount[deque.peekLast()] + 1;
                    deque.pollLast();
                }

                Lcount[index++] = count;
                deque.add(i);
            }
        }
    }

    // 从右往左扫一遍,获得建筑物i右边高度比他大的建筑物数量
    // 即构造单调递增队列
    public void RQuery(){
        Deque<Integer> deque = new LinkedList<>();

        int index = n-1;

        for(int i=n-1;i>=0;i--){
            int data = h[i];
            if(deque.isEmpty()){
                deque.add(i);
                Rcount[index--] = 0;
            }else {

                int count = 0;
                // 如果当前元素比队尾元素小,那么队尾出队
                // 直到当前元素比队尾大或队列为空
                while (!deque.isEmpty() && data<=h[deque.peekLast()]){
                    count += Rcount[deque.peekLast()] + 1;
                    deque.pollLast();
                }

                Rcount[index--] = count;
                deque.add(i);
            }
        }
    }

    public void Slove(){
        LQuery();
        RQuery();

        long maxR = 0;

        for(int i=0;i<n;i++){
            int height = h[i];
            maxR = Math.max(height*(Lcount[i]+Rcount[i]+1),maxR);
        }

        System.out.println(maxR);
    }
}
