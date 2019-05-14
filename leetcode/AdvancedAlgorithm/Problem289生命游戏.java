package leetcode.AdvancedAlgorithm;

/**
 *   进阶:
*         使用O(1)的空间复杂度
 *   思路:
 *      1.使用原地数组标记那些死亡或者复活的格子(只有它们会干扰后续计算)
 *      当一个格子的活细胞死亡时,记录为-2,当一个格子的死细胞复活时,记录为2
 *      最后更新完整个细胞后,将所有记录为-2的格子变为0,记录为2的格子变为1
 */
public class Problem289生命游戏 {

    public static void main(String[] args){
        Problem289生命游戏 problem289 = new Problem289生命游戏();
        int[][] array = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        problem289.gameOfLife(array);
        for(int[] l:array){
            for(int i:l) System.out.print(i+" ");
            System.out.println();
        }
    }

    int[][] dir = {
            {-1,-1},    // 左上
            {-1,0},     // 上
            {-1,1},     // 右上
            {0,-1},     // 左
            {0,1},      // 右
            {1,-1},     // 左下
            {1,0},      // 下
            {1,1}       // 右下
    };

    /**
     * 返回board处的细胞是否处于存活状态
     * @param borad
     * @param a
     * @param b
     * @return
     */
    public boolean isLive(int[][] borad,int a,int b){
        if(borad[a][b]==1 || borad[a][b]==-2)
            return true;
        else
            return false;
    }

    /**
     * 更新在(a,b)格子的细胞
     * @param board
     * @param a
     * @param b
     */
    public void check(int[][] board,int a,int b){
        int row = board.length;
        int col = board[0].length;

        // 记录该细胞周围的活细胞数量
        int total = 0;

        for(int i=0;i<dir.length;i++){
            int nextX = a+dir[i][0];
            int nextY = b+dir[i][1];
            if(nextX>=0 && nextX<row && nextY>=0 && nextY<col){
                if(isLive(board,nextX,nextY))
                    total++;
            }
        }

        if(isLive(board,a,b)){
            // 如果当前格子是活细胞
            if(total<2 || total>3)
                // 活细胞死亡
                board[a][b] = -2;
        }else {
            // 如果当前格子是死细胞
            if(total==3)
                // 死细胞复活
                board[a][b] = 2;
        }
    }

    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                check(board,i,j);
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==-2){
                    board[i][j] = 0;
                }else if(board[i][j]==2){
                    board[i][j] = 1;
                }
            }
        }
    }
}
