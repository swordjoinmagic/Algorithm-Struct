package leetcode.PrimaryAlgorithm.string;

public class Problem125验证回文字符串 {
    public static void main(String[] args){
        String s = "abbaa;a4:,.'/][-";

        Problem125验证回文字符串 problem125 = new Problem125验证回文字符串();
        System.out.println(problem125.isPalindrome(s));
    }

    public boolean isPalindrome(String s) {
        if(s.length()==0)
            return true;
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z0-9]","");
        for(int i=0;i<s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length()-i-1))
                return false;
        }
        return true;
    }

    public boolean isNumOrAlphet(char c){
        if((c>='0' && c<='9') || (c>='a' && c<='z'))
            return true;
        else
            return false;
    }
}
