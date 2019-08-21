package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定K个窗口,N个顾客,和每个顾客的到达窗口时间和处理时间,
 *      求所有客户的平均等待时间.
 *      规则是每个客户都必须等在黄线之后,直到轮到他处理事务并且窗口有空闲,
 *      每个客户处理时间小于等于1h
 * 思路:
 *      1. 模拟题.时间转分钟.
 *      第一步,先将所有客户根据时间排序.
 *      然后遍历所有客户,计算其等待时间,具体方法是:
 *          a. 等待时间 = 所有窗口中完成时间最短的 - 该客户的到达时间
 *          b. 如果当前窗口有空余,那么等待时间 = 到达时间 < 8:00 ? 8:00-到达时间 : 0
 *
 *      这就需要维护所有窗口中的最小完成时间,这里用一个优先队列来解决.
 *      即加入队列的客户按照他们的完成时间进行排序,那么第一个一定是最短完成时间,
 *      这样每次只需要将第一个出队,将第一个的完成时间-当前要进行业务的客户的到达时间就OK了
 *
 * 坑:
 *      1. 除了到达时间晚于17:00:01的以外,其他就算排队等到17:00:00以后也算在平均数内,这把我坑死了!!
 */
public class PAT1017QueueingatBank_25point {

    public static void main(String[] args){
        PAT1017QueueingatBank_25point pat1017QueueingatBank = new PAT1017QueueingatBank_25point();
        pat1017QueueingatBank.Input();
    }

    // 顾客总数
    int N;

    // 窗口数量
    int K;

    // 维护所有窗口的最小完成时间
    PriorityQueue<Custom> queue;

    Custom[] customs;

    // 8:00:00 时的秒数
    final int eightTime = 8 * 60 * 60;
    // 17:00:00 时的描述
    final int seventeenTime = 17 * 60 * 60;

    class Custom{
        // 以秒计时的到达时间
        int arriveTime;
        // 以秒计时的完成时间
        int finsihTime;
        // 完成业务所需时间,以秒计时
        int processTime;

        int h,m,s;

        public Custom(String arriveTime, int processTime) {

            String[] times = arriveTime.split(":");
            h = Integer.parseInt(times[0]);
            m = Integer.parseInt(times[1]);
            s = Integer.parseInt(times[2]);

            this.arriveTime = s + m*60 + h*60*60;
            this.processTime = processTime;
        }

        @Override
        public String toString(){
            return String.format("%02d:%02d:%02d %d FT:%d",h,m,s,processTime,finsihTime);
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();

        customs = new Custom[N];
        queue = new PriorityQueue<>(new Comparator<Custom>() {
            @Override
            public int compare(Custom o1, Custom o2) {
                if(o1.finsihTime>o2.finsihTime)
                    return 1;
                else if(o1.finsihTime<o2.finsihTime)
                    return -1;
                else
                    return 0;
            }
        });

        for(int i=0;i<N;i++){
            String arriveTime = in.next();
            int processTime = in.nextInt()*60;
            customs[i] = new Custom(arriveTime,processTime);
        }

        Slove();
    }

    public void Slove(){

        // 先根据到达时间进行排序
        Arrays.sort(customs,new Comparator<Custom>(){
            @Override
            public int compare(Custom o1,Custom o2){
                if(o1.arriveTime>o2.arriveTime)
                    return 1;
                else if(o1.arriveTime<o2.arriveTime)
                    return -1;
                else
                    return 0;
            }
        });

        // 总的等待时间,以秒计数
        int totalWaitTime = 0;
        // 总共有多少客户是纳入计算的
        int totalCustom = N;

        // 将这些顾客根据到达时间顺序依次加入优先队列(先计算完成时间)
        for(int i=0;i<N;i++){

            Custom custom = customs[i];

            // 如果新客户到达时间晚于或等于 17:00:01,那么不对该客户进行处理,
            // 也不纳入平均数中
            if(custom.arriveTime >= seventeenTime+1){
                totalCustom = i;
                break;
            }

            // 如果当前队列未满(即窗口未满)
            // 那么可以直接排(八点以后),八点之间就增加等到八点的时间
            if(queue.size() < K){
                // 如果是八点之前来的,那么等待时间加上等到8点的时间
                if(custom.arriveTime < eightTime){
                    totalWaitTime += eightTime-custom.arriveTime;
                    // 完成时间为8点加业务处理时间
                    custom.finsihTime = eightTime + custom.processTime;
                }else {
                    // 如果是八点之后来的,没有等待时间,直接上,
                    // 完成时间为到达时间+业务处理时间
                    custom.finsihTime = custom.arriveTime + custom.processTime;
                }

                // 将该客户加入队列
                queue.add(custom);
            }else {
                // 如果队列已满,说明大家都在用窗口,
                // 那么此时的客户要去完成时间最短的那个窗口
                Custom lastCustom = queue.poll();

                // 如果上一个客户的最小完成时间已经是在17:00:01以后了,那么不处理这个客户
//                if(lastCustom.finsihTime >= seventeenTime+1){
//                    totalCustom = i;
//                    break;
//                }

                if(lastCustom.finsihTime > custom.arriveTime) {
                    // 新客户的等待时间为 上一个客户的最小完成时间 - 该客户的到达时间
                    totalWaitTime += lastCustom.finsihTime - custom.arriveTime;
                    // 新客户的完成时间 = 上一个客户的最小完成时间 + 该客户的业务时间
                    custom.finsihTime = lastCustom.finsihTime + custom.processTime;
                }else {
                    custom.finsihTime = custom.arriveTime + custom.processTime;
                }

                // 将新客户加入队列
                queue.add(custom);
            }
        }


        System.out.format("%.1f",totalWaitTime/60f/totalCustom);
    }
}
