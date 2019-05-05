package leetcode.StructPractice.QueueAndStack;

import java.util.Stack;

/**
 * 思路：
 *      1. 类似括号匹配和表达式计算。
 *
 *          a. 遇到 '[' 时，进栈
 *          b. 遇到字母时，进栈
 *          b. 遇到 ']'时，一直出栈，直到遇到第一个 '[' 字符，
 *          将之前出栈的字符连接成字符串，将该字符串重新进栈
 *
 */
public class Problem394字符串解码 {

    public static void main(String[] args){
        String s = "10[c]2[b2[a]]";
        Problem394字符串解码 problem394 = new Problem394字符串解码();
        String result = problem394.decodeString(s);
        System.out.print(result);
    }

    private Stack<String> stack;

    public String decodeString(String s) {
        stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if( ch != ']'){
                if(!isNum(ch))
                    stack.add(ch+"");
                else {

                    String num = "";
                    while (i<s.length()){
                        ch = s.charAt(i++);
                        if(isNum(ch))
                            num += ch;
                        else
                            break;
                    }
                    i-=2;

                    stack.add(num);
                }
            }else {

                StringBuilder str = new StringBuilder();

                // 出栈，直到遇到第一个'['运算符
                while (true){

                    String t = stack.pop();

                    if(t.equals("[")){

                        int num = Integer.parseInt(stack.pop());

                        String newStr = "";

                        while (num-->0){
                            newStr += str.toString();
                        }

                        stack.add(newStr);

                        break;
                    }else {
                        str.insert(0,t);
                    }

                }


            }

        }

        StringBuilder result = new StringBuilder();

        while (!stack.empty()){
            result.insert(0,stack.pop());
        }

        return result.toString();
    }

    /**
     * 判断一个字符是否是字母
     * @param ch
     * @return
     */
    private boolean isAlphat(char ch){
        if( (ch<='z' && ch>='a') || (ch<='Z' && ch>='A') )
            return true;
        return false;
    }

    /**
     * 判断一个字符是否是数字
     * @param ch
     * @return
     */
    private boolean isNum(char ch){
        if(ch>='0' && ch<='9')
            return true;
        return false;
    }
}
