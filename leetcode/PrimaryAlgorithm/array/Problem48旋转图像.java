package leetcode.PrimaryAlgorithm.array;

public class Problem48旋转图像 {

    public static void main(String[] args){
        int[][] matrix ={
            { 5, 1, 9,11},
            { 2, 4, 8,10},
            {13, 3, 6, 7},
            {15,14,12,16}
        };
        new Problem48旋转图像().rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        // 关于x轴对称
        for(int i=0;i<matrix.length/2;i++){
            for(int j=0;j<matrix.length;j++){
                swap(matrix, i, j, matrix.length-i-1, j);
            }
        }

        // 关于对角线对称
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<i;j++){
                swap(matrix,i,j,j, i);
            }
        }

    }

    public void swap(int[][] matrix,int x1,int y1,int x2,int y2){
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    private void print(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
