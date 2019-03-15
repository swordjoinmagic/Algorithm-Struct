package szptLesson.Recursion;

import java.util.Scanner;

public class SzptProblem1469蛇形填数 {

    private int n;
    private int[][] array;
    int num;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[n][n];
        num = 1;
        Search(0,n-1,n-1);
    }

    public void Search(int x,int y,int len){

    }
}
