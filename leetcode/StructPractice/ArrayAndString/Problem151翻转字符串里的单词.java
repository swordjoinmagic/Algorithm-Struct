package leetcode.StructPractice.ArrayAndString;

/**
 *  思路:
 *       1. 类似于计数排序?
 *       从后往前,获得每一个单词,然后将单词append到stringBuidler中
 */
public class Problem151翻转字符串里的单词 {

    public static void main(String[] args){
        String s = "  hello world!  ";
        Problem151翻转字符串里的单词 problem151 = new Problem151翻转字符串里的单词();
        String result = problem151.reverseWords(s);
        System.out.println(result);
    }

    public String reverseWords(String s) {
        s = s.trim();
        int start = s.length();

        StringBuilder result = new StringBuilder();

        while (true){
            // 跳过空格
            while (start>0 && s.charAt(start-1) == ' ') start--;

            Object[] parms = getNextWord(s,start-1);
            String word = (String) parms[1];
            start = (int)parms[0];
            if(start==-1){
                result.append(word);
                break;
            }else {
                result.append(word).append(" ");
            }
        }

        return result.toString();
    }


    /**
     * 从后往前获得每个单词,返回一个Object数组,
     * 数组样例如下:
     *
     * [index,word]
     *
     * 第一个元素是单词的头指针下标,第二个元素是单词
     * @param s
     * @param start
     * @return
     */
    public Object[] getNextWord(String s,int start){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i=start;i>=0;i--){
            char ch = s.charAt(i);
            if(ch!=' '){
                stringBuilder.insert(0,ch);
            }else {
                i++;
                break;
            }
        }
        return new Object[]{i,stringBuilder.toString()};
    }
}
