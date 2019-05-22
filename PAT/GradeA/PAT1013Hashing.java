package PAT.GradeA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述:
 *      1. 给定hash表的大小 MSize 和 一共有多少个数N,
 *      求每个数插入到Hash表中时的下标.
 *      一个关键点在于,hash表大小必须为质数,当题目给出的
 *      MSize不是质数时,找到一个正好比他大的质数
 *
 * 思路:
 *      1. 先用素数筛求素数,再用二分找到正好比MSize大的质数,
 *      最后计算hash下标
 */
public class PAT1013Hashing {

    public static void main(String[] args){
        PAT1013Hashing pat1013 = new PAT1013Hashing();
        pat1013.Input();
    }

    final int MAXNUMBER = 10001;

    int Msize;
    int N;

    // 筛子
    boolean[] u;
    // 素数表
    List<Integer> prims;

    // 某个下标是否已经插过值了
    boolean[] visited;

    public void Input(){
        Scanner in = new Scanner(System.in);
        Msize = in.nextInt();
        N = in.nextInt();

        // 找素数
        u = new boolean[MAXNUMBER+1];
        prims = new ArrayList<>();
        FindPrime();

        // 找到正好比N大的素数
        int k = BinarySearch(Msize,0,prims.size());
        if(k<0){
            Msize = prims.get(-(k+1));
        }

        visited = new boolean[Msize+1];

        // 插入值
        for(int i=0;i<N;i++){
            int index = in.nextInt();
            index %= Msize;

            index = QuadraticProbing(index);
            if(index>=0){
                System.out.print(index+" ");
            }else {
                System.out.print("- ");
            }
        }
    }

    // 二次探查法,返回元素可以插入的下标,如果没有,返回-1
    public int QuadraticProbing(int index){
        if(!visited[index]) {
            visited[index] = true;
            return index;
        }
        int insertIndex;
        for(int i=1;i<Msize;i++){
            insertIndex = (index+i*i)%Msize;
            if(!visited[insertIndex]){
                visited[insertIndex] = true;
                return insertIndex;
            }
        }

        return -1;
    }

    /**
     * 返回第一个大于等于 target 的目标数在数组中的下标
     * @param target 要查找的目标数
     * @param start  查找开始位置
     * @param end    查找结束位置
     * @return
     */
    public int BinarySearch(int target,int start,int end){
        int low = start;
        int high = end - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = prims.get(mid);

            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * 埃式筛法找素数
     */
    public void FindPrime(){
        for(int i=2;i<=MAXNUMBER;i++){
            if(!u[i]){
                // i是素数,将i的倍数全部筛去
                for(int j=2;i*j<=MAXNUMBER;j++){
                    u[i*j] = true;
                }

                prims.add(i);
            }
        }
    }
}
