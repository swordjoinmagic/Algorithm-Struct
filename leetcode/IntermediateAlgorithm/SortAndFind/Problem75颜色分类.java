package leetcode.IntermediateAlgorithm.SortAndFind;

/**
 * 思路:
 *      1. 计数排序,扫描一趟记录红/白/蓝的数量,然后第二趟顺序赋值就好了
 */
public class Problem75颜色分类 {

    public static void main(String[] args){
        Problem75颜色分类 problem75 = new Problem75颜色分类();
        int[] nums = new int[]{1,0,0};
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
        if(nums.length==0 || nums.length==1) return;
        // 一次遍历,如果是0,移动到表头,如果是2,移动到表尾
        int startIndex = 0;
        int endIndex = nums.length-1;

        int i = 0;
        while (i<=endIndex && startIndex<=endIndex){
            if(nums[i]==2){
                swap(nums,i,endIndex);
                endIndex--;
            }else if(nums[i]==0){
                swap(nums,i,startIndex);
                startIndex++;
            }else
                i++;
        }
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
