package leetcode.competition.Competition2019_3_24;

/**
 * 思路：
 *      1. 遍历i，j，时间复杂度O(n2),结果：时间超限
 */
public class Problem1020将数组分成和相等的三个部分 {

    public static void main(String[] args){
        int[] A = new int[]{0,2,1,-6,6,-7,9,1,2,0,1};
        int[][] total = new int[A.length][A.length];
        for(int i=0;i<A.length;i++){
            total[i][i] = A[i];
            for(int j=i+1;j<A.length;j++){
                total[i][j] = A[j]+total[i][j-1];
            }
        }

        for(int i=0;i<A.length;i++){
            for(int j=i+1;j<A.length;j++){
                System.out.print("total["+i+"]["+j+"]:"+total[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean canThreePartsEqualSum(int[] A) {

        // 打表，遍历一次，获得所有范围内的总和
        int[][] total = new int[A.length][A.length];
        for(int i=0;i<A.length;i++){
            total[i][i] = A[i];
            for(int j=i+1;j<A.length;j++){
                total[i][j] = A[j]+total[i][j-1];
            }
        }

        for(int i=0;i<A.length;i++){
            for(int j=i+1;j<A.length;j++){
                int total1 = total[0][i];
                int total2 = total[i+1][j-1];
                int total3 = total[j][A.length-1];
                if(total1==total2 && total2==total3){
                    return true;
                }
            }
        }

        return false;
    }
}
