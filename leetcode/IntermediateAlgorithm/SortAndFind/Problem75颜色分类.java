package leetcode.IntermediateAlgorithm.SortAndFind;

/**
 * 思路:
 *      1. 计数排序,扫描一趟记录红/白/蓝的数量,然后第二趟顺序赋值就好了
 */
public class Problem75颜色分类 {

    public static void main(String[] args){
        Problem75颜色分类 problem75 = new Problem75颜色分类();
        int[] nums = new int[]{2,0,2,1,1,0};
        problem75.sortColorsOnce(nums);
        for(int data : nums){
            System.out.print(data+" ");
        }
    }

    public void sortColors(int[] nums) {
        int[] rgb = new int[3];
        for(int data : nums){
            rgb[data] += 1;
        }
        int index = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<rgb[i];j++)
                nums[index++] = i;
        }
    }

    public void sortColorsOnce(int[] nums){
        int start = 0;
        int end = nums.length-1;

        int startIndex = 0;
        int endIndex=  nums.length-1;

        while (start<end){
            // 从前往后找2
            while (start<end && nums[start]!=2) start++;
            if(start<end) {
                swap(nums, start, endIndex);
                endIndex--;
            }

            // 从后往前找0
            while (start<end && nums[end]!=0) end--;
            if(start<end) {
                swap(nums, end, startIndex);
                startIndex++;
            }
        }

    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
