package PAT.GradeAOfficial.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个老鼠和他们的质量,将他们按照给出的顺序分成N组,
 *      每组都会有一个最胖的老鼠胜利,输的老鼠排名相同,每组最胖的进入下一轮,
 *      下一轮依旧分成M组,选每组最胖,继续进入下一轮
 *
 *      输出最后所有老鼠的排名
 *      PS: 排名是 1 2 2 4,即相同排名排名相同,小的那个还有继续顺着排下去
 *
 * 思路:
 *      1. 模拟题.
 *      用二维list,保留每一层的胜者的下标
 *
 */
public class PAT1056MiceandRice_25point {

    public static void main(String[] args){
        PAT1056MiceandRice_25point pat1056 = new PAT1056MiceandRice_25point();
        pat1056.Input();
    }

    // 保留每一层的胜者的下标
    List<List<Integer>> winers = new ArrayList<>();

    // 老鼠数量以及每组几个老鼠
    int NP,NG;

    // 老鼠重量
    int[] weight;

    // 一开始比赛的顺序
    int[] initOrder;

    public void Input(){
        Scanner in = new Scanner(System.in);
        NP = in.nextInt();
        NG = in.nextInt();

        weight = new int[NP];
        initOrder = new int[NP];

        for(int i=0;i<NP;i++) weight[i] = in.nextInt();

        winers.add(new ArrayList<>());
        for(int i=0;i<NP;i++){
            initOrder[i] = in.nextInt();
            winers.get(0).add(initOrder[i]);
        }

        Slove();
    }

    public void Slove(){

        int layer = 0;

        while (winers.get(layer).size()>1){
            // 取出这一层的参赛者
            List<Integer> list = winers.get(layer);

            // 下一层的老鼠,存的是老鼠在weight数组上的下标
            List<Integer> nextLayer = new ArrayList<>();

            // 进行分组比较
            for(int i=0;i<list.size();i+=NG){
                int maxWeight = 0;
                int maxIndex = 0;
                // 取出j组,并将fattest的老鼠扔到下一组
                for(int j=i;j<i+NG && j<list.size();j++){
                    if(maxWeight<weight[list.get(j)]){
                        maxWeight = weight[list.get(j)];
                        maxIndex = list.get(j);
                    }
                }
                // 将最胖的扔到下一层
                nextLayer.add(maxIndex);
            }

            layer++;
            winers.add(nextLayer);
        }

        // 设置排名
        int rank = 1;
        int[] ranks = new int[NP];
        // 从第一名往后算
        for(int i=winers.size()-1;i>=0;i--){
            List<Integer> list = winers.get(i);
            int j = 0;
            for(int index : list){
                if(ranks[index]==0) {
                    ranks[index] = rank;
                    j++;
                }
            }
            rank += j;
        }

        System.out.print(ranks[0]);
        for(int i=1;i<NP;i++){
            System.out.print(" "+ranks[i]);
        }
    }
}