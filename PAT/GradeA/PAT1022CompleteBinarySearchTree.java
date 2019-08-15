package PAT.GradeA;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 思路:
 *      1. 对二叉树进行中序遍历,可以得到有序的序列,
 *      而完全二叉树的编号是有连续的.
 *
 *      所以可以先对输入序列进行排序,然后对二叉树进行中序遍历,
 *      构造一棵完全二叉搜索树
 */
public class PAT1022CompleteBinarySearchTree {

    public static void main(String[] args){
        PAT1022CompleteBinarySearchTree pat1022 = new PAT1022CompleteBinarySearchTree();
        pat1022.Input();
    }

    // 节点数
    int N;
    int[] array;

    int[] tree;

    int index = 1;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        array = new int[N+1];
        tree = new int[N+1];

        for(int i=1;i<=N;i++){
            array[i] = in.nextInt();
        }

        Arrays.sort(array);

        Slove();
    }

    // 中序遍历二叉搜索树
    public void Middle(int number){
        if(number>N) return;
        // 左子树
        Middle(number+number);

        tree[number] = array[index++];

        // 右子树
        Middle(number+number+1);
    }

    public void Slove(){
        Middle(1);

        for(int i=1;i<=N;i++){
            System.out.print(tree[i]+" ");
        }
    }
}
