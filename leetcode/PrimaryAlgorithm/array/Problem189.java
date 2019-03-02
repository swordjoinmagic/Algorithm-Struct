package leetcode.PrimaryAlgorithm.array;


import javafx.scene.transform.Rotate;

/**
 *
 * 特点
 * 1. 每移动[数组长度]个位置,数组回归原样.
 * 2. 向右移动k次,表示原本下标为i的元素,移动后下标为 (i+k) % len
 */
public class Problem189 {

    public void rotate(int[] nums, int k) {

        int len = nums.length;

        // 每移动k次,数组回归原样
        k %= len;

        // 模拟k次
        for(int i=0;i<k;i++){
            rotateNums(nums,k);
        }
    }

    // 数组右移一位
    private void rotateNums(int[] nums,int k){

        int temp = nums[nums.length-1];
        for(int i=nums.length-1;i>0;i--){
            nums[i] = nums[i-1];
        }
        nums[0] = temp;
    }

}
