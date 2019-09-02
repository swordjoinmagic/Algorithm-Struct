package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定A.B,以 xGySzK表示,求A+B的结果
 *      其中29K = 1S,17S = 1G
 * 思路:
 *      1. 先将AB都化为xxxK的形式,再将xxxK的形式转为xGySzK的形式
 */
public class PAT1058ABinHogwarts_20point {

    public static void main(String[] args){
        PAT1058ABinHogwarts_20point pat1058 = new PAT1058ABinHogwarts_20point();
        pat1058.Input();
    }

    int aG,aS,aK;
    int bG,bS,bK;

    int[] A = new int[3];
    int[] B = new int[3];
    int[] C = new int[3];

    int[] map = new int[]{
            1,17,29
    };

    public void Input(){
        Scanner in = new Scanner(System.in);
        String[] strs = in.next().split("\\.");

        aG = Integer.parseInt(strs[0]);A[0] = aG;
        aS = Integer.parseInt(strs[1]);A[1] = aS;
        aK = Integer.parseInt(strs[2]);A[2] = aK;

        strs = in.next().split("\\.");

        bG = Integer.parseInt(strs[0]);B[0] = bG;
        bS = Integer.parseInt(strs[1]);B[1] = bS;
        bK = Integer.parseInt(strs[2]);B[2] = bK;

        Slove();
    }

    public void Slove(){
        for(int i=2;i>=0;i--){
            int total = A[i]+B[i];
            // 考虑进位
            if(i!=0&&total>=map[i]){
                C[i-1] += 1;
                total -= map[i];
            }
            C[i] += total;
        }

        System.out.print(C[0]);
        for(int i=1;i<3;i++)
            System.out.print("."+C[i]);
    }
}
