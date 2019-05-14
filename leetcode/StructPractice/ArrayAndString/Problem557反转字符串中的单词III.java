package leetcode.StructPractice.ArrayAndString;

public class Problem557反转字符串中的单词III {

    public static void main(String[] args){
        String s = "Let's take LeetCode contest";
        Problem557反转字符串中的单词III problem557 = new Problem557反转字符串中的单词III();
        String result = problem557.reverseWords(s);
        System.out.println(result);
    }

    public String reverseWords(String s) {
        s = s.trim();
        int start = s.length();

        StringBuilder result = new StringBuilder();

        String[] strs = s.split(" ");
        for(int i=0;i<strs.length;i++) {
            String str = strs[i];
            result.append(Reverse(str));
            if(i<strs.length-1)
                result.append(" ");
        }

        return result.toString();
    }

    public String Reverse(String s){
        char[] chars = s.toCharArray();

        int start = 0;
        int end = chars.length-1;

        while (start<end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }

        return new String(chars);
    }

}
