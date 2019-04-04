package leetcode.StructPractice.array;

/**
 * 思路：
 *      1. 与移动零（题号283）类似的思路。
 *      设fastIndex在遇到非val的元素时，跳过
 *      index表示当前下标
 */
public class Problem27移除元素 {

    public static void main(String[] args){
        int[] nums = new int[]{3,2,2,3};
        int len = new Problem27移除元素().removeElement(nums,3);
        for(int i=0;i<len;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        System.out.println(len);
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        int fastIndex = 0;
        while (fastIndex<nums.length){
            if(nums[fastIndex]==val)
                fastIndex++;
            else {
                nums[index++] = nums[fastIndex++];
            }
        }
        return index;
    }
}
