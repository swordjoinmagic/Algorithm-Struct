package leetcode.PrimaryAlgorithm.Ohter;

import java.util.Stack;

/**
 * 思路：
 *      括号匹配，
 *      1. 如果是左括号，进栈
 *      2. 如果是右括号，判断栈顶元素是否匹配
 */
public class Problem20有效的括号 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(ch == '(' || ch=='{' || ch=='[')
                stack.push(ch);
            else if(stack.size()<=0)
                return false;
            else if(ch==')' && stack.pop()!='(')
                return false;
            else if(ch=='}' && stack.pop()!='{')
                return false;
            else if(ch==']' && stack.pop()!='[')
                return false;
        }
        return stack.isEmpty();
    }
}
