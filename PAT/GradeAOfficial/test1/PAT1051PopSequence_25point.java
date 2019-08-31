package PAT.GradeAOfficial.test1;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题意:
 *      1. 出栈序列.给定入栈序列{1,2,3,4,5,6,...,N}
 *      和出栈序列A1,A2,A3...AN,问这个出栈序列是否是可能的出栈序列
 * 思路:
 *      1. 直接用栈模拟
 */
public class PAT1051PopSequence_25point {

    public static void main(String[] args){
        PAT1051PopSequence_25point pat1051 = new PAT1051PopSequence_25point();
        pat1051.Input();
    }

    // M: 栈的最大容量
    // N: 入栈徐磊的长度
    // K: 有多少出栈序列需要被检查
    int M,N,K;

    int[] popSequence;

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();
        K = in.nextInt();

        popSequence = new int[N];

        for(int i=0;i<K;i++){
            for(int j=0;j<N;j++) popSequence[j] = in.nextInt();
            if(Check())
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public boolean Check(){
        Stack<Integer> stack = new Stack<>();

        // 表示当前准备进栈的数
        int now = 1;

        // num表示当前要出栈的数
        for(int num : popSequence){
            // 如果当前栈顶不是要找的数num,那么就持续入栈,
            // 直到栈顶==num
            while (stack.empty() || stack.peek()!=num){
                // 如果已经到达了入栈序列的末尾,报错
                if(now>N) return false;
                stack.add(now++);
                // 如果当前栈的容量超过最大容量,那么报错
                if(stack.size()>M) return false;
            }

            // 查看栈顶是否就是num
            if(stack.peek()==num){
                // 是的话,出栈
                stack.pop();
            }
        }

        return true;
    }
}
