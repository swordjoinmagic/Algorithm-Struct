package PAT.GradeAOfficial.test1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 求出所有连续子序列和为K的子序列,
 *      如果没有子序列和等于K,那么要输出所有消耗最小的方案
 *
 * 思路:
 *      1. 可以使用滑动窗口求子序列和.
 *      具体步骤如下:
 *          设滑动窗口左指针为i,右指针为j,
 *
 *          当滑动窗口内子序列和<K时,j++
 *          当滑动窗口内子序列==K时,i++;同时将目前的答案添加进列表
 *          当滑动窗口内子序列>K时,i++;
 *
 *      同时滑动窗口记录正好大于等于K的最小数
 */
public class PAT1044ShoppinginMars_25point {

    public static void main(String[] arg){
        PAT1044ShoppinginMars_25point pat1044 = new PAT1044ShoppinginMars_25point();
        pat1044.Input();
    }

    class Tuple{
        int i,j;

        public Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public String toString(){
            return String.format("%d-%d",i,j);
        }
    }

    // 序列长度
    int N;
    // 规定的和值
    int M;

    int[] array;

    List<Tuple> result = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        array = new int[N];
        for(int i=0;i<N;i++) array[i] = in.nextInt();

        Slove();
    }

    public void Slove(){
        // 设置滑动窗口左右指针(包括i,j,范围为[i,j])
        int i = 0,j=0;

        // 正好大于M的最小值
        int minM = Integer.MAX_VALUE;

        // 目前滑动窗口的和值
        int total = array[i];

        while (true){
            if(total<M){
                j++;
                if(j>=N) break;
                total += array[j];
            }else if(total > M){
                minM = Math.min(total,minM);
                total -= array[i];
                i++;
            }else {
                // 记录该答案
                result.add(new Tuple(i+1,j+1));
                j++;
                if(j>=N) break;
                total += array[j];
            }
        }

        if(result.size()==0){
            // 没有连续子序列和==M,这里将M变为minM
            M = minM;
            // 设置滑动窗口左右指针(包括i,j,范围为[i,j])
            i = 0;j=0;
            // 目前滑动窗口的和值
            total = array[i];

            while (true){
                if(total<M){
                    j++;
                    if(j>=N) break;
                    total += array[j];
                }else if(total > M){
                    total -= array[i];
                    i++;
                }else {
                    // 记录该答案
                    result.add(new Tuple(i+1,j+1));
                    j++;
                    if(j>=N) break;
                    total += array[j];
                }
            }
        }

        for(Tuple tuple : result)
            System.out.println(tuple);
    }
}
