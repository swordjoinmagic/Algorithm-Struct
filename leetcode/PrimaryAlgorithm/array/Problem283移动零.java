package leetcode.PrimaryAlgorithm.array;

/**
 * 移动零
 *
 * 思路：
 *      把0移到后面，相当于将非0元素移到前面
 *
 *      那么只要按顺序填好所有非0元素，后面全部设0就好了
 */
public class Problem283移动零 {

    public static void main(String[] args){
        int[] nums = new int[]{0,0,1};
        new Problem283移动零().slove2(nums);
    }

    private void print(int[] nums){
        for(int n : nums){
            System.out.print(n+" ");
        }
    }

    public void slove2(int[] nums){
        int nowIndex = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[nowIndex] = nums[i];
                nowIndex++;
            }
        }

        for(int i=nowIndex;i<nums.length;i++){
            nums[i] = 0;
        }
        print(nums);
    }

    public void moveZeroes(int[] nums) {
        // 已经放好的0的个数
        int zeroNum = 0;
        for(int i=nums.length-1;i>=0&&nums[i]==0;i--){
            zeroNum++;
        }
        for(int i=0;i<nums.length-zeroNum;i++){
            if(nums[i]==0){

                // 将该位数字冒泡到最后去
                for(int j=i+1;j<nums.length-zeroNum;j++){
                    swap(nums,j, j-1);
                }

                zeroNum++;
                i--;
            }
        }

        print(nums);
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
