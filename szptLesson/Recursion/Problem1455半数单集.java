package szptLesson.Recursion;

import java.util.Scanner;

public class Problem1455半数单集 {
    private int n;
    private int[] array;
    private int result = 0;
    private boolean[] visited;

    public static void main(String[] args){
        Problem1455半数单集 problem1455 = new Problem1455半数单集();
        problem1455.Input();
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[n];
        visited = new boolean[n];

        CreateHalfNumberSet(0,n);
    }

    // 产生半数集
    public void CreateHalfNumberSet(int k,int num){
        if(num==0){
            System.out.print(n+" ");
            for(int i=0;i<k-1;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=num/2;i>=0;i--){
            array[k] = i;
            CreateHalfNumberSet(k + 1, i);
        }
    }

}
