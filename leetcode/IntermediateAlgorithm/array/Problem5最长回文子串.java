package leetcode.IntermediateAlgorithm.array;

/**
 * 思路:
 *      1. 暴力,遍历n2遍,找到所有子串并判断回文,时间复杂度O(n3)
 *      2. DP
 *      设P(i,j)表示子串i~j位置是否是回文子串,是则为true,不是则为false
 *      P(i,j) = P(i+1,j-1) and Si==Sj
 *
 *      P(i,i) = true
 *      P(i,i+1) = (Si==Si+1)
 */
public class Problem5最长回文子串 {

    public static void main(String[] args){
        String s = "abcba";
        Problem5最长回文子串 problem5 = new Problem5最长回文子串();
        String result = problem5.longestPalindrome(s);
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        boolean[][] p = new boolean[s.length()+1][s.length()+1];

        if(s.length()==0) return "";
        if(s.length()==1) return s;

        int maxLength = 0;
        int indexI = 0;
        int indexJ = 0;

        for(int i=0;i<s.length();i++)
            p[i][i] = true;

        p[0][1] = s.charAt(0)==s.charAt(1);

        if(p[0][1]){
            maxLength = 1;
            indexI = 0;
            indexJ = 1;
        }

        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++)
                if(j - i == 1){
                    p[i][j] = s.charAt(i)==s.charAt(j);

                    if(p[i][j] && j-i>maxLength){
                        maxLength = j-i;
                        indexI = i;
                        indexJ = j;
                    }
                }
        }

        for(int i=s.length()-1;i>=0;i--){
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j) && p[i+1][j-1]){
                    // 此子串是回文串
                    p[i][j] = true;

                    if(j-i>maxLength){
                        maxLength = j-i;
                        indexI = i;
                        indexJ = j;
                    }
                }

                print(p,s.length());
                System.out.println();
            }
        }

        return s.substring(indexI,indexJ+1);
    }

    public void print(boolean[][] p,int n){
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++)
                System.out.print(p[i][j] + " ");
            System.out.println();
        }
    }
}
