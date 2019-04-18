package leetcode.StructPractice.LookupTable;

import java.util.HashMap;
import java.util.Map;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

/**
 * 思路：
 *
 *      1. 暴力。找到平面上所有直线（利用两点式），
 *      然后遍历其余所有点，判断当前点是否在直线上。
 *      时间复杂度O(n3)
 *
 *      需要考虑特殊情况：
 *
 *      a. 斜率不存在的情况，即直线平行于x轴、y轴的情况（两点式不适用与这种情况），
 *         需要额外考虑
 *      b. 两点重合时，无法确定一条直线，但这两点是百分百共线的，
 *         需要额外考虑
 *      c. 避免使用除法，因为精度问题
 *
 *      2. 使用Map记录。
 *      思路是，
 *
 *      1. 当斜率存在时，用 y = kx+b 的形式表示一条直线，已知两点A(x1,y1),B(x2,y2),
 *      k = (y2-y1) / (x2-x1)，这里需要注意的是，因为浮点数精度问题，不要进行除法运算，
 *      而是将除法变成分数，再将分数化为最简形式。
 *      根据求得的k，再求得b的分数形式，再将b化为最简形式。
 *
 *      此时将能唯一表示一条直线的 “k+b” 作为键存入Map，每次遇到相同的斜率和b，就自增
 *
 *      2. 当斜率不存在时。以 "x=xx","y=xx" 的形式来作为键存入Map，每次遇到相同的键，就自增
 *
 *      3. 重复点的情况。以“x,y”作为键值存入map，记录重复点的数量。
 *      最后计算共线点数量时，代入直线的k值和b值，判断直线是否经过重复点
 *
 *      只需遍历两层即可解决，时间复杂度O(n2)
 */

// 分数
class Fraction{
    // 分子
    public int a;
    // 分母
    public int b;

    public Fraction(int a,int b){
        this.a = a;
        this.b = b;
    }

    // 分数加法,例 2 + 1/2
    // 这里将2变为分母与要加减的分数一样,分子为分母*实质数字的组合,上栗可以表示为:
    // 4/2 + 1/2
    public static Fraction Add(Fraction left,int right){
        Fraction newRight = new Fraction(left.b*right,left.b);
        return Add(left,newRight);
    }

    public static Fraction Add(Fraction left,Fraction right){
        int a = left.a + right.a;
        return new Fraction(a,left.b);
    }

    public static Fraction Sub(Fraction left,int right){
        Fraction newRight = new Fraction(left.b*right,left.b);
        return Sub(left,newRight);
    }
    public static Fraction Sub(int left,Fraction right){
        Fraction newLeft = new Fraction(right.b*left,right.b);
        return Sub(newLeft,right);
    }
    public static Fraction Sub(Fraction left,Fraction right){
        int a = left.a - right.a;
        return new Fraction(a,left.b);
    }

    public static Fraction Mul(Fraction left,int right){
        int a = left.a * right;
        return new Fraction(a,left.b);
    }

    // 化简分数
    public void Normalize(){
        int gcd = GCD(a,b);
        a /= gcd;
        b /= gcd;
    }

    // 获得a和b之间的最大公约数
    private int GCD(int i,int j){
        return j==0 ? i:GCD(j,i%j);
    }

    @Override
    public String toString(){

        if(a>0 && b>0)
            return String.valueOf(a)+"/"+String.valueOf(b);

        if(a<0 && b<0)
            return String.valueOf(-a)+"/"+String.valueOf(-b);

        if(a>0 && b<0)
            return "-"+String.valueOf(a)+"/"+String.valueOf(-b);

        if(a<0 && b>0)
            return "-"+String.valueOf(-a)+"/"+String.valueOf(b);

        return "0";
    }
}
public class Problem149直线上最多的点数 {

    public static void main(String[] args){
        Problem149直线上最多的点数 problem149 = new Problem149直线上最多的点数();
        Point[] points = new Point[]{
//                new Point(1,1),
//                new Point(3,2),
//                new Point(5,3),
//                new Point(4,1),
//                new Point(2,3),
//                new Point(1,4)
                new Point(1,1),
                new Point(2,2),
                new Point(3,3)
        };
        int result = problem149.maxPoints(points);
        System.out.println(result);

    }

    int result = 0;

    public int maxPoints(Point[] points) {
        if(points.length==1) return 1;

        // 存储共线点数量
        Map<String,Integer> linePoints = new HashMap<>();

        for(int i=0;i<points.length;i++){

//            System.out.println("计算点"+"("+points[i].x+","+points[i].y+")");

            // 每次计算前清空map
            linePoints.clear();
            int duplicate = 1;
            for(int j=i+1;j<points.length;j++){

                Point Ai = points[i];
                Point Aj = points[j];
                // 计算点Ai和Aj直线的斜率
                int dx = Aj.x - Ai.x;
                int dy = Aj.y - Ai.y;

                // 有重复点的情况
                if(Ai.x== Aj.x && Ai.y==Aj.y){
                    duplicate++;
                    continue;
                }

                // 斜率不存在的情况,直线方程为 x=xx 或 y=xx
                if(dx==0 || dy==0){

                    if(dx==0){
                        String key = String.format("x=%d",Ai.x);

                        linePoints.put(key,linePoints.getOrDefault(key,0)+1);
                    }else {
                        String key = String.format("y=%d",Ai.y);

                        linePoints.put(key,linePoints.getOrDefault(key,0)+1);
                    }

                    continue;
                }

                // 斜率存在的情况， 设直线方程为 y = kx+b
                if(dx!=0 && dy!=0){

                    // 斜率k=dy/dx，这里用分数表示（避免浮点数精度问题）
                    Fraction k = new Fraction(dy,dx);
                    k.Normalize();  // 化简分数

                    // 根据已知的k计算该条直线的b
                    // 公式为:
                    // b = y-kx
                    Fraction b = Fraction.Sub(Aj.y,Fraction.Mul(k, Aj.x));
                    b.Normalize();

                    // 将"k+b"作为键存入Map中
                    String key = k.toString()+"+"+b.toString();

//                    System.out.println(String.format(" ~ 点(%d,%d), k=%d/%d, k+b: %s",Aj.x,Aj.y,dy,dx,key));

                    linePoints.put(key,linePoints.getOrDefault(key,0)+1);
                }
            }

            result = Math.max(result,duplicate);

            for(String key : linePoints.keySet()){
//                System.out.println(key+" : "+linePoints.get(key));
                result = Math.max(result,linePoints.get(key)+duplicate);
            }
//            System.out.println();
        }


        return result;
    }
}
