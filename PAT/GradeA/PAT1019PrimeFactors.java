package PAT.GradeA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述;
 *      1. 将N用它的质因子描述出来,即
 *      N = p1^k1 * p2^k2 ....
 *      其中pi是k的质因子,ki是pi的个数
 * 思路:
 *      1. 素数筛找到 <= N的所有质数,
 *      同时找到N的所有质因子.
 *
 *      遍历质因子,对N不停的进行除法操作,直到N==0
 *
 *      时间复杂度O(NLogLogN) 主要用在了找素数上吧
 *
 * 坑:
 *      1. 思路1总体没错,但是由于题中的N太大了,Java开不了这么大的数组
 *      这里可以从题目入手,要找的是N的质因数,也就是说这个质因数估计会比N小得多,
 *      这里可以直接将N/x一个个试
 */
public class PAT1019PrimeFactors {

    public static void main(String[] args){
        PAT1019PrimeFactors pat1019 = new PAT1019PrimeFactors();
        pat1019.Input();
    }

    // 筛子,u[i]=true表示i不是素数,被筛去
    boolean u[];

    // N的所有质因数
    List<Integer> primeFactors = new ArrayList<>();

    int N;

    int sushuCount;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

//        sushuCount = (int)Math.sqrt(N);

        sushuCount = N/8;

        u = new boolean[sushuCount];

        Slove();
    }

    // 素数筛找素数
    public void FindPrims(){
        for(int i=2;i<sushuCount;i++){
            // 将当前素数(即i)的所有倍数从筛子中筛去
            if(!u[i]){

                // 当前的i是素数
                if(N%i==0){
                    // 当前的i是N的因数
                    primeFactors.add(i);
                }

                for(int j=2;i*j<sushuCount;j++)
                    u[i*j] = true;  // 筛去素数i的所有倍数
            }
        }
    }

    public void Slove(){
        FindPrims();

        int tempN = N;
        int index = 0;

        int[] count = new int[primeFactors.size()];

        while (tempN!=0 && index<primeFactors.size()){
            if(tempN%primeFactors.get(index)==0){
                tempN /= primeFactors.get(index);
                count[index]++;
            }else {
                index++;
            }
        }

        System.out.print(N+"=");
        if(count.length==0){
            System.out.print(N);
        }else if(count[0]==1){
            System.out.print(primeFactors.get(0));
        }else if(count[0]>1) {
            System.out.print(primeFactors.get(0)+"^"+count[0]);
        }
        for(int i=1;i<primeFactors.size();i++){
            if(count[i]==0) continue;
            if(count[i]==1){
                System.out.print("*"+primeFactors.get(i));
            }else {
                System.out.print("*"+primeFactors.get(i)+"^"+count[i]);
            }
        }
    }
}
