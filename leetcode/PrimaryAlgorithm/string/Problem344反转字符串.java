package leetcode.PrimaryAlgorithm.string;

public class Problem344反转字符串 {

    public static void main(String[] args){
        char[] str = {'h','e','l','l','o'};
        new Problem344反转字符串().reverseString(str);
    }

    public void reverseString(char[] s) {
        for(int i=0;i<s.length/2;i++){
            swap(s,i,s.length-1-i);
        }
    }

    private void swap(char[] s,int i,int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
