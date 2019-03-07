package leetcode.PrimaryAlgorithm.mathProblem;

public class Problem326_3的幂 {
    public boolean isPowerOfThree(int n) {
        if(n==1) return true;
        while (n%3 == 0 && n!=0 && n!=1){
            n /= 3;
            if(n==1)
                return true;
        }
        return false;
    }

    /**
     * 进阶,不用循环或递归判断某个数是否是3的幂次方
     * @param n
     * @return
     */
    public boolean levelUp(int n){
        return false;
    }
}
