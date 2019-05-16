package leetcode.AdvancedAlgorithm;

import java.util.Stack;

/**
 * 思路:
 *      1. 同计算中缀表达式一样.
 *      步骤:
 *          a. 将中缀表达式变为后缀表达式
 *          b. 利用栈计算后缀表达式
 */
public class Problem227基本计算器II {

    public static void main(String[] args){
        Problem227基本计算器II problem227 = new Problem227基本计算器II();
        int result = problem227.calculate("100000000/1/2/3/4/5/6/7/8/9/10");
        System.out.println(result);
    }

    int[] prioity;

    /**
     * 从start处向下获得该字符串中的一个整数,
     * 返回获得的整数和第一个非整数的下标
     * @param str
     * @param start
     * @return
     */
    public int[] GetInt(String str,int start){
        String total = "";
        for(;start<str.length();start++){
            char ch = str.charAt(start);
            if(ch<='9' && ch>='0'){
                total += ch;
            }else
                break;
        }
        int result = Integer.parseInt(total);
        return new int[]{result,start};
    }

    public int calculate(String s) {

        prioity = new int[256];
        prioity['+'] = 1;
        prioity['-'] = 1;
        prioity['*'] = 2;
        prioity['/'] = 2;
        prioity['0'] = 0;

        // 数字栈
        Stack<Integer> stack = new Stack<>();
        // 运算符栈
        Stack<Character> operateStack = new Stack<>();

        int i=0;
        while (i<s.length()){
            char ch = s.charAt(i);

            // 自动忽略所有空格
            if(ch==' ') {
                i++;
                continue;
            }

            if(ch>='0' && ch<='9'){
                // 数字
                int[] result = GetInt(s,i);
                int number = result[0];
                i = result[1];

                stack.add(number);
            }else {
                // 运算符

                // 判断当前运算符和栈顶运算符的优先级
                // 当前运算符优先级更高(大于等于),那么入栈,
                // 当前优先级小于栈顶运算符优先级,那么出栈,
                // 直到当前运算符优先级大于等于栈顶运算符

                // 栈顶运算符
                char top = '0';
                if(!operateStack.isEmpty())
                    top = operateStack.peek();
                if(prioity[ch]>prioity[top]){
                    // 当前运算符优先级更高,入栈
                    operateStack.add(ch);
                }else {
                    // 当前运算符优先级更低,出栈,直到当前运算符优先级更高
                    while (!operateStack.isEmpty() && prioity[ch]<=prioity[operateStack.peek()]){
                        pop(stack,operateStack);
                    }
                    operateStack.add(ch);
                }

                i++;
            }
        }

        while (stack.size()!=1){
            pop(stack,operateStack);
        }

        return stack.pop();
    }

    private void pop(Stack<Integer> intStack,Stack<Character> charStack){
        char ch = charStack.pop();
        int b = intStack.pop();
        int a = intStack.pop();
        int result = 0;
        switch (ch){
            case '+':
                result = a+b;
                break;
            case '-':
                result = a-b;
                break;
            case '*':
                result = a*b;
                break;
            case '/':
                result = a/b;
                break;
        }

        intStack.add(result);
    }
}
