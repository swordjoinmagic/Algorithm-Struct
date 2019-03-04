package szptLesson.Recursion;

import java.util.Date;
import java.util.Scanner;

public class Combination组合 {

    private int n;
    private int r;

    private int[] nums;
    private boolean[] mark;
    private int[] result;

    public static void main(String[] args){
        Combination组合 combination = new Combination组合();
        combination.Input();
        combination.Combinate(0);
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        r = in.nextInt();
        nums = new int[n];
        mark = new boolean[n];
        result = new int[r];
        for(int i=0;i<n;i++){
            nums[i] = i+1;
        }
    }

    /**
     * 从n个数中选择r个数进行组合
     * @param pos
     */
    public void Combinate(int pos){

        if(pos==r){

            for(int data : result){
                System.out.print(data+" ");
            }
            System.out.println();
            return;
        }

        for(int i=pos;i<nums.length;i++){
            // 选择该数
            result[pos] = nums[i];

            // 选择下一个数
            if(pos==0 || result[pos]>result[pos-1])
                Combinate(pos+1);
        }
    }

    /**
     * 降序，从n个数中选择r个数进行组合
     * @param pos
     */
//    public void DownCombinate(int pos){
//
//        if(pos==r){
//
//            for(int data : result){
//                System.out.print(data+" ");
//            }
//            System.out.println();
//
//            return;
//        }
//
//        for(int i=pos;i<nums.length;i++){
//            // 选择该数
//            result[pos] = nums[i];
//
//            // 选择下一个数
//            if(pos==0 || result[pos]>result[pos-1])
//                Combinate(pos+1);
//        }
//    }
}
