package szptLesson.Recursion;

import java.util.Scanner;

public class Problem1221拼出缘分 {
    public static void main(String[] args){
        Problem1221拼出缘分 problem1221 = new Problem1221拼出缘分();
        problem1221.Input();
        problem1221.Slove(0);
    }

    private int n;
    private int[] array;
    private boolean[] visited;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[2*n];
        visited = new boolean[n+1];
    }

    private void Print(){
        for(int data : array){
            System.out.print(data+" ");
        }
        System.out.println();
    }

    public void Slove(int pos){
        if(pos==2*n){
            for(int data : array){
                System.out.print(data+" ");
            }
            System.out.println();
            return;
        }
        if(array[pos]!=0) Slove(pos+1);
        else {
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && pos + i + 1 < 2 * n && array[pos] == 0 && array[pos + i + 1] == 0) {

                    array[pos] = i;
                    array[pos + i + 1] = i;
                    visited[i] = true;

                    Slove(pos + 1);

                    // 还原
                    array[pos] = 0;
                    array[pos + i + 1] = 0;
                    visited[i] = false;
                }
            }
        }
    }
}
