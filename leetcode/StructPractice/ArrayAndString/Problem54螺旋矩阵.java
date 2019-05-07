package leetcode.StructPractice.ArrayAndString;

import leetcode.Other.Problem547朋友圈;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 *      1. DFS.按照右、上、左、下进行遍历。
 *
 */
public class Problem54螺旋矩阵 {

    public static void main(String[] args){
        int[][] matrix = {
                { 1, 2, 3 ,4 },
                { 5, 6, 7, 8 },
                { 9,10,11,12 },
                {13,14,15,16 },
        };
        Problem54螺旋矩阵 problem54 = new Problem54螺旋矩阵();
        List<Integer> result = problem54.spiralOrder(matrix);
        for(int data : result) System.out.print(data+" ");
    }

    private boolean[][] visited;

    int row;
    int col;

    List<Integer> result;

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0) return new ArrayList<>();
        row = matrix.length;
        col = matrix[0].length;

        visited = new boolean[row][col];
        visited[0][0] = true;
        result = new ArrayList<>();
        result.add(matrix[0][0]);
        int[] position = new int[]{0,0};
        while (result.size()!=col*row){
            position = DFS(position[0],position[1],matrix,dir[0]);
            position = DFS(position[0],position[1],matrix,dir[1]);
            position = DFS(position[0],position[1],matrix,dir[2]);
            position = DFS(position[0],position[1],matrix,dir[3]);
        }


        return result;
    }

    int[][] dir = {
            {0,1},   // 右
            {1,0},  // 下
            {0,-1},  // 左
            {-1,0},   // 上
    };

    public int[] DFS(int x,int y,int[][] matrix,int[] dir){
        int nextX = x+dir[0];
        int nextY = y+dir[1];

        if(nextX>=0 && nextX<row && nextY>=0 && nextY<col &&
           !visited[nextX][nextY]){
            result.add(matrix[nextX][nextY]);
            visited[nextX][nextY] = true;
            return DFS(nextX,nextY,matrix,dir);
        }
        return new int[]{x,y};
    }
}
