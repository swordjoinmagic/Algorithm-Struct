package leetcode.StructPractice.array;

/**
 * 思路：
 *      1. 暴力。两层遍历，时间复杂度O(n2)
 *      2. 对撞指针法。
 *
 *      设left表示头指针，right表示尾指针。
 *
 *      按照以下步骤算出最大面积：
 *
 *      1. max = Math.max(max, left - right 的面积)
 *      2. left持续右移，直到找到一个A[left]大于当前值的，计算1
 *      3. right持续左移，直到找到一个A[right]大于当前值的，计算1
 *      4. 重复1,2,3，直到left==right
 *
 *      思路2错误。
 *
 *      3. 参考题解。双指针法。
 *      设置left表示头指针，right表示尾指针。
 *
 *      1. max = Math.max(max, [left -—— right] 的面积)
 *      2. 较短那一段向较长那一段前进
 */
public class Problem11盛最多水的容器 {

    public static void main(String[] args){
        int[] nums = new int[]{2,3,10,5,7,8,9};
        Problem11盛最多水的容器 problem11 = new Problem11盛最多水的容器();
        int result = problem11.maxArea(nums);
        System.out.println(result);
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0;

        while (left<right){
            max = Math.max(max, (right-left)*Math.min(height[left],height[right])  );
            if(height[left]>height[right]){
                right--;
            }else {
                left++;
            }
        }
        return max;
    }
}
