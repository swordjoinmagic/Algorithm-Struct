package leetcode.StructPractice.QueueAndStack;

/**:
 * 思路:
 *      1. DFS深搜.与岛屿那道题相似.
 *      从初始坐标开始,向上下左右四个坐标深搜,
 *      只有像素值与初始坐标相同的像素才进行递归,
 *      每个像素值都被替换成新的颜色值
 */
public class Problem733图像渲染 {

    public static void main(String[] args){
        Problem733图像渲染 problem733 = new Problem733图像渲染();
        int[][] image = {
                {0,0,0},
                {0,1,1},
        };
        int sr = 1;
        int sc = 1;
        int newColor = 1;

        int[][] result = new Problem733图像渲染().floodFill(image,sr,sc,newColor);

        for(int[] l:result){
            for(int i:l){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    private int[][] dir = {
            {0,1},   // 下
            {0,-1},  // 上
            {1,0},   // 右
            {-1,0},  // 左
    };

    int srcColor;
    int col;    // 列数
    int row;    // 行数
    boolean[][] visted;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        row = image.length;
        col = image[0].length;
        srcColor = image[sr][sc];
        visted = new boolean[row][col];

        DFS(image,sr,sc,newColor);

        return image;
    }

    public void DFS(int[][] image, int x, int y, int newColor) {
        // 上色
        image[x][y] = newColor;
        // 标记访问
        visted[x][y] = true;

        for(int i=0;i<4;i++){

            int nextX = dir[i][0] + x;
            int nextY = dir[i][1] + y;

            if(
                    nextX<row && nextX>=0 &&
                    nextY<col && nextY>=0 &&
                    image[nextX][nextY] == srcColor &&
                    !visted[nextX][nextY]
            ){
                DFS(image,nextX,nextY, newColor);
            }
        }
    }
}
