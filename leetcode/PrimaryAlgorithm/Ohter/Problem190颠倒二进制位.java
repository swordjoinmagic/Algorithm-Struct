package leetcode.PrimaryAlgorithm.Ohter;

/**
 * 思路:
 *      1. 将输入的整数转为二进制数
 *      2. 将该二进制数以string的形式反转
 *      3. 计算反转后的二进制数表示的整数形式
 */
public class Problem190颠倒二进制位 {

    public static void main(String[] args){
        Problem190颠倒二进制位 problem190 = new Problem190颠倒二进制位();
        int result = problem190.reverseBits(-1763388427);
        System.out.println(result);
//        System.out.println(1<<0);
    }

    public int reverseBits(int n) {
        StringBuilder result = new StringBuilder();

        int temp = n;
        if(temp<0){
            // 将该负数变成正整数
            temp += 1;
            temp += 2147483647;
        }
        System.out.println(temp);
        // 将输入的整数转为二进制数
        while (temp!=0){
            System.out.println("temp:"+temp+" temp%2:"+temp%2);
            result.insert(0, String.valueOf(temp%2));
            temp /= 2;
        }
        // 32位整数,0不够就补0
        while (result.length()<31)
            result.insert(0,String.valueOf(0));

        if(n<0)
            // 负数符号位
            result.insert(0, String.valueOf(1));
        else
            result.insert(0, String.valueOf(0));

        System.out.println("NoReverse:"+result.toString());

        // 反转该二进制数
        result = result.reverse();

        System.out.println("reverse  :"+result);

        // 将反转后的二进制数转为整数
        int total = 0;
        // 第一位是符号位,不计算
        for(int i=result.length()-1;i>=1;i--){
            if(result.charAt(i)-'0'==1)
                total += 1<<( (result.length()-1) - i);
        }

        if(result.charAt(0)=='1') total += Integer.MIN_VALUE;

        return total;

    }
}
