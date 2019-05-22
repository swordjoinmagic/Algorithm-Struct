package PAT.GradeA;

import java.util.*;

/**
 * 题目描述:
 *      1. 给定N个人以及他们所跟随的人,求第i个人发推时,经过k层转发,
 *      最多会有多少人转发这条推文
 *
 * 思路:
 *      1. 模拟题.
 *      使用map记录第i个人的跟随者,
 *      经过k层跳转,有多少人转发推文
 */
public class PAT1011ForwardsonWeibo {

    public static void main(String[] args){
        PAT1011ForwardsonWeibo pat1011 = new PAT1011ForwardsonWeibo();
        pat1011.Input();
    }

    // 用户数量
    int N;
    // 最多有几层间接跳转
    int L;

    // map[i] 表示 第i个人 的 所有追随者
    Map<Integer, List<Integer>> followers;

    boolean[] visited;


    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        L = in.nextInt();

        followers = new HashMap<>();

        for(int i=1;i<=N;i++){

            if(!followers.containsKey(i)) followers.put(i,new ArrayList<>());

            int total = in.nextInt();
            for(int j=0;j<total;j++){
                // owner表示 第i号用户 追随的用户 的标号
                int owner = in.nextInt();

                if(!followers.containsKey(owner)) followers.put(owner,new ArrayList<>());

                followers.get(owner).add(i);
            }
        }

        int total = in.nextInt();
        visited = new boolean[N+1];
        for(int i=0;i<total;i++){

            Arrays.fill(visited,false);
            int userID = in.nextInt();

            System.out.println(QueryUserFollowerByKIndirect(userID));
        }
    }

    /**
     * 查询用户i发推后,最多有多少用户转发该用户推文
     *
     * @param userID
     */
    public int QueryUserFollowerByKIndirect(int userID){
        int max = 0;
        visited[userID] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(userID);

        // BFS层数
        int level = 0;

        while (!queue.isEmpty()){

            level ++;

            int size = queue.size();

            for(int i=0;i<size;i++){
                int userid = queue.poll();
                for(int id : followers.get(userid)){
                    if(!visited[id]){
                        max++;
                        visited[id] = true;
                        queue.add(id);
                    }
                }
            }

            if(level==L)
                return max;
        }

        return max;
    }
}
