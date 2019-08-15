package PAT.GradeA;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题意：
 *      给定一个入栈序列1,2,3,N,和一个空栈(限定容量为M),
 *      给出一个出栈序列,求该出栈序列是否可能实现
 *
 * 思路:
 *      模拟法.按照顺序对出栈序列的所有元素进行检测,
 *      首先让空栈不停的进栈,直到栈顶元素为当前检测的出栈序列的元素,
 *      如果检测成功,那么让栈顶出栈,检测元素后移,继续前面的步骤,直到
 *      检测完出栈的所有元素
 */
public class PAT1040PopSequence {

    public static void main(String[] args){
        PAT1040PopSequence pat1040 = new PAT1040PopSequence();
        pat1040.Input();
    }

    // 空栈能保持的最大容量
    int M;

    // 入栈序列的长度
    int N;

    // 一共有多少个出栈序列需要检测
    int K;

    // 出栈序列
    int[] array;

    Stack<Integer> stack;

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();
        K = in.nextInt();

        array = new int[N];

        for(int i=0;i<K;i++){
            for(int j=0;j<N;j++) {
                array[j] = in.nextInt();
            }
            Slove();
        }
    }

    public void Slove(){
        // 当前检测出栈序列的元素的下标
        int index = 0;

        // 当前入栈元素
        int element = 1;

        stack = new Stack<>();
        stack.push(element++);

        while (index<N){
            // 当前栈顶元素和检测的元素相等
            if(!stack.empty() && stack.peek()==array[index]){
                stack.pop();index++;
            }else {

                if(element>N){System.out.println("NO");return;}


                if(stack.size()<M)
                    stack.push(element++);
                else {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }
}
