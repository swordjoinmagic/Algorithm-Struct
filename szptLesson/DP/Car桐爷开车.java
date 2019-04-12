package szptLesson.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 思路:
 *
 *      1. DP。设 f[i][j]为到达第i个加油站时，还剩j升油的最小花费
 *      状态转移方程如下：
 *
 *          f[i][j] = min(  f[i-1][] );
 */
public class Car桐爷开车 {

    public static void main(String[] args){
        Car桐爷开车 car = new Car桐爷开车();
        car.Input();
        car.Slove();
    }

    // 每个黑油站距离深圳的距离（舍弃下标0）
    private List<Integer> distances;
    // 每个黑油站的油费(舍弃下标0)
    private List<Integer> oilsPayment;

    private int distance;
    // 加油站数量
    private int oilCount;

    private final int MAX_VALUE = 999999999;

    public void Input(){
        Scanner in = new Scanner(System.in);
        distance = in.nextInt();

        distances = new ArrayList<>();
        oilsPayment = new ArrayList<>();

        // 舍弃下标0
        distances.add(-1);
        oilsPayment.add(-1);

        while (in.hasNextInt()){

            // 油站距离深圳距离
            int tempDistance = in.nextInt();
            // 当前油站油费
            int tempOil = in.nextInt();
            distances.add(tempDistance);
            oilsPayment.add(tempOil);

            // 油站总数
            oilCount++;

        }
    }

    public void Slove(){
        int[][] f = new int[oilCount+1][201];

        // 根据第一个加油站初始化
        // 一开始有100升油，跑到第一个加油站时，
        // 耗费的油=distance
        int nowOil = 100-distances.get(1);  // 到达第一个加油站时的油量
        for(int oil=nowOil+1;oil<=200;oil++){
            // 表示在这里充 oil -  nowOil 升油
            f[1][oil] = (oil - nowOil)*oilsPayment.get(1);
        }

        // i: 表示到达第几个加油站
        for(int i=2;i<=oilCount;i++){
            // 第i个加油站和第i-1个加油站之间的距离
            int distanceDifferent = distances.get(i)-distances.get(i-1);

            Arrays.fill(f[i],MAX_VALUE);
            // j: 表示到达该加油站时还剩的油量
            for(int j=0;j<=200 && distanceDifferent+j<=200;j++){

                for(int k=0;k<=distanceDifferent+j;k++){
                    // 在前一个加油站加油 , 当到达第i个加油站时,还剩J升油
                    // 中途耗油量为distanceDifferent,
                    // 则在第 i-1 个加油站,要将在第i-1个加油站时的油量加到 distanceDifferent+J升油
                    // k表示在第i-1个加油站时的当前油量,
                    // 此时要加 distaceDifferent+J - K升油,才能在到第i个加油站时的当前油量为J升
                    // K>=0 && K<=distanceDifferent+J
                    f[i][j] = Math.min(f[i][j],f[i-1][k]+(distanceDifferent+j-k)*oilsPayment.get(i-1));



                    // 在当前加油站加油 加 到J升油
                    // k表示的意思是到达当前加油站时当前油量
                    // 这时要将 当前油量K 加到 J升油,则 K <= J+distanceDifferent(中途耗油量),
                    // 故 此时要加 (J-K) 升油才能从当前的K升油加到J升油
                    f[i][j] = Math.min(f[i][j],f[i][k]+(j-k)*oilsPayment.get(i));

                }
            }
        }

        // 终点离最后一个加油站的距离
        int lastDistance = distance - distances.get(oilCount);

        // 到达终点时，至少要有100升油，
        // 即在最后一个加油站，
        // 油量要大于等于 lastDistance + 100
        // 其中 lastDistance 是最后一个加油站到终点需要的耗油量
        int minPayment = MAX_VALUE; // 最小花费
        for(int p=lastDistance+100;p<=200;p++){
            minPayment = Math.min(minPayment,f[oilCount][p]);
        }

        if(minPayment!=MAX_VALUE)
            System.out.println(minPayment);
        else
            System.out.println("Impossible");
    }

}
