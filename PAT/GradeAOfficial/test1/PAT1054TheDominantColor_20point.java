package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 找到二维数组中出现次数最多的值(要求都是连接起来的值)
 */
public class PAT1054TheDominantColor_20point {

    public static void main(String[] args){
        PAT1054TheDominantColor_20point pat1054T = new PAT1054TheDominantColor_20point();;
        pat1054T.Input();
    }

    int M,N;

    int[][] array;

    int[] count = new int[33554432];

    int maxColor = 0;
    int maxCount = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();
        array = new int[M][N];

        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                array[i][j] = in.nextInt();
                count[array[i][j]]++;
                if(maxCount < count[array[i][j]]){
                    maxCount = count[array[i][j]];
                    maxColor = array[i][j];
                }
            }
        }

        Slove();
    }

    public void Slove(){
        System.out.println(maxColor);
    }
}
