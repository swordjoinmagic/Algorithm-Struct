package leetcode.IntermediateAlgorithm.other;

import java.util.Stack;

/**
 * 思路：
 *      1. 即计算后缀表达式的值。利用栈。
 *      当读到数字时，值入栈，当读到运算符时，栈弹出两个数字，
 *      经过运算后将结果压入栈中
 */
public class Problem150逆波兰表达式求值 {

    public static void main(String[] args){
        Problem150逆波兰表达式求值 problem = new Problem150逆波兰表达式求值();
        String[] tokens = new String[]{"4", "13", "5", "/", "+"};
        int result = problem.evalRPN(tokens);
        System.out.println(result);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<tokens.length;i++){
            String str = tokens[i];
            if(!OperatorCalculate(str,stack)){
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    /**
     * 判断一个字符串是否是运算符,如果是，那么进行运算，返回true，
     * 否则返回false
     * @return
     */
    private boolean OperatorCalculate(String str,Stack<Integer> stack){
        if(str.equals("+")){
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a+b);
            return true;
        }else if(str.equals("-")){
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a-b);
            return true;
        }else if(str.equals("*")){
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a*b);
            return true;
        }else if(str.equals("/")){
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a/b);
            return true;
        }
        return false;
    }
}
