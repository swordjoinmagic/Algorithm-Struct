package leetcode.StructPractice.ArrayAndString;

/**
 * 思路：
 *      1. 对角线遍历方向与层数有关。
 *      奇数层向右上，偶数层向左下。
 */
public class Problem498对角线遍历 {
    public int[] findDiagonalOrder(int[][] matrix) {
        // 层数
        int layers = 1;

        int row = matrix.length;
        int col = matrix[0].length;

        // 遍历的元素个数
        int[] nums = new int[row*col];
        int n = 0;

        boolean[][] visited = new boolean[row][col];

        int x=0,y=0;

        while (n!=row*col){
            if(layers%2!=0){
                // 奇数层，向右上遍历
                if(y==col-1)
                    // 向右推进一格
                    nums[n++] = matrix[x][++y];
                else if(x==0)
                    // 向下推进一格
                    nums[n++] = matrix[x++][y];
                else {
                    // 向右上遍历到不能遍历为止
                    while (x >= 0 && y >= 0 && y < col) {
                        nums[n++] = matrix[x++][y++];
                    }
                    x--;
                    y--;    // 复原
                }
            }else {
                // 偶数层，向左下遍历
                if(y==0)
                    nums[n++] = matrix[x++][y];
                else if(x==row-1)
                    nums[n++] = matrix[x][y--];
                else {
                    while (x >= 0 && y >= 0) {
                        nums[n++] = matrix[x--][y--];
                    }
                    x++;
                    y++;    // 复原
                }
            }

            // 层数+1
            layers++;
        }

        return null;
    }
}
