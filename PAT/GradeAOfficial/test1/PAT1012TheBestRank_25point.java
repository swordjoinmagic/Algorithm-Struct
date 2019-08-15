package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 多关键词排序.
 *
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
        int rank1 = 0;
        for(int i=0;i<N;i++) {
            if(i==0 || students[i].A != students[i-1].A) rank1++;
            if(rank1<students[i].bestRank){
                students[i].bestRank = rank1;
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


        int rank2 = 0;
        for(int i=0;i<N;i++) {
            if(i==0 || students[i].C != students[i-1].C) rank2++;
            if(rank2<students[i].bestRank){
                students[i].bestRank = rank2;
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
        int rank3 = 0;
        for(int i=0;i<N;i++) {
            if(i==0 || students[i].M != students[i-1].M) rank3++;
            if(rank3<students[i].bestRank){
                students[i].bestRank = rank3;
                students[i].bestSubject = "M";
            }
        }

        // 排序E
        Arrays.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1,Student o2){
                if(o1.E>o2.E)
                    return -1;
                else if(o1.E<o2.E)
                    return 1;
                else
                    return 0;
            }
        });
        int rank4 = 0;
        for(int i=0;i<N;i++) {
            if(i==0 || students[i].E != students[i-1].E) rank4++;
            if(rank4<students[i].bestRank){
                students[i].bestRank = rank4;
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
