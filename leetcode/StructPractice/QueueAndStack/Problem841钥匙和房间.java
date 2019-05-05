package leetcode.StructPractice.QueueAndStack;

import java.util.*;

/**
 * 思路:
 *      1. BFS.根据题意,从0号房间出发,根据0号房间的所有钥匙,
 *      去其他房间,再根据其他房间的钥匙,去另外一些房间.
 *      每次访问一个房间,访问房间数+1,当访问房间数==房间数时,
 *      返回True
 */
public class Problem841钥匙和房间 {

    public static void main(String[] args){
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<>());rooms.add(new ArrayList<>());rooms.add(new ArrayList<>());rooms.add(new ArrayList<>());
        rooms.get(0).add(1);rooms.get(0).add(3);
        rooms.get(1).add(3);rooms.get(1).add(0);rooms.get(1).add(1);
        rooms.get(2).add(2);
        rooms.get(3).add(0);

        boolean result = new Problem841钥匙和房间().canVisitAllRooms(rooms);

        System.out.print(result);
    }

    private boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[rooms.size()];

        // 加入初始节点
        queue.add(0);

        // 已访问房间数
        int visitedRooms = 0;

        while (!queue.isEmpty()){

            int roomNumber = queue.poll();
            visited[roomNumber] = true;
            visitedRooms ++;
            if(visitedRooms == rooms.size()) return true;

            for(int key : rooms.get(roomNumber)){
                if(!visited[key]){
                    visited[key] = true;
                    queue.add(key);
                }
            }

        }
        return false;
    }
}
