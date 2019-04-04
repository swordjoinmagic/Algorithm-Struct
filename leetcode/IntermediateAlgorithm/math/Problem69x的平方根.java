package leetcode.IntermediateAlgorithm.math;

/**
 * 思路:
 *      1. 二分查找.
 *      要求x的平方根,相当于在 1~x的范围内找到一个
 *      尽量大的数n,符合条件:
 *              n^2 <= x
 */
public class Problem69x的平方根 {

    public static void main(String[] args){
        Problem69x的平方根 problem69 = new Problem69x的平方根();
        int result = problem69.mySqrt(2147483647);
        System.out.println(result);
    }

    public int mySqrt(int x) {
        return binarySearch(x);
    }

    public int binarySearch(int x){
        int left = 1;
        int right = x>=46341 ? 46341 : x;
        while (left<=right){

            boolean isMaxInteger = false;

            int mid = left+(right-left)/2;
            int tempResult = mid>=46341 ? Integer.MAX_VALUE : mid*mid;
            if(mid>=46341) isMaxInteger = true;
            System.out.println(mid+"  tempResult:"+tempResult);
            if(isMaxInteger || tempResult>x){
                right = mid-1;
            }else if(tempResult<x)
                left = mid+1;
            else
                return mid;
        }
        return left-1;
    }
}
