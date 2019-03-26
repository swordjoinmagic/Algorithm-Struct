package szptLesson.DP;

import java.util.Scanner;

public class Different {

    public static void main(String[] args){
        Different different = new Different();
        different.Input();
        different.Slove();
    }

    int n;
    int[] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        array = new int[n];

        for(int i=0;i<n;i++){
            array[i] = in.nextInt();
        }
    }

    public void Slove(){
        int[] f = new int[n+1];

        // 初始化f
        f[0] = 0;
        f[1] = f[0]-f[1] >0 ? f[0]-f[1] : 0;

        for(int j=1;j<n;j++){
            f[j] = (f[j-1]+array[j-1]) - array[j];
        }

        for(int data : f){
            System.out.print(data+" ");
        }
        System.out.println();

        int max = 0;
        for(int j=1;j<n;j++){
            max = Math.max(max,f[j]);
        }

        System.out.println(max);
    }
}
