package szptLesson.DP;

import java.util.Scanner;

/**
 * 思路：
 *      1. 从倒数第2层开始
 */
public class CompulateTriangle {

    public static void main(String[] args){
        CompulateTriangle compulateTriangle = new CompulateTriangle();
        compulateTriangle.Input();
        compulateTriangle.Slove();
    }

    // N层
    int n;
    // 数字三角形
    int[][] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                array[i][j] = in.nextInt();
            }
        }
    }

    public void Slove(){

        // 从倒数第二层开始
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<i+1;j++){
                array[i][j] += Math.max(array[i+1][j],array[i+1][j+1]);
            }
        }

        System.out.println(array[0][0]);
    }
}
