package szptLesson.Recursion;

import java.util.Scanner;

public class Combination分治法求组合 {

    public static void main(String[] args){
        Combination分治法求组合 combination = new Combination分治法求组合();
        combination.Input();
        combination.Slove();
    }

    // 一共n个数，选m个数
    int n,m;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        printArray = new int[m];
        array = new int[n];
        for(int i=0;i<n;i++){
            array[i] = i+1;
        }
    }

    public void Slove(){
        Search(0,0);
    }

    // 用于输出组合的数组
    private int[] printArray;
    // 要取数进行组合的数组
    private int[] array;

    public void Search(int pos,int index){
        if(pos == m){
            for(int data : printArray){
                System.out.print(data+" ");
            }
            System.out.println();
            return;
        }

        // 处理越界
        if(index>=n) return;

        // 选当前数
        printArray[pos] = array[index];
        // 对下一位的数进行选取
        Search(pos+1,index+1);


        // 不选当前数
        Search(pos,index+1);

    }
}
