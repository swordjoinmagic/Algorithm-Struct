package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意：
 *      1. 给定N个学生和每个学生选修的课程，
 *      最后按升序输出所有课程和选修它的学生
 * 思路：
 *      1. 水题。map+TreeSet,map的键是课程，值是学生
 */
public class PAT1047StudentListforCourse_25point {

    public static void main(String[] args){
        PAT1047StudentListforCourse_25point pat1047 = new PAT1047StudentListforCourse_25point();
        pat1047.Input();
    }

    // 学生总数
    int N;
    // 课程总数
    int K;

    Map<Integer, TreeSet<String>> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        for(int i=0;i<N;i++){
            String name = in.next();
            int cCount = in.nextInt();
            for(int j=0;j<cCount;j++){
                int cID = in.nextInt();

                if(!map.containsKey(cID)) map.put(cID,new TreeSet<>());

                map.get(cID).add(name);
            }
        }

        Slove();
    }

    public void Slove(){
        for(int i=1;i<=K;i++){
            System.out.println(i +" "+map.getOrDefault(i,new TreeSet<>()).size());
            if(map.containsKey(i))
            for(String name : map.get(i)){
                System.out.println(name);
            }
        }
    }
}
