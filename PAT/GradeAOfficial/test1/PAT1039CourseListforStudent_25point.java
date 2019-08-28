package PAT.GradeAOfficial.test1;

import PAT.GradeA.PAT1039StringSubtraction;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 题意:
 *      1. 给定N个学生和他们所学的课程.
 *      求每个学生的课程列表.
 *      最后按升序排序课程
 * 思路:
 *      1. map.
 */
public class PAT1039CourseListforStudent_25point {

    public static void main(String[] args){
        PAT1039CourseListforStudent_25point pat1039 = new PAT1039CourseListforStudent_25point();
        pat1039.Input();
    }

    // 键为学生名,值为课程列表
    Map<String, TreeSet<Integer>> map = new HashMap<>();

    // 询问的学生数量
    int N;
    // 课程总数
    int K;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();

        for(int i=0;i<K;i++) {
            int courseIndex = in.nextInt();
            int studentCount = in.nextInt();
            for(int j=0;j<studentCount;j++){
                String studentName = in.next();
                if(!map.containsKey(studentName)) map.put(studentName,new TreeSet<>());
                map.get(studentName).add(courseIndex);
            }
        }

        for(int i=0;i<N;i++){
            String name = in.next();
            if(!map.containsKey(name)) map.put(name,new TreeSet<>());
            System.out.print(name+" "+map.get(name).size());
            for(int n : map.get(name))
                System.out.print(" "+n);
            System.out.println();
        }
    }
}
