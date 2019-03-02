package szptLesson.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 全排列
public class Perm全排列 {

    public static void main(String[] args){
        Perm全排列 perm = new Perm全排列();
//        int[] array = {1,2,3,4};
//        perm.Perm(array,1,2);
        System.out.println(perm.GCD(399,333));
    }

    // 全排列的递归算法
    // 产生list[k:m]的所有排列
    public void Perm(int[] array,int k,int m){
        if(k==m){
            // 只剩一个元素
            for(int i=0;i<=m;i++)
                System.out.print(array[i]);
            System.out.println();
        }else{
            // Perm([1,2,3])的全排列等于(1)Perm([2,3])+(2)Perm([1,3])+(3)Perm([1,2])
            for(int i=k;i<=m;i++){
                // 将ri交换到开头
                swap(array,i,k);
                // 计算(Ri)Perm(R-Ri)的全排列
                Perm(array,k+1,m);
                // 将ri交换回去
                swap(array,i,k);
            }
        }
    }

    private void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int GCD(int a,int b){
        return b==0 ? a : GCD(b,a%b);
    }
}
