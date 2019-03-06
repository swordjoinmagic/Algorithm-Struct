package leetcode.PrimaryAlgorithm.sortAndFind;

/**
 * 思路，二分搜索
 *
 * 1. 如果当前版本是false（即不是错误版本），那么版本号向前一半搜索
 * 2. 如果当前版本是true,检查是否前一个是False，
 * 如果前一个是False，那么这就是第一个错误版本，否则向后一半搜索
 */
public class Problem278第一个错误的版本 {

    public static void main(String[] args){
        int result = new Problem278第一个错误的版本().firstBadVersion(2147483647);
        System.out.println(result);
    }

    boolean isBadVersion(int version){
        if(version>=2147483647) return true;
        else return false;
    }

    public int firstBadVersion(int n) {

        int left = 1;
        int right = n;
        int mid = left + (right-left)/2;
        while (left<=right){
            System.out.println("left:"+left+" right:"+right+" mid:"+mid+" isBadVersion:"+isBadVersion(mid));
            if(!isBadVersion(mid)){
                // 向前一半搜索
                // 下界增大
                left = mid + 1;
            }else {
                if(mid==1 || !isBadVersion(mid-1)){
                    // 当前版本为错误版本，且前一个版本不为错误版本
                    return mid; // 第一个错误版本是当前版本
                }else {
                    // 向后一半查找
                    // 上界缩小
                    right = mid - 1;
                }
            }
            mid = left + (right-left)/2;
        }

        return 1;
    }
}
