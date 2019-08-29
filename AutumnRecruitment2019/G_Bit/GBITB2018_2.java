package AutumnRecruitment2019.G_Bit;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 思路:
 *      1. 暴力,O(n2)复杂度.
 *      将问题转化为求一条直线过点i,直线上点的数量为最多
 */
public class GBITB2018_2 {

    public static void main(String[] args){
        GBITB2018_2 gbitb2018_2 = new GBITB2018_2();
        gbitb2018_2.Input();
    }

    class Point{
        int x,y,z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    // 方向向量
    class Vector implements Comparable<Vector>{
        double x,y,z;

        // 归一化,即向量每个分量除于它的长度
        public void Normalize(){
            double mangitude = Math.sqrt(x*x+y*y+z*z);
            x/=mangitude; y/=mangitude; z/=mangitude;
        }

        public Vector(int x1,int y1,int z1,
                      int x2,int y2,int z2){
            // 求向量AB,则AB = -A + B
            x = x2-x1;
            y = y2-y1;
            z = z2-z1;
        }

        @Override
        public String toString(){
            return String.format("%f %f %f",x,y,z);
        }

        @Override
        public int compareTo(Vector o) {
            if(x==o.x && y==o.y && z==o.z)
                return 0;
            else if(x>o.x)
                return 1;
            else
                return -1;
        }
    }

    int maxCount = 0;

    int N;

    Point[] points;

    HashMap<String,Integer> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        points = new Point[N];

        for(int i=0;i<N;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            points[i] = new Point(x,y,z);
        }

        Slove();
    }

    public void Slove(){
        for(int i=0;i<N;i++){

            Point a = points[i];
            map.clear();

            // 计算经过点i的直线上的点的最多数量的点
            for(int j=0;j<N;j++){
                if(i==j) continue;

                Point b = points[j];

                Vector vector = new Vector(a.x,a.y, a.z,b.x,b.y,b.z);

                map.put(vector.toString(),map.getOrDefault(vector.toString(), 0)+1);
                if(map.get(vector.toString())>maxCount)
                    maxCount = map.get(vector.toString());

            }

            System.out.println();
        }

        System.out.println(maxCount);
    }
}
