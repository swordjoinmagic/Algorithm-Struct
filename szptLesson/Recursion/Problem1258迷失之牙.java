package szptLesson.Recursion;

import java.util.Scanner;

/**
 * 思路:
 *      1. 深度优先,
 */
public class Problem1258迷失之牙 {

    public static void main(String[] args){
        Problem1258迷失之牙 problem1258 = new Problem1258迷失之牙();
        problem1258.Input();
    }

    // n行m列
    int n,m;

    // 迷宫
    int[][] map;

    // 标记数组,标记是否曾走过这个地方
    boolean[][] visited;

    int startX,startY;
    int endX,endY;
    boolean result;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                map[i][j] = in.nextInt();

        while (true){
            result = false;
            for(int i=0;i<n;i++) for(int j=0;j<m;j++) visited[i][j] = false;
            int power = in.nextInt();
            if(power==0) return;
            startX = in.nextInt();
            startY = in.nextInt();
            endX = in.nextInt();
            endY = in.nextInt();
            visited[startX][startY] = true;

            DFS(startX,startY,power);

            System.out.println(result);
        }
    }

    private void printMap(int x,int y){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==x && j==y)
                    System.out.print("M ");
                else
                    System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void DFS(int x,int y,int nowPower){
        // 边界判断
        if(x==endX && y==endY){
            result = true;
            return;
        }

        if(result) return;

//        printMap(x,y);

        int tempX,tempY;

        // 上
        tempX = x-1;
        tempY = y;
        try{
            if(!visited[tempX][tempY] && nowPower>=map[tempX][tempY]){

                // 向上走,标记
                visited[tempX][tempY] = true;

                DFS(tempX,tempY,nowPower-map[tempX][tempY]);

                // 取消标记
                visited[tempX][tempY] = false;
            }
        }catch (Exception e){}

        // 下
        tempX = x+1;
        tempY = y;
        try{
            if(!visited[tempX][tempY] && nowPower>=map[tempX][tempY]){

                // 向上走,标记
                visited[tempX][tempY] = true;

                DFS(tempX,tempY,nowPower-map[tempX][tempY]);

                // 取消标记
                visited[tempX][tempY] = false;
            }
        }catch (Exception e){}

        // 左
        tempX = x;
        tempY = y-1;
        try{
            if(!visited[tempX][tempY] && nowPower>=map[tempX][tempY]){

                // 向上走,标记
                visited[tempX][tempY] = true;

                DFS(tempX,tempY,nowPower-map[tempX][tempY]);

                // 取消标记
                visited[tempX][tempY] = false;
            }
        }catch (Exception e){}


        // 右
        tempX = x;
        tempY = y+1;
        try{
            if(!visited[tempX][tempY] && nowPower>=map[tempX][tempY]){

                // 向上走,标记
                visited[tempX][tempY] = true;

                DFS(tempX,tempY,nowPower-map[tempX][tempY]);

                // 取消标记
                visited[tempX][tempY] = false;
            }
        }catch (Exception e){}
    }
}
