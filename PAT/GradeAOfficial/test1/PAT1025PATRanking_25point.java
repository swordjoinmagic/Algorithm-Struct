package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定N份队列,每份队列有K份成绩,
 *      要求得到每个学生在当前队列的排名和总的排名
 *
 * 坑:
 *      1. 相同成绩的相同排名,同时会把下一个排名挤掉.
 *      如 99 99 30,则第第一个人是第一名,第二个人是第一名,第三个人第三名
 *      2. 排序时,如果两人排名相等,则按照他们的编号升序排序
 *
 * 思路:
 *      1. 优先队列 + 归并排序.
 */
public class PAT1025PATRanking_25point {

    public static void main(String[] args){
        PAT1025PATRanking_25point pat1025 = new PAT1025PATRanking_25point();
        pat1025.Input();
    }

    class Student implements Comparable<Student>{
        String id;
        int score;
        int localRank;
        int localNumber;    // 考场序号,1-N
        int finalRank;

        public Student(String id,int score,int localNumber){
            this.id = id;
            this.score = score;
            this.localNumber = localNumber;
        }

        @Override
        public int compareTo(Student o) {
            if(score>o.score){
                return -1;
            }else if(score<o.score){
                return 1;
            }else {
                return id.compareTo(o.id);
            }
        }
    }

    // N个考场
    int N;
    // 每个考场的人数
    int K;

    // 记录每个考场的学生
    List<List<Student>> list = new ArrayList<>();

    List<Student> students = new ArrayList<>();

    // 总的考场人数
    int totalPerson = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        for(int i=0;i<N;i++){
            List<Student> priorityQueue = new ArrayList<>();
            K = in.nextInt();
            totalPerson += K;
            for(int j=0;j<K;j++){
                String id = in.next();
                int score = in.nextInt();
                Student student = new Student(id,score,i+1);
                priorityQueue.add(student);
                students.add(student);
            }

            Collections.sort(priorityQueue);

            int number = 1;
            int rank = 1;
            // 第一个学生
            Student firstStudent = priorityQueue.get(0);
            firstStudent.localRank = rank;
            // 上一个学生
            Student lastStudent = firstStudent;
            // 设置本考场的排名
            for(Student s : priorityQueue){
                // 跳过第一个学生
                if(s==firstStudent) {
                    number++;
                    continue;
                }
                // 与上一个学生比较成绩,相同则排名相等
                if(s.score==lastStudent.score){
                    s.localRank = lastStudent.localRank;
                }else {
                    // 不相同则等于目前的该学生的序号
                    s.localRank = number;
                }
                number++;
                lastStudent = s;
            }

            list.add(priorityQueue);
        }

        Slove();
    }

    public void Slove(){
        // 对所有考场的人员进行一次归并排序

        Collections.sort(students);

        int number = 1;
        int rank = 1;
        // 第一个学生
        Student firstStudent = students.get(0);
        firstStudent.finalRank = rank;
        // 上一个学生
        Student lastStudent = firstStudent;
        // 设置本考场的排名
        for(Student s : students){
            // 跳过第一个学生
            if(s==firstStudent) {
                number++;
                continue;
            }
            // 与上一个学生比较成绩,相同则排名相等
            if(s.score==lastStudent.score){
                s.finalRank = lastStudent.finalRank;
            }else {
                // 不相同则等于目前的该学生的序号
                s.finalRank = number;
            }
            number++;
            lastStudent = s;
        }

        System.out.println(totalPerson);
        for(Student s : students){
            System.out.println(s.id+" "+s.finalRank+" "+s.localNumber+" "+s.localRank);
        }
    }
}
