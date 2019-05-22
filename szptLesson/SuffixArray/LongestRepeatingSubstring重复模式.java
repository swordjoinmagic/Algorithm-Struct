package szptLesson.SuffixArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LongestRepeatingSubstring重复模式 {

    public static void main(String[] args) throws FileNotFoundException {
        LongestRepeatingSubstring重复模式 longestRepeatingSubstring = new LongestRepeatingSubstring重复模式();
        longestRepeatingSubstring.Input();
    }

    String str;

    // 后缀数组
    String[] array;

    public void Input() throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\我的文档\\WeChat Files\\wxid_pqm163cfpg4x52\\FileStorage\\File\\2019-05\\testData.txt"));
        Scanner in = new Scanner(System.in);
        str = in.next();
        Slove();
    }

    public int GetCommondPreffix(String s1,String s2){
        int result = 0;
        for(int i=0;i<s1.length() && i<s2.length();i++){
            if(s1.charAt(i)==s2.charAt(i))
                result++;
            else
                return result;
        }
        return result;
    }

    public void Slove(){
        MakeSufferArray();

        int max = 0;

        for(int i=0;i<array.length-1;i++){
            max = Math.max(max,GetCommondPreffix(array[i],array[i+1]));
        }

        System.out.print(max);
    }

    // 创建后缀数组
    public void MakeSufferArray(){
        array = new String[str.length()];

        for(int i=0;i<str.length();i++){
            array[i] = str.substring(i);
        }

        // 后缀数组排序
        Arrays.sort(array);
    }
}
