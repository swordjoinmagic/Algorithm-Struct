package PAT.GradeB;

import java.util.Scanner;

public class PATProblem1004福尔摩斯的约会 {

    public static void main(String[] args){
        PATProblem1004福尔摩斯的约会 problem1004 = new PATProblem1004福尔摩斯的约会();
        problem1004.Slove();
    }

    int start = 0;

    // 从第一个字符串的start位置开始,
    // 找到这两个字符串第一个相同的大写英文字母(字母相同位置相同)位置
    // 返回该字母
    public int GetHighterAlphet(String str1,String str2,int start){
        for(int i=start;i<str1.length();i++){
            char ch = str1.charAt(i);
            if(ch<='Z' && ch>='A'){
                // 大写字母
                int index = str2.indexOf(ch,i);
                if(index!=-1 && index==i) {
                    this.start = i;
                    return ch - 'A' + 1;
                }
            }
        }
        return 0;
    }

    public char GetSmilarAlphetOrNumber(String str1,String str2,int start){
        for(int i=start;i<str1.length();i++){
            char ch = str1.charAt(i);
            int index = str2.indexOf(ch,i);
            if(index!=-1 && index==i) {
                return ch;
            }
        }
        return '0';
    }

    public int GetSmilarAlphet(String str1,String str2,int start){
        for(int i=start;i<str1.length();i++){
            char ch = str1.charAt(i);

            if( (ch<='z' && ch>='a') || (ch<='Z' && ch>='A') ){
                int index = str2.indexOf(ch,i);
                if(index!=-1 && index==i) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void Slove(){
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
        String str3 = in.next();
        String str4 = in.next();

        int day = GetHighterAlphet(str1,str2,0);
        char hour = GetSmilarAlphetOrNumber(str1, str2, this.start+1);
        int minute = GetSmilarAlphet(str3,str4, 0);

        System.out.println(GetDay(day)+" "+GetHour(hour)+":"+(minute<10?"0"+minute:minute));
    }

    public String GetHour(char hour){
        if(hour<='Z' && hour>='A'){
            String result = String.valueOf(hour-'A'+10);
            if(result.length()<=1)
                return "0"+result;
            else
                return result;
        }else {
            String result = String.valueOf(hour-'0');
            if(result.length()<=1)
                return "0"+result;
            else
                return result;
        }
    }

    public String GetDay(int day){
        switch (day){
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            default:
                return "SUN";
        }
    }
}
