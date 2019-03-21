package leetcode.IntermediateAlgorithm.Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 思路:
 *      1. 使用字典将数字与字符串进行对应,n个数字字符需要循环
 *      pow(3,n)
 */
public class Problem17电话号码的字母组合 {

    public static void main(String[] args){
        Problem17电话号码的字母组合 problem17 = new Problem17电话号码的字母组合();
        List<String> a = problem17.letterCombinations("23");
        System.out.println(a);

    }

    Map<Integer,String> map = new HashMap<>();
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");

        if(digits==null || digits.length()<=0) return list;
        getCombinate(digits,0,"");

        return list;
    }

    /**
     *
     * @param digits
     * @param pos 当前下标
     * @return
     */
    public void getCombinate(String digits,int pos,String result){

        if(pos>=digits.length()){
            list.add(result);
            return;
        }

        int index = digits.charAt(pos) - '0';
        String str = map.get(index);

        for(int i=0;i<str.length();i++){
            getCombinate(digits,pos+1, result+str.charAt(i));
        }
    }
}
