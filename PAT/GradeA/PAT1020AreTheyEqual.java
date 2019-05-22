package PAT.GradeA;

import java.util.Scanner;

/**
 * 题目描述：
 *      1. 给定n个有效数字，两串小于等于10^100的浮点数，
 *      求在有效数字范围内时，这两个浮点数是否相等
 *
 * 思考：
 *      1.
 *      a. 找两串字符串的小数点位置
 *      b. 顺序截取两个字符串n位有效数字（遇小数点跳过）
 *      c. 将两串浮点数变为uniform形式
 *
 * 坑:
 *      1. 输出的答案要有N位数字,没有0要补足
 *      2. 第二步顺序截取n位有效数字时,遇到的0要舍去,
 *      除非这个0位于最后一个位置或这个0位于小数点后面的位置
 *      3. 注意输入数字全为0的情况
 *      4. 注意输入数字为 0.000xxx的情况
 *
 */
public class PAT1020AreTheyEqual {

    public static void main(String[] args){
        PAT1020AreTheyEqual pat1020 = new PAT1020AreTheyEqual();
        pat1020.Input();
    }

    int n;
    String f1;
    String f2;

    int f1Dot;
    int f2Dot;
    int f1Zero;
    int f2Zero;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        f1 = in.next();
        f2 = in.next();

        f1 = trim(f1);
        f2 = trim(f2);

        Slove();
    }


    public void Slove(){
        // 找字符串中的小数点位置
        f1Dot = f1.indexOf('.');
        f2Dot = f2.indexOf('.');

        // 截取有效位数后的字符串
        String tF1 = split(f1);
        String tF2 = split(f2);

        // 找字符串小数点后，在遇到第一个有效数字之前，0的数目
        f1Zero = getZero(f1,f1Dot);
        f2Zero = getZero(f2,f2Dot);

        String uF1 = getUniformStr(tF1,f1Dot,f1,f1Zero);
        String uF2 = getUniformStr(tF2,f2Dot,f2,f2Zero);

        if(uF1.equals(uF2)){
            System.out.print("YES ");
            System.out.print(uF1);
        }else {
            System.out.print("NO ");
            System.out.print(uF1+" ");
            System.out.print(uF2);
        }
    }

    public int getZero(String str,int dot){
        if(dot==-1) return 0;
        int result = 0;
        for(int i=dot+1;i<str.length();i++){
            if(str.charAt(i)=='0')
                result++;
            else
                return result;
        }
        return result;
    }

    public String trim(String str){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(result.length()==0 && ch=='0' && i+1<str.length() && str.charAt(i+1)!='.') continue;
            result.append(ch);
        }
        return result.toString();
    }

    /**
     * 给定一个有n位有效数字的字符串以及它的小数点的位置，
     * 返回它的标准表示形式（0.xx*10^yy）
     * @param str
     * @param dotPos
     * @return
     */
    public String getUniformStr(String str,int dotPos,String initalStr,int zeroNumber){
        String result = "0."+str;
        // 补齐0
        if(str.length()<n){
            for(int i=str.length();i<n;i++)
                result+="0";
        }
        result += "*";
        if(str.equals("0")){
            result += "10^0";
            return result;
        }
        if(dotPos==-1){
            // 没有小数点
            result += "10^"+initalStr.length();
        }else if(dotPos>=n){
            //小数点在有效数字范围外
            result += "10^"+(initalStr.length()-(initalStr.length()-dotPos));
        } else{

            if(initalStr.charAt(dotPos-1)=='0' && dotPos==1) dotPos-=1;

            dotPos-=zeroNumber;

            // 小数点在有效数字范围内
            result += "10^"+dotPos;
        }
        return result;
    }

    public String split(String str){

        StringBuilder result = new StringBuilder();

        // 截取有效数字
        for(int i=0,j=0;j<n && i<str.length();i++){
            char ch = str.charAt(i);
            if(ch=='.') continue;

            if(ch=='0' && result.length()==0 && (i!=str.length()-1)) continue;

            result.append(ch);
            j++;
        }

        return result.toString();
    }
}
