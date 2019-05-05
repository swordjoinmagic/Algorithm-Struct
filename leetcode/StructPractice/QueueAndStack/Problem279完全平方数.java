package leetcode.StructPractice.QueueAndStack;

import java.util.*;

/**
 * 思路:
 *      1.  使用BFS状态搜索.
 *      起始状态 0
 *      第一层状态 0+1 , 0+4 , 0+9 ....
 *      第二层状态 0+1+1,0+1+4,0+1+9 ....
 *                 0+4+1,0+4+4,0+4+9 ....
 *                 以此类推
 *
 */
public class Problem279完全平方数 {

    public static void main(String[] args){
        Problem279完全平方数 problem279 = new Problem279完全平方数();

        int result = problem279.numSquares(13);

        System.out.println(result);
    }

    // 完全平方数表
    List<Integer> squreNumbers = new ArrayList<>();

    public int numSquares(int n) {
        // 打表
        for(int i=1;i*i<=n;i++){
            squreNumbers.add(i*i);
        }
        return BFS(n);
    }

    public int BFS(int target){

        // 已访问过的状态
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();

        // 加入初始状态
        queue.add(0);

        // 组成和的完全平方数个数
        int step = -1;

        while (!queue.isEmpty()){
            step ++;

            // 这一层的状态数量
            int size = queue.size();

            // 遍历该层,将所有新的状态加入队列
            for(int i=0;i<size;i++){

                // 获得当前状态
                int nowStatus = queue.poll();

                // 如果已经到达目标状态,那么返回最小步骤
                if(nowStatus == target) return step;

                // 如果当前已访问过,那么跳过本状态
                if(visited.contains(nowStatus)) continue;

                // 标记本状态已访问
                visited.add(nowStatus);

                // 根据当前状态得到新的状态
                for(int num : squreNumbers){
                    if(nowStatus+num>target) break;
                    queue.add(nowStatus+num);
                }

            }
        }
        return 0;
    }
}
