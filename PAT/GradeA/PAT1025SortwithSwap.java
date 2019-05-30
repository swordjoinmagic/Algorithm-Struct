package PAT.GradeA;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定序列{1,2,3,4...N},只能使用swap(0,x)操作,对该序列进行排序
 *
 * 思路:
 *      1. 每次都将0与它下标位置的数进行交换,直到0的下标为0,排序结束
 */
public class PAT1025SortwithSwap {

    public static void main(String[] args){
        PAT1025SortwithSwap pat1025 = new PAT1025SortwithSwap();
        pat1025.Input();
    }

    int N;

    int[] array;

    // 记录每个数的下标
    // index[x]=y表示数x在arry数组中的下标是y
    int[] index;

    boolean[] finished;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        array = new int[N];
        index = new int[N];
        finished = new boolean[N];

        for(int i=0;i<N;i++){
            array[i] = in.nextInt();
            index[array[i]] = i;
        }
        Slove();
    }

    private void Swap(int x,int y){

        // 交换下标
        int tempIndex = index[array[x]];
        index[array[x]] = index[array[y]];
        index[array[y]] = tempIndex;

        // 交换值
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public void Slove(){
        int count = 0;

        int zeroIndex = -1;

        while (true) {
            while (zeroIndex != 0) {
                // 找到目前0所在的下标
                zeroIndex = index[0];
                if(zeroIndex==0) break;
                // 找到目前要和0交换的数(该数其实也就是0的下标)
                int numberIndex = index[zeroIndex];

                Swap(zeroIndex, numberIndex);
                count++;

            }

            // 找到第一个没有排序成功的数,跟0交换
            int number = -1;
            for(int i=0;i<N;i++) {
                if(array[i]!=i) {
                    number = i;
                    break;
                }
            }
            if(number==-1) break;

            // 找到目前0所在的下标
            zeroIndex = index[0];
            Swap(zeroIndex,index[number]);
            count++;

            zeroIndex = index[0];
        }

        System.out.println(count);
    }
}
