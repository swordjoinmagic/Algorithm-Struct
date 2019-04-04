package leetcode.IntermediateAlgorithm.math;

/**
 * 思路:
 *      1. 相当于算27进制的一个数
 */
public class Problem171Excel表列序号 {

    public static void main(String[] args){
        Problem171Excel表列序号 problem171Excel表列序号 = new Problem171Excel表列序号();
        int result = problem171Excel表列序号.titleToNumber("ZY");
        System.out.println(result);
    }

    public int titleToNumber(String s) {
        int result = 0;
        // 进位
        int count = 0;
        for(int i=s.length()-1;i>=0;i--){
            int number = s.charAt(i)-'A'+1;
            result += pow(26,count)*number;
            count++;
        }
        return result;
    }

    /**
     * 返回n的k次方
     * @param n
     * @param k
     * @return
     */
    public int pow(int n,int k){
        if(k==0) return 1;
        if(k==1)
            return n;
        // 快速幂
        if(k%2==0){
            int pow2 = pow(n,k/2);
            return pow2 * pow2;
        }else {
            int pow2 = pow(n,k/2);
            return pow2*pow2*n;
        }
    }
}
