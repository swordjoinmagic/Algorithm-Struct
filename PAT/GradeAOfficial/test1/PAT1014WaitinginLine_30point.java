package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 排队问题.
 *      给定N个窗口,每个窗口可以容纳M个客人.
 *
 *      给定K个客人以及他们的业务处理时间,求每个客人处理完他们的业务的离开时间.
 *
 *      要点:
 *          a. 每个客人都会前往序号最小且人数最少的空余窗口
 *          b. 第一个客人假设从八点钟开始被服务
 *          c. 银行17:00关门,在要等到17:00之后的客人不接受服务
 *          d. 客人编号从1到K
 *
 * 思路:
 *      1. 用N个容量为M的队列进行模拟.
 *
 *      当所有队列未满时,模拟入队.
 *      当所有队列已满时,模拟出队
 *
 *      模拟入队操作时:
 *          a. 将顾客入队至 容量最小 的队列中
 *      模拟出队操作时:
 *          b. 将离开时间最早的顾客出队
 */
public class PAT1014WaitinginLine_30point {

    public static void main(String[] args){
        PAT1014WaitinginLine_30point pat1014 = new PAT1014WaitinginLine_30point();
        pat1014.Input();
    }

    class Custom{
        // 开始业务时间(以分为单位)
        int startTime;
        // 结束业务时间
        int endTime;
        // 业务处理时间
        int processingTime;

        public boolean isSorrry;

        public Custom(int processingTime){
            this.processingTime = processingTime;
        }

        public String toStandardTime(){
            return String.format("%02d:%02d",endTime/60,endTime%60);
        }

        public String toString(){
            return toStandardTime();
        }
    }

    // 窗口数量及每个窗口能容纳人数
    int N,M;

    // 顾客数量及询问顾客数量
    int K,Q;

    final int eightTime = 8*60;
    final int seventeenTime = 17*60;

    int[] querys;
    Custom[] customs;
    List<Queue<Custom>> windows = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt(); M = in.nextInt();
        K = in.nextInt(); Q = in.nextInt();

        for(int i=0;i<N;i++) windows.add(new LinkedList<>());
        customs = new Custom[K];
        querys = new int[Q];

        for(int i=0;i<K;i++)
            customs[i] = new Custom(in.nextInt());
        for(int i=0;i<Q;i++)
            querys[i] = in.nextInt()-1;

        Slove();
    }

    // 顾客中的最早离开时间(以分计算)
    int minTime;
    int minTimeWindows; // 拥有最早离开时间的队列的序号
    int minCapWindows;  // 拥有最少容量最小序号的队列序号

    // 计算是否所有队列都已满,
    // 如果都已满,那么计算顾客的最早离开时间和
    // 拥有最早离开时间的队列序号,
    // 如果都未满,计算容量最少序号最少的队列序号
    public boolean isFullQueue(){
        boolean result = true;
        // 判断是否所有队列已满
        for(int i=0;i<N;i++){
           if(windows.get(i).size()<M)
               result = false;
       }

        minTime = Integer.MAX_VALUE;
        int tempMinCap = M;

        if(result){
            // 如果所有队列已满,计算最早离开时间
            for(int i=0;i<N;i++){
                if(windows.get(i).peek().endTime < minTime){
                    minTime = windows.get(i).peek().endTime;
                    minTimeWindows = i;
                }
            }
        }else {
            // 如果队列未满,计算最少容量的队列序号
            for(int i=0;i<N;i++){
                if(windows.get(i).size()<tempMinCap) {
                    tempMinCap = windows.get(i).size();
                    minCapWindows = i;
                }
            }
        }

        return result;
    }

    // 模拟出队
    public void Pop(){
        // 将拥有最小离开时间的队列的队首出队
        windows.get(minTimeWindows).poll();
    }

    // 模拟入队
    public void Push(Custom custom){
        if(windows.get(minCapWindows).size()>0){
            Custom lastCustom = ((LinkedList<Custom>)(windows.get(minCapWindows))).getLast();
            if(lastCustom.endTime>=seventeenTime) custom.isSorrry = true;
            custom.endTime = lastCustom.endTime+custom.processingTime;
        }else custom.endTime = eightTime+custom.processingTime;
        // 将顾客进队至拥有最小容量最小序号的队列中
        windows.get(minCapWindows).add(custom);
    }

    public void Slove(){

        int i = 0;

        while (i<K) {
            if (isFullQueue()) {
                // 队列已满,模拟出队
                Pop();
            } else {
                // 队列未满,模拟进队
                Push(customs[i]);
                i++;
            }
        }

        for(int j=0;j<Q;j++){
            if(customs[querys[j]].isSorrry)
                System.out.println("Sorry");
            else
                System.out.println(customs[querys[j]].toStandardTime());
        }
    }
}
