package leetcode.IntermediateAlgorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 *      1. 遍历一遍矩阵，找到0，然后将这些有0的位置存起来
 *      遍历一遍0的位置，将该位置上的行列设为0
 *      2. 使用两个布尔变量，标志矩阵首行和首列是否应该清0，
 *         遍历矩阵，下标从(1,1)开始，当前数是0的情况下，设
 *         当前数在第i行j列，设置A[i][0] = 0,A[0][j] = 0
 *         最后检查时，只需判断第一个元素是否为0，就知道是否
 *         要将当前行和列清0
 */
public class Problem73矩阵置零 {

    class Point{
        int x,y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    List<Point> list = new ArrayList<>();

    public void setZeros(int[][] matrix){
        if(matrix.length==0) return;;
        // 是否将第0行清零
        boolean isSet0RowZero = false;
        // 是否将第0列清0
        boolean isSet0ColZero = false;

        // 总共的行数
        int n = matrix.length;
        // 总共的列数
        int m = matrix[0].length;

        // 检查第0行第0列
        for(int i=0;i<n;i++){
            if(matrix[i][0]==0){
                isSet0ColZero = true;
                break;
            }
        }

        for(int j=0;j<m;j++){
            if(matrix[0][j]==0){
                isSet0RowZero = true;
                break;
            }
        }


        // 从第1行第1列开始遍历
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(matrix[i][j]==0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 检查第一行
        for(int j=1;j<m;j++){
            if(matrix[0][j]==0){
                for(int i=1;i<n;i++){
                    matrix[i][j] = 0;
                }
            }
        }
        // 检查第一列
        for(int i=1;i<n;i++){
            if(matrix[i][0]==0){
                for(int j=1;j<m;j++){
                    matrix[i][j] = 0;
                }
            }
        }

        // 检查标志位
        if(isSet0ColZero){
            for(int i=0;i<n;i++){
                matrix[i][0] = 0;
            }
        }

        if(isSet0RowZero){
            for(int j=0;j<m;j++){
                matrix[0][j] = 0;
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        if(matrix.length==0) return;

        int m = matrix.length;
        int n = matrix[0].length;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0) list.add(new Point(i,j));
            }
        }

        for(Point point : list){
            int x = point.x;
            int y =point.y;
            for(int i=0;i<m;i++)
                matrix[i][y] = 0;

            for(int i=0;i<n;i++)
                matrix[x][i] = 0;
        }
    }
}
