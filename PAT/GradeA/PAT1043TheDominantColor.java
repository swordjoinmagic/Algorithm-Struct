package PAT.GradeA;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      给出N*M个数,找出其中出现次数最多的数
 *
 * 思路:
 *      1. 模拟
 */
public class PAT1043TheDominantColor {

    public static void main(String[] args){
        PAT1043TheDominantColor pat1043TheDominantColor = new PAT1043TheDominantColor();
        pat1043TheDominantColor.Input();
    }

    int N,M;

    int maxCount;
    int maxNumber;

    int[][] array;

    Map<Integer,Integer> map;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        array = new int[N][M];
        map = new HashMap<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                array[i][j] = in.nextInt();
                map.put(array[i][j],map.getOrDefault(array[i][j],0)+1);

                if(maxCount<map.get(array[i][j])){
                    maxCount = map.get(array[i][j]);
                    maxNumber = array[i][j];
                }
            }
        }

        System.out.print(maxNumber);
    }
}
