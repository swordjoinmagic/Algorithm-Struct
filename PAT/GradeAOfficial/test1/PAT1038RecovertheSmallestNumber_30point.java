package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给出一堆数字,求他们排列组合生成的最小数字
 */
public class PAT1038RecovertheSmallestNumber_30point {

    public static void main(String[] args){
        PAT1038RecovertheSmallestNumber_30point pat1038 = new PAT1038RecovertheSmallestNumber_30point();
        pat1038.Input();
    }

    String[] nums;

    int N;

    // 目前最高位
    int hightestBit;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        nums = new String[N];

        for(int i=0;i<N;i++){
            nums[i] = String.valueOf(in.nextInt());
        }

        Slove();
    }

    public void Slove(){
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        StringBuilder result = new StringBuilder();
        for(String str : nums) result.append(str);
        System.out.println(result.toString());
    }

}
