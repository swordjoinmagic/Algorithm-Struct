package leetcode.StructPractice.ArrayAndString;

/**
 * 思路:
 *      1. 先将两个二进制数转为10进制,
 *      运行完加法后,再将10进制转为2进制
 *
 *      时间复杂度O(m) m是字符串长度
 *      思路1错误,当数字超过64位后无法计算
 *
 *      2. 考虑从加法的底层实现开始.
 *
 *      从个位数开始向上考虑,每次加法后进行进位操作
 */
public class Problem67二进制求和 {
    public static void main(String[] args){
        Problem67二进制求和 problem67 = new Problem67二进制求和();
        String result =  problem67.addBinary(
                "11","1"
        );
        System.out.println(result);
    }

    public String addBinary(String a, String b) {

        // 补足0
        StringBuilder sA = new StringBuilder(a);
        StringBuilder sB = new StringBuilder(b);

        while (sA.length()<sB.length())
            sA.insert(0,'0');
        while (sB.length()<sA.length())
            sB.insert(0,'0');

        // 进位信息
        int level = 0;
        int length = sA.length();
        StringBuilder result = new StringBuilder(length);

        for(int i=length-1;i>=0;i--){

            int valueA = sA.charAt(i) - '0';
            int valueB = sB.charAt(i) - '0';

            int resultValue = (valueA+valueB)%2;
            if(level>0){
                resultValue+=1; level--;
                if(resultValue>=2){
                    level++;
                    resultValue = resultValue%2;
                }
            }
            if(valueA+valueB>=2) {
                level++;    // 进位
            }

            result.insert(0,resultValue);
        }

        while (level-->0) result.insert(0,1);
        return result.toString();
    }
}
