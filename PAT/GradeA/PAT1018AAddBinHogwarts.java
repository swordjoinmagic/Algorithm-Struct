package PAT.GradeA;

import java.util.Scanner;

/**
 * 题目描述:
 *      1. 魔法世界有货币 G , S ,K
 *      其中
 *      1G  =  17 SS
 *      1S  =  29 K
 *
 *      现在给出两个货币 A 和 B,他们都是以
 *      xG ySS zK的形式给出,求A+B的值(同样以这个形式输出)
 *
 * 思路:
 *      1. 简单的进制转换题,先处理个位,即K,进位时S+1,计算S位同理
 */
public class PAT1018AAddBinHogwarts {

    public static void main(String[] args){
        PAT1018AAddBinHogwarts pat1018 = new PAT1018AAddBinHogwarts();
        pat1018.Input();
    }

    int[] A = new int[3];
    int[] B = new int[3];

    public void Input(){
        Scanner in = new Scanner(System.in);
        String[] str = in.next().split("\\.");
        A[0] = Integer.parseInt(str[0]);
        A[1] = Integer.parseInt(str[1]);
        A[2] = Integer.parseInt(str[2]);
        str = in.next().split("\\.");
        B[0] = Integer.parseInt(str[0]);
        B[1] = Integer.parseInt(str[1]);
        B[2] = Integer.parseInt(str[2]);

        Slove();
    }

    public void Slove(){

        int[] C = new int[3];

        // 从个位开始加起,考虑进位
        for(int i=2;i>=0;i--){
            int result = A[i]+B[i];
            if(i==2){
                // 个位(即K位)

                // 进位判断
                if(result>=29){
                    // 进位
                    result -= 29;
                    A[i-1] ++;
                }
            }else if(i==1){

                // 十位(即S位)

                // 进位判断
                if(result>=17){
                    // 进位
                    result -= 17;
                    A[i-1]++;
                }
            }
            C[i] = result;
        }

        System.out.println(C[0]+"."+C[1]+"."+C[2]);
    }
}
