package leetcode.StructPractice.QueueAndStack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 思路：
 *      1. 本质上是状态搜索问题。
 *      要求最小旋转次数，使用BFS算法。
 *      每次将当前状态可以变化到的所有状态加入队列，
 *      每一层变化即旋转次数+1
 */
public class Problem752打开转盘锁 {

    public static void main(String[] args){
        String[] deadends = new String[]{"8888"};
        String target = "0009";

        Problem752打开转盘锁 problem752 = new Problem752打开转盘锁();

        int result = problem752.openLock(deadends,target);

        System.out.println(result);
    }

    public int openLock(String[] deadends, String target) {
        return BFS(deadends,target);
    }

    public int BFS(String[] deadends, String target){
        // 死锁状态
        Set<String> deadLockStrings = new HashSet<>();
        for(String s : deadends) deadLockStrings.add(s);

        // 已经变化过的状态（用来去重，防止无限循环）
        Set<String> visitedStatus = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        // 初始状态
        String initStatus = "0000";

        // 队列加入初始状态
        queue.add(initStatus);

        // 当前旋转次数
        int step = -1;

        while (!queue.isEmpty()){

            step ++;

            // 本层遍历状态数
            int size = queue.size();

            for(int i=0;i<size;i++){

                // 当前状态
                String nowStatus = queue.poll();

                // 到达目标状态,返回
                if(nowStatus.equals(target)){
                    return step;
                }

                // 如果当前状态已经访问过,或者是死锁状态,那么跳过此状态
                if(deadLockStrings.contains(nowStatus) || visitedStatus.contains(nowStatus))
                    continue;

                // 将当前状态标记为已访问
                visitedStatus.add(nowStatus);

                char[] sequence = nowStatus.toCharArray();
                // 将当前状态可以变化的状态全部加入队列
                for(int j=0;j<sequence.length;j++){

                    // 当前数字
                    int nowNumber = sequence[j]-'0';

                    // 将当前数字向上翻
                    int upNumber = nowNumber==0? 9 : nowNumber-1;

                    // 将当前数字向下翻
                    int downNumber = nowNumber==9? 0 : nowNumber+1;

                    // 将上翻下翻两个状态加入队列
                    sequence[j] = (char)(upNumber+'0');
                    queue.add(new String(sequence));

                    sequence[j] = (char)(downNumber+'0');
                    queue.add(new String(sequence));

                    // 复原当前char数组
                    sequence[j] = (char)(nowNumber+'0');
                }

            }
        }
        return -1;
    }
}
