package PAT.GradeA;

import javax.net.ssl.SSLContext;
import java.util.*;

/**
 * 题目描述:
 *      1. 给定一个栈数据结构,支持三种操作,push,peek,PeekMedian,
 *      其中peekMedian操作表示返回当前 第(栈的大小/2)小的元素,
 *      如果栈的大小是奇数,那么返回当前 第(栈的大小+1/2)小的元素
 *
 * 思路:
 *      1. 快排思想找第k小的元素
 *      思路1错误,使用快排依旧超时
 *
 *      2.
 */
public class PAT1017Stack {

    public static void main(String[] args){
        PAT1017Stack pat1017 = new PAT1017Stack();
        pat1017.Input();
    }

    // 操作数
    int N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        Slove(in);
    }

    /**
     *
     * 表示一趟快排,
     * 将比基准大的元素交换到基准右边,
     * 比基准小的元素交换到基准左边
     *
     * 返回值是基准当前所在下标
     *
     * @param array 要快排的数组
     * @param start 当前要快排的数组的开始位置
     * @param end   当前要快排的数组的结束位置
     */
    private int OnceQuickSort(Object[] array,int start,int end){

        // 基准,取第一个元素作为基准
        int standard = (int)array[start];

        int i = start;
        int j = end;
        while (i<j){

            // 找到第一个比基准小的元素
            while (i<j && (int)array[j] >=standard)    j--;
            if(i<j)
                // 将这个比基准小的元素交换到左边去
                array[i++] = array[j];

            // 找到第一个比基准大的元素
            while (i<j && (int)array[i]<=standard)  i++;
            if(i<j)
                // 将这个比基准大的元素交换到右边去
                array[j--] = array[i];

        }
        array[i] = standard;
        return i;
    }

    /**
     * 获得栈中第n小的元素
     * @param stack
     * @param smallestTH
     * @return
     */
    public int PeekMedian(Stack<Integer>stack,int smallestTH){
        Object[] array = stack.toArray();

        int start = 0;
        int end = array.length-1;

        while (start<end) {
            int index = OnceQuickSort(array, start, end);
            if (index == smallestTH - 1) return (int) array[index];
            else {
                if (index < smallestTH - 1) {
                    start = index + 1;
                } else {
                    end = index - 1;
                }
            }
        }

        return (int)array[smallestTH-1];
    }

    public void Slove(Scanner in){

        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<N;i++){
            String command = in.next();

            if(command.equals("Pop")){
                if(stack.empty()){
                    System.out.println("Invalid");
                }else {
                    System.out.println(stack.pop());
                }
            }

            if(command.equals("PeekMedian")){
                int smallest_th = stack.size()%2==0? stack.size()/2 : (stack.size()+1)/2;
                if(stack.empty()){
                    System.out.println("Invalid");
                }else {
                    System.out.println(PeekMedian(stack,smallest_th));
                }
            }

            if(command.equals("Push")){
                int n = in.nextInt();
                stack.push(n);
            }
        }
    }
}
