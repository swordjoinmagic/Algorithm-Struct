package PAT.GradeAOfficial.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个非负数N,求N的所有位数上的数相加的结果sum,
 *      sum用英文进行表述,如15 = one five
 * 思路:
 *      1. 模拟.N超过整形,所以用读字符串的方式读.
 *      对于结果sum,也用读字符串的方式读并挨个判断
 */
public class PAT1005SpellItRight_20point {

    public static void main(String[] args){
        PAT1005SpellItRight_20point pat1005 = new PAT1005SpellItRight_20point();
        pat1005.Input();
    }

    // 数字和英语一一对应
    String[] map;

    String N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.next();

        // 初始化map
        map = new String[]{
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
        };

        Slove();
    }

    public void Slove(){

        int sumResult = 0;

        for(int i=0;i<N.length();i++){
            int num = N.charAt(i)-'0';
            sumResult += num;
        }

        String sum = String.valueOf(sumResult);
        List<String> result = new ArrayList<>();
        for(int i=0;i<sum.length();i++){
            int index = sum.charAt(i)-'0';
            result.add(map[index]);
        }

        for(int i=0;i<result.size();i++){
            if(i!=result.size()-1){
                System.out.print(result.get(i)+" ");
            }else System.out.print(result.get(i));
        }
    }
}
