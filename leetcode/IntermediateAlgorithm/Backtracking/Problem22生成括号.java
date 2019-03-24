package leetcode.IntermediateAlgorithm.Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 思路：
 *      1. 对于一个括号，可以在括号里面生成括号，
 *      可以在括号左边生成一个括号，可以在括号右边生成一个括号
 *      2. 回溯法，生成“（）”的所有排列来获得所有括号对数组合
 */
public class Problem22生成括号 {

    public static void main(String[] args){
        Problem22生成括号 problem22 = new Problem22生成括号();
        List<String> list = problem22.generateParenthesis(3);
        System.out.println(list);
    }

    private Set<String> set;

    public List<String> generateParenthesis(int n) {
        allCombinates = new ArrayList<>();
        backTrack("",0,0,n);
        return allCombinates;
    }

    public void DFS(String result,int k){
        if(k==0){
            set.add(result);
            return;
        }
        // 在括号左边右边中间加括号
        for(int i=0;i<result.length();i++){
            DFS(result.substring(0,i)+"()"+result.substring(i),k-1);
        }
    }


    List<String> allCombinates;
    /**
     *
     * @param cur  当前生成的括号对数字符串
     * @param open 左括号个数
     * @param close 右括号个数
     * @param max   一共可以放多少个括号
     */
    public void backTrack(String cur,int open,int close,int max){
        // 生成一个组合
        if(close==max && open==max){
            allCombinates.add(cur);
            return;
        }

        if(open<max)
            backTrack(cur+"(",open+1, close, max);
        if(close<open)
            backTrack(cur+")",open,close+1, max);
    }
}
