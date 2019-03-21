package leetcode.IntermediateAlgorithm.TreeAndGraph;

/**
 * 思路：
 *      1. 深度优先,每次进去,岛屿数量加1,之后,将遇到的每一个
 *      为"1"的点(即陆地)设为已访问,之后深度时,不深度这些已访问的点
 */
public class Problem200岛屿的个数 {

    public static void main(String[] args){
        char[][] chars = new char[][]{
                {'1','1','0','1','0'},
                {'1','0','0','1','0'},
                {'0','1','0','0','0'},
                {'1','1','1','1','0'}
        };
        Problem200岛屿的个数 problem200 = new Problem200岛屿的个数();
        int result = problem200.numIslands(chars);
        System.out.println(result);
    }

    public int numIslands(char[][] grid) {
        if(grid.length<=0) return 0;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){

                // 遇到陆地,且陆地未被访问过
                if(grid[i][j]=='1' && !visited[i][j]){
                    // 岛屿数量+1
                    count ++;
                    DFS(i,j,grid);
                }

            }
        }
        return count;
    }

    // 标记数组
    private boolean[][] visited;
    // 用于上下左右走的数组
    private int[][] pace = new int[][]{
            {0,1},  // 下
            {0,-1}, // 上
            {1,0},  // 右
            {-1,0}  // 左
    };
    // 行数
    int row;
    // 列数
    int col;
    // 岛屿数量
    private int count;

    /**
     * x是行数,y是列数
     * @param x
     * @param y
     */
    private void DFS(int x,int y,char[][] grid){
        for(int i=0;i<4;i++){
            int targetX = pace[i][0] + x;
            int targetY = pace[i][1] + y;

            // 越界判断
            if(targetX<row && targetX>=0 && targetY<col && targetY>=0){
                // 如果是陆地,继续深度
                if(grid[targetX][targetY]=='1' && !visited[targetX][targetY]){
                    visited[targetX][targetY] = true;
                    DFS(targetX,targetY,grid);
                }
            }
        }
    }
}
