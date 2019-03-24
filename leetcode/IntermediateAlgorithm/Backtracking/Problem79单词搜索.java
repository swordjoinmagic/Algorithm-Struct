package leetcode.IntermediateAlgorithm.Backtracking;

import java.util.Arrays;

/**
 * 思路:
 *      1. 深度优先搜索
 */
public class Problem79单词搜索 {

    public static void main(String[] args){
        Problem79单词搜索 problem79 = new Problem79单词搜索();
        char[][] boards = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        boolean result = problem79.exist(boards,"ABCB");

        System.out.println(result);
    }

    private boolean[][] visited;
    private boolean result;
    private int[][] pace = new int[][]{
            {0,1},  // 下
            {0,-1}, // 上
            {1,0},  // 右
            {-1,0}  // 左
    };

    // 行数
    private int row;
    // 列数
    private int col;

    private void DFS(int x,int y,int pos,String requiredStr,char[][] borad){

        if(result)
            return;

        if(pos == requiredStr.length()){
            result = true;
            return;
        }

        for(int i=0;i<4;i++){

            int tempX = x+pace[i][0];
            int tempY = y+pace[i][1];

            // 越界处理
            if(tempX < row && tempX>=0 && tempY<col && tempY>=0){

                // 判断目标方向是否是需要的字符串
                if(!visited[tempX][tempY] && borad[tempX][tempY] == requiredStr.charAt(pos)){

                    // 标记
                    visited[tempX][tempY] = true;

                    DFS(tempX,tempY,pos+1, requiredStr, borad);

                    // 取消标记
                    visited[tempX][tempY] = false;

                }

            }
        }
    }

    public boolean exist(char[][] board, String word) {
        row = board.length;
        if(row<=0) return true;
        col = board[0].length;
        result = false;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==word.charAt(0)){

                    // 清空visited数组
                    visited = new boolean[row][col];
                    visited[i][j] = true;

                    DFS(i,j,1,word,board);
                    if(result) return true;

                }
            }
        }

        return result;
    }
}
