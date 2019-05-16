package leetcode.AdvancedAlgorithm;

/**
 * 要求:
 *      1. 不能修改原数组
 *      2. 空间复杂度为O(1)
 *      3. 时间复杂度小于O(n2)
 *
 * 思路:
 *      1. 暴力.时间复杂度O(n2)
 *      2. 参考评论区大神,将问题抽象成链表问题,
 *      这样就是一个链表判环问题,使用快慢指针解决
 *      链表在循环节处就是重复的数字
 *
 *      关键在于,快慢指针只能判有无环,对于要找到循环点,
 *      要再开一个循环定位环入口,需要数学证明
 *
 *      时间复杂度O(N)
 *
 *      3. 参考博客大神.鉴于解法2正常人就算想到了,也不知道怎么找循环节,
 *      这里用一下比较正常的二分解法.
 *
 *          a. 首先找到数组的中数
 *          b. 在区间[1,n]搜索小于 中位数 的数有多少个
 *          c. 如果个数小于等于mid,说明 重复数字 在[mid+1,n]之间,
 *          反之重复值在[1,mid-1]之间,以此类推
 *
 */
public class Problem287寻找重复数 {

    public static void main(String[] args){
        Problem287寻找重复数 problem287 = new Problem287寻找重复数();
        int[] nums = {1,3,4,2,2};
        int result = problem287.findDuplicate(nums);
        System.out.println(result);
    }

    public int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length;
        while (left<right){
            int mid = left + (right-left)/2;
            int count = 0;
            for(int num : nums){
                if(num<=mid) count++;
            }
            if(count<=mid)
                left = mid+1;
            else
                right = mid;
        }

        return right;
    }
}
