package PAT.GradeA;

import java.util.*;

/**
 * 题意:
 *      给定N个硬币,和要凑的钱M,
 *      求用来凑够M块钱的两个硬币V1,V2,使得V1+V2=M,
 *      如果有多个解答,那么选择V1最小的那个
 *
 * 思路:
 *      1. map.
 */
public class PAT1037FindCoins {

    public static void main(String[] args){
        PAT1037FindCoins pat1037 = new PAT1037FindCoins();
        pat1037.Input();
    }

    // N个硬币
    int N;
    // 要凑的钱
    int M;

    int[] array;
    Map<Integer,Integer> map;

    int v1 = Integer.MAX_VALUE,v2 = Integer.MAX_VALUE;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        array = new int[N];
        map = new HashMap<>();

        for(int i=0;i<N;i++){
            int coin = in.nextInt();
            array[i] = coin;
            map.put(coin,map.getOrDefault(coin,0)+1);
        }
        Slove();
    }

    public void Slove(){
        for(int coinV1 : array){
            int coinV2 = M-coinV1;

            if(coinV1>coinV2) continue;


            if(coinV2 != coinV1){

                if(map.containsKey(coinV2)){
                    if(coinV1<v1){
                        v1 = coinV1;
                        v2 = coinV2;
                    }
                }

            }else {

                if(map.getOrDefault(coinV2,0)>=2){
                    if(coinV1<v1){
                        v1 = coinV1;
                        v2 = coinV2;
                    }
                }

            }
        }

        if(v1!=Integer.MAX_VALUE)
            System.out.print(v1+" "+v2);
        else
            System.out.print("No Solution");
    }
}
