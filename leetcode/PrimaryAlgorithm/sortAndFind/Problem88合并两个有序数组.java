package leetcode.PrimaryAlgorithm.sortAndFind;

/**
 * 使用直接插入排序,算法复杂度O(n2)
 */
public class Problem88合并两个有序数组 {

    public static void main(String[] args){
        Problem88合并两个有序数组 problem88 = new Problem88合并两个有序数组();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        problem88.merge(nums1,3,nums2,3);
        problem88.Print(nums1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 直接插入排序,将nums2中的元素插入到有序数组nums1中
        for(int i=0;i<n;i++){
            int data = nums2[i];

            // 找到data插入的地方
            int pos;
            for(pos=m-1;pos>=0;pos--){
                if(data<=nums1[pos]){
                    nums1[pos+1] = nums1[pos];
                }else break;
            }
            // data插入pos位置
            nums1[pos+1] = data;
            m++;
        }
    }

    public void Print(int[] nums){
        for(int data : nums){
            System.out.print(data+" ");
        }
        System.out.println();
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
