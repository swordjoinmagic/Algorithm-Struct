package PAT.GradeB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 思路,素数筛
 *
 * 埃式筛法,时间复杂度O(NLogLogN)
 */
public class PATProblem1003数素数 {

    public static void main(String[] args){
        PATProblem1003数素数 problem1003 = new PATProblem1003数素数();
        problem1003.Slove();
    }

    // 筛子,为True表示筛去
    boolean[] u;

    // 素数表
    List<Integer> sushu;

    public void Slove(){

        int MAX_VALUE = 1000000;

        Scanner in = new Scanner(System.in);

        int M = in.nextInt();
        int N = in.nextInt();

        u = new boolean[MAX_VALUE+1];
        sushu = new ArrayList<>();

        u[0] = true;
        u[1] = true;

        for(int i=2;sushu.size()<N;i++){
            if(!u[i]){
                sushu.add(i);

                // 素数,筛去它的所有倍数
                for(int j=2;i*j<=MAX_VALUE;j++){
                    u[i*j] = true;
                }
            }
        }

        int count = 0;
        for(int i=M-1;i<=N-1;i++){
            if(i==N-1){
                System.out.print(sushu.get(i));
            }else {
                System.out.print(sushu.get(i)+" ");
            }
            if(count==10){
                System.out.println();
                count = 1;
            }
            count++;
        }
    }
}
