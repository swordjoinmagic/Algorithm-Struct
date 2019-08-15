package PAT.GradeA;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给出一串字符,求出里面出现次数最多的单词.
 *      如果有多个出现次数最多的单词,那么选择其中字典序最小的输出
 */
public class PAT1027SpeechPatterns {

    public static void main(String[] args){
        PAT1027SpeechPatterns pat1027 = new PAT1027SpeechPatterns();
        pat1027.Input();
    }

    String line;
    String[] strs;

    int maxCount = 0;
    String maxString = "";

    Map<String,Integer> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        strs = line.toLowerCase().split("[^A-Za-z0-9]");

        Slove();
    }

    public void Slove(){
        for(String str:strs){
            if(str.length()==0) continue;
            map.put(str,map.getOrDefault(str,0)+1);

            int count = map.get(str);
            if(count>maxCount){
                maxCount = count;
                maxString = str;
            }else if(count==maxCount){
                if(str.compareTo(maxString) < 0){
                    maxString = str;
                }
            }
        }

        System.out.print(maxString+" "+maxCount);
    }
}
