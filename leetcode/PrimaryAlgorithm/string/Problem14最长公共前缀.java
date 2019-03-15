package leetcode.PrimaryAlgorithm.string;

/**
 * 思路:
 *      1. 暴力,遍历字符串数组中每一个字符串,
 *      得到所有公共前缀,然后把最长的挑出来
 *      时间复杂度O(MN),M为数组长度,N为字符串长度
 */
public class Problem14最长公共前缀 {

    public static void main(String[] args){
        Problem14最长公共前缀 problem14 = new Problem14最长公共前缀();
        String result = problem14.longestCommonPrefix(new String[]{"aca","cba"});
        System.out.println(result);
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        // 最长公共前缀
        String result = "";

        String temp = "";

        String templeate = strs[0];

        for(int i=0;i<templeate.length();i++){

            char ch = templeate.charAt(i);
            for(int j=1;j<strs.length;j++){
                if(i>=strs[j].length()) {
                    return result;
                }
                if(strs[j].charAt(i)!=ch){
                    return result;
                }
            }

            temp += ch;
            if(temp.length() > result.length())
                result = temp;
        }

        return result;
    }
}
