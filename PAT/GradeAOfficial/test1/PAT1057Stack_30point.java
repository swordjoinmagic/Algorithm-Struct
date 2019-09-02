package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 设计一个可以返回中位数的栈
 * 思路:
 *      1. 每次push的时候,都在另一个stack上push当前的中位数
 */
public class PAT1057Stack_30point {

    public static void main(String[] args){
        PAT1057Stack_30point pat1057 = new PAT1057Stack_30point();
        pat1057.Input();
    }

    Stack<Integer> stack = new Stack<>();

    int N;
    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        for(int i=0;i<N;i++){
            String command = in.next();
            switch (command){
                case "Pop":
                    Pop();
                    break;
                case "PeekMedian":
                    PeekMedian();
                    break;
                case "Push":
                    int x = in.nextInt();
                    Push(x);
                    break;
            }
        }
    }

    public void PeekMedian(){
        if(stack.empty()){
            System.out.println("Invalid");
            return;
        }
        List<Integer> list = new ArrayList<>(stack);
        Collections.sort(list);
        int total = list.size();
        if(total % 2==0){
            System.out.println(list.get(total/2-1));
        }else {
            System.out.println(list.get((total+1)/2-1));
        }
    }

    public void Push(int x){
        stack.add(x);
    }

    public void Pop(){
        if(stack.empty()){
            System.out.println("Invalid");
            return;
        }
        System.out.println(stack.pop());
    }
}
