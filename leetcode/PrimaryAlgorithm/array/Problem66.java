package leetcode.PrimaryAlgorithm.array;

public class Problem66 {

    public static void main(String[] args){
        int[] nums = new int[]{9,9};

        int[] result = new Problem66().plusOne(nums);


        for(int i:result){
            System.out.print(i+" ");
        }
    }

    public int[] plusOne(int[] digits) {

        // 是否进位
        boolean high = false;

        for(int i=digits.length-1;i>=0;i--){

            if(high) {
                digits[i] += 1;
            }

            if( (digits[i] >= 9 && !high) || (digits[i]>=10 && high)){
                digits[i] = 0;
                high = true;

                if(i==0){
                    int[] nums = new int[digits.length+1];
                    System.arraycopy(digits,0,nums, 1, digits.length);
                    nums[0] = 1;
                    return nums;
                }
            }else {
                if(!high){
                    digits[i] += 1;
                    break;
                }
                high = false;
                break;
            }
        }

        return digits;
    }
}
