package PAT.GradeA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题目描述:
 *      1. 参考评论区
 *      2. 给定一个M x N x L的三维数组,求里面联通的1的个数,个数小于T的不予计算
 *
 * 思路：
 *      1. DFS.
 */
public class PAT1004AcuteStroke {

    public static void main(String[] args){
        PAT1004AcuteStroke pat1004 = new PAT1004AcuteStroke();
        pat1004.Input();
    }

    // 三维数组的x,y,z轴
    int M,N,L;
    // 阈值
    int T;

    int[][][] graph;

    boolean[][][] visited;

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();
        L = in.nextInt();
        T = in.nextInt();

        graph = new int[L][M][N];

        for(int l=0;l<L;l++){
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    graph[l][i][j] = in.nextInt();
                }
            }
        }

        Slove();
    }

    public void Slove(){
        int ans = 0;
        visited = new boolean[L][M][N];
        for(int l=0;l<L;l++){
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    if(!visited[l][i][j] && graph[l][i][j]==1){
                        visited[l][i][j] = true;
                        int total = BFS(l,i,j)+1;
                        if(total>=T) ans+=total;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    int[][] dir = new int[][]{
            {-1,0,0},  // 垂直上
            {1,0, 0},  // 垂直下
            {0,-1,0},  // 平面上
            {0, 1,0},  // 平面下
            {0,0,-1},  // 平面左
            {0,0, 1},  // 平面右
    };

    class Node{
        int x, y, z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString(){
            return String.format("[%d,%d,%d]",x,y,z);
        }
    }

    public int BFS(int x,int y,int z){
        int result = 0;
        Node root = new Node(x,y,z);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            for(int i=0;i<dir.length;i++){
                int nextX = node.x+dir[i][0];
                int nextY = node.y+dir[i][1];
                int nextZ = node.z+dir[i][2];
                if( nextX>=0&&nextY>=0&&nextZ>=0 &&
                    nextX<L && nextY<M && nextZ<N &&
                    !visited[nextX][nextY][nextZ] &&
                    graph[nextX][nextY][nextZ] == 1
                ){
                    visited[nextX][nextY][nextZ] = true;
                    result ++;
                    Node nextNode = new Node(nextX,nextY,nextZ);
                    queue.add(nextNode);
                }
            }
        }
        return result;
    }

    public int DFS(int x,int y,int z){
        visited[x][y][z] = true;
        int result = 0;
        for(int i=0;i<dir.length;i++){
            int nextX = x+dir[i][0];
            int nextY = y+dir[i][1];
            int nextZ = z+dir[i][2];
            if( nextX>=0&&nextY>=0&&nextZ>=0 &&
                nextX<L && nextY<M && nextZ<N &&
                !visited[nextX][nextY][nextZ] &&
                graph[nextX][nextY][nextZ] == 1
            ){
                visited[nextX][nextY][nextZ] = true;
                result += DFS(nextX,nextY,nextZ) + 1;
            }
        }

        return result;
    }
}
