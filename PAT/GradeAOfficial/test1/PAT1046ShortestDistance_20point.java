package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定环形道路,求最短距离
 * 思路:
 *      1. 设left[i]为从1号节点到i的总距离,
 *         设right[i]为从i号节点到N号节点的总距离
 *
 *         那么,如果要求从start号节点到end号节点的最短距离,(start<end)
 *         有以下两种距离可以考虑:
 *              a. 从起点从左往右出发到达终点,
 *              表现为 distance = left[end]-left[start]
 *              b. 从起点从右往左出发到达终点,
 *              表现为 distance = left[start] + right[end]
 */
public class PAT1046ShortestDistance_20point {

    public static void main(String[] args){
        PAT1046ShortestDistance_20point pat1046 = new PAT1046ShortestDistance_20point();
        pat1046.Input();
    }

    // 路径数量,与节点数一致
    int N;

    int[] distance;
    int[] left;
    int[] right;

    // 要询问的点的数量
    int M;

    int cirleLine;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        distance = new int[N];
        left = new int[N];
        right = new int[N];

        for(int i=0;i<N;i++){
            distance[i] = in.nextInt();
            if(i==N-1){
                left[0] = 0;
            }else {
                left[i+1] = distance[i]+left[i];
            }
        }
        cirleLine = distance[N-1];
        for(int i=N-1;i>=0;i--){
            if(i==N-1)
                right[i] = 0;
            else
                right[i] = distance[i] + right[i+1];
        }

        M = in.nextInt();

        for(int i=0;i<M;i++){
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            Slove(a,b);
        }
    }

    public void Slove(int a,int b){
        /*
            *  a. 从起点从左往右出发到达终点,
            *  表现为 distance = left[end]-left[start]
            *  b. 从起点从右往左出发到达终点,
            *  表现为 distance = left[start] + right[end]
         */

        if(a>b){
            int temp = a; a = b; b = temp;
        }

        int distance = Math.min(left[b]-left[a],left[a]+right[b]+cirleLine);

        System.out.println(distance);
    }
}
