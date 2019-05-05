package leetcode.StructPractice.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路:
 *      1. 对矩阵中每个元素都进行一次宽度优先搜索.
 *      类似于对矩阵每个元素进行一次迪杰斯特拉,
 *      时间复杂度O(n3)
 *      2. 考虑从0出发.在0周围的数,如果非0,那么他距离0的步数必定为1,
 *      以此类推,这些距离0步数为1的数,周围的非0数距离0的步数必定为 到0为步数1的数的步数 + 1
 *      时间复杂度O(n2)
 */
public class Problem541_01矩阵 {

    public static void main(String[] args){
        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        int[][] result = new Problem541_01矩阵().updateMatrix(matrix);

        for(int[] l:result){
            for(int i:l){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public int[][] updateMatrix(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] result = new int[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                result[i][j] = BFS(0,i,j,matrix);
            }
        }

        return result;
    }

    private int[][] dir = {
            {0,1},   // 下
            {0,-1},  // 上
            {1,0},   // 右
            {-1,0},  // 左
    };

    class Node{
        public int x,y;
        public int value;
        public Node(int x,int y,int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    boolean[][] visited;

    /**
     *
     * @param destValue 要达到的目标位置的值
     * @param srcX  初始x坐标
     * @param srcY  初始y坐标
     * @return  返回从初始坐标到达目标位置所需最小步数
     */
    public int BFS(int destValue,int srcX,int srcY,int[][] matrix){

        int step = -1;

        if(matrix[srcX][srcY]==destValue) return 0;

        Node root = new Node(srcX,srcY,matrix[srcX][srcY]);

        Queue<Node> queue = new LinkedList<>();

        int row = matrix.length;
        int col = matrix[0].length;
        visited = new boolean[row][col];

        // 加入初始值
        queue.add(root);

        while (!queue.isEmpty()){

            step++;

            int size = queue.size();

            for(int i=0;i<size;i++){

                Node currentNode = queue.poll();
                visited[currentNode.x][currentNode.y] = true;

                if(currentNode.value==destValue) return step;

                for(int j=0;j<4;j++){

                    int nextX = dir[j][0] + currentNode.x;
                    int nextY = dir[j][1] + currentNode.y;

                    if( nextX<row && nextX>=0 &&
                        nextY<col && nextY>=0 &&
                        !visited[nextX][nextY]){

                        Node nextNode = new Node(nextX,nextY,matrix[nextX][nextY]);
                        queue.add(nextNode);

                    }

                }
            }

        }

        return step;
    }
}
