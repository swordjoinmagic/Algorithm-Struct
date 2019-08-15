package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个数.一开始单位位于0层,上升一层需要6s,下降一层需要4秒,
 *      每次操作需要等待5秒执行.
 * 思路:
 *      1. 模拟.
 */
public class PAT1008Elevator_20point {

    public static void main(String[] args){
        PAT1008Elevator_20point pat1008 = new PAT1008Elevator_20point();
        pat1008.Input();
    }

    int totalTime = 0;
    int currentLevel = 0;

    int N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        for(int i=0;i<N;i++){
            int nextLevel = in.nextInt();
            int diff = nextLevel-currentLevel;
            if(diff > 0){
                // 上升
                totalTime += diff*6;
            }else {
                // 下降
                totalTime += -diff*4;
            }
            currentLevel = nextLevel;
            totalTime += 5;
        }

        System.out.println(totalTime);
    }
}
