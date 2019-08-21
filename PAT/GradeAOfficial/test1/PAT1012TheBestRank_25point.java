package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定学生的ECMA成绩,求各个学生的最佳成绩.
 *      最佳成绩定义为该学生排名最前的学科的成绩,如果有多个排名相同的学科,
 *      那么选优先级高的
 *
 * 坑:
 *      1. 排序规则!
 *      对于 99 99 98
 *      排名 1  1  3
 *      其中第二个99排名不是2,第三个98排名也不是2,连环巨坑
 */
public class PAT1012TheBestRank_25point {

    public static void main(String[] args){
        PAT1012TheBestRank_25point pat1012 = new PAT1012TheBestRank_25point();
        pat1012.Input();
    }

    class Student{
        String id;
        int C,M,E,A;

        public String bestSubject;
        public int bestRank = Integer.MAX_VALUE;

        public int rankA,rankE,rankC,rankM;

        public Student(String id,int C,int M,int E){
            this.id = id;
            this.C = C;
            this.M = M;
            this.E = E;
            this.A = (C+M+E)/3;
        }
    }

    String[] names;
    Student[] students;
    Map<String,Student> map;

    int N,M;

    public void Input(){
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        M = in.nextInt();

        students = new Student[N];
        names = new String[M];
        map = new HashMap<>();

        for(int i=0;i<N;i++){
            String id = in.next();
            int C = in.nextInt();
            int M = in.nextInt();
            int E = in.nextInt();
            students[i] = new Student(id, C,M,E);
            map.put(id,students[i]);
        }

        // 先排序A
        Arrays.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1,Student o2){
                if(o1.A>o2.A)
                    return -1;
                else if(o1.A<o2.A)
                    return 1;
                else
                    return 0;
            }
        });

        for(int i=0;i<N;i++){
            // 第一步,得到当前students[i]的排名
            int rank = i+1; // 正常情况下i所在的排名
            // 如果当前i与i-1的A相等,那么说明i和i-1的排名相同
            if(i!=0 && students[i].A == students[i-1].A)
                rank = students[i-1].rankA;

            students[i].rankA = rank;

            // 判断当前排名是否比最佳排名靠前,如果靠前,那么替换掉最佳排名
            if(rank<students[i].bestRank){
                students[i].bestRank = rank;
                students[i].bestSubject = "A";
            }
        }


        // 排序C
        Arrays.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1,Student o2){
                if(o1.C>o2.C)
                    return -1;
                else if(o1.C<o2.C)
                    return 1;
                else
                    return 0;
            }
        });


        for(int i=0;i<N;i++){
            // 第一步,得到当前students[i]的排名
            int rank = i+1; // 正常情况下i所在的排名
            // 如果当前i与i-1的A相等,那么说明i和i-1的排名相同
            if(i!=0 && students[i].C == students[i-1].C)
                rank = students[i-1].rankC;

            students[i].rankC = rank;

            // 判断当前排名是否比最佳排名靠前,如果靠前,那么替换掉最佳排名
            if(rank<students[i].bestRank){
                students[i].bestRank = rank;
                students[i].bestSubject = "C";
            }
        }

        // 排序M
        Arrays.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1,Student o2){
                if(o1.M>o2.M)
                    return -1;
                else if(o1.M<o2.M)
                    return 1;
                else
                    return 0;
            }
        });
        for(int i=0;i<N;i++){
            // 第一步,得到当前students[i]的排名
            int rank = i+1; // 正常情况下i所在的排名
            // 如果当前i与i-1的A相等,那么说明i和i-1的排名相同
            if(i!=0 && students[i].M == students[i-1].M)
                rank = students[i-1].rankM;

            students[i].rankM = rank;

            // 判断当前排名是否比最佳排名靠前,如果靠前,那么替换掉最佳排名
            if(rank<students[i].bestRank){
                students[i].bestRank = rank;
                students[i].bestSubject = "M";
            }
        }

        // 排序E
        Arrays.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1,Student o2){
                if(o1.E > o2.E)
                    return -1;
                else if(o1.E < o2.E)
                    return 1;
                else
                    return 0;
            }
        });
        for(int i=0;i<N;i++){
            // 第一步,得到当前students[i]的排名
            int rank = i+1; // 正常情况下i所在的排名
            // 如果当前i与i-1的A相等,那么说明i和i-1的排名相同
            if(i!=0 && students[i].E == students[i-1].E)
                rank = students[i-1].rankE;

            students[i].rankE = rank;

            // 判断当前排名是否比最佳排名靠前,如果靠前,那么替换掉最佳排名
            if(rank<students[i].bestRank){
                students[i].bestRank = rank;
                students[i].bestSubject = "E";
            }
        }

        for(int i=0;i<M;i++){
            names[i] = in.next();
        }

        Slove();
    }

    public void Slove(){
        for(String id : names){
            if(!map.containsKey(id)){
                System.out.println("N/A");
                continue;
            }
            System.out.println(map.get(id).bestRank+" "+map.get(id).bestSubject);
        }
    }
}
