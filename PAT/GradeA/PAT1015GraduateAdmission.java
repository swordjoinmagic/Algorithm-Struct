package PAT.GradeA;

import java.util.*;

/**
 * 题意:
 *      1. 有M个大学,他们都各自有x个名额.
 *      有N个学生,他们每个人心目都有一个想去的学校排名,
 *      按照学生成绩高低进行排序,安排每个学生去大学
 *
 *      2. 成绩按总成绩排序,如果总成绩相等,那么按笔试成绩排名,
 *      如果笔试成绩也相等,那么这两个学生的排名必须相同
 *
 *      3. 每个学生有K个优先选择,这k个选择按想去的学校排名,
 *      对于一个学校,如果学校还有名额,那么该学生可以入学,如果
 *      名额不够,那么继续判断下一个选择,如果该学生所有选择的学校
 *      的名额都已满,那么该学生将会被淘汰
 *
 *      4. 对于相同排名的学生,学校必须接受他们,即使招收名额已满
 *
 * 思路:
 *      模拟题.
 *      1. 先对N个学生按 (总成绩,笔试成绩) 进行排序,
 *      并额外用一个数组记录学生真正排名(总成绩&&笔试成绩一致的排名必须相等)
 *      2. 用数组记录各个学校目前名额,对排名的学生从上到下进行选择
 *      3. 每个学校都用一个数组记录他们招了排名为x的学生,
 *      当一个学生x志愿是y学校时,首先判断学校名额是否已满,
 *          a. 如果学校名额未满,学生加入学校,学校名额-1
 *          b. 如果学校名额已满,判断学校是否招过跟这个学生相同排名的人
 *              1) 如果招过,那么要招这个学生x
 *              2) 如果没招过,那么不招这个学生
 *
 *
 * 坑:
 *    1. 总成绩是个浮点数来着!!!!!!!!!!!!!
 */
public class PAT1015GraduateAdmission {

    public static void main(String[] args){
        PAT1015GraduateAdmission pat1015 = new PAT1015GraduateAdmission();
        pat1015.Input();
    }

    class Student implements Comparable<Student>{
        // 笔试成绩
        int GE;
        // 面试成绩
        int GI;
        // 总成绩
        double finalGrade;
        // 学生原始编号
        int index;
        // 学生志向
        int[] choices;

        public Student(int GE, int GI,int[] choices,int index) {
            this.GE = GE;
            this.GI = GI;
            this.choices = choices;
            this.index = index;
            this.finalGrade = ((double) this.GE+this.GI)/2;
        }

        @Override
        public  String toString(){
            String attribute = String.format("(GE:%d,GI:%d,Final:%f,index:%d)",GE,GI,finalGrade,index);
            String choices = "choices[";
            for(int i:this.choices) choices+=i+",";
            choices+="]";
            return attribute+"-"+choices;
        }

        @Override
        public int compareTo(Student o) {
            if(this.finalGrade>o.finalGrade){
                return -1;
            }else if(this.finalGrade<o.finalGrade){
                return 1;
            }else{
                if(this.GE > o.GE){
                    return -1;
                }else if(this.GE < o.GE){
                    return 1;
                }else {
                    return 0;
                }
            }
        }
    }

    // 学生总数
    int N;
    // 学校数量
    int M;
    // 志愿数
    int K;

    // 各个学校的招生名额
    int[] schoolQuota;

    // 每个学校招了什么排名的人
    // schoolStudents[0][2]=true表示编号为0的学校招了排名为2的学生
    boolean[][] schoolStudents;

    Student[] students;

    Student[] initalStudents;

    // 存储每个学生的排名
    // rank[i]=1表示下标为i的学生排名为1
    int[] ranks;

    // result[i] 表示 学校 i 招收的所有学生
    List<List<Student>> result = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        K = in.nextInt();

        schoolQuota = new int[M];
        schoolStudents = new boolean[M][N];
        students = new Student[N];
        ranks = new int[N];
        initalStudents = new Student[N];

        for(int i=0;i<M;i++){
            schoolQuota[i] = in.nextInt();
            // 初始化result
            result.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++){
            int GE = in.nextInt();
            int GI = in.nextInt();
            int[] choices = new int[K];
            for(int j=0;j<K;j++) choices[j] = in.nextInt();
            Student student = new Student(GE,GI,choices,i);
            students[i] = student;
            initalStudents[i] = student;
        }

        Arrays.sort(students);

        Slove();
    }

    // 计算学生排名
    public void ComputeRank(){

        // 排名
        int rank = 1;

        ranks[students[0].index] = rank;
        for(int i=1;i<N;i++){
            if(students[i].compareTo(students[i-1])==0){
                // 排名相同
                ranks[students[i].index] = rank;
            }else {
                ranks[students[i].index] = ++rank;
            }
        }
    }

    /**
     * 对第i个学生进行它的志愿处理
     * @param index
     */
    public void ComputeChoice(int index){
       Student student = students[index];
       for(int choice : student.choices){
           // 如果学校名额未满,那么招这个学生
           if(schoolQuota[choice]>0){
               // 编号为choice的学校招收排名为i的学生
               schoolStudents[choice][ ranks[student.index] ] = true;
               // 名额-1
               schoolQuota[choice]--;
               result.get(choice).add(student);
               break;
           }else if(schoolStudents[choice][ ranks[student.index] ]){
               // 如果学校招过跟该学生同样排名的学生,
               // 那么无视该学校的名额限制,直接招收该学生

               // 名额-1
               schoolQuota[choice]--;
               result.get(choice).add(student);
               break;
           }
       }
    }

    public void Slove(){
        ComputeRank();

        for(int index=0;index<N;index++){
            ComputeChoice(index);
        }

        for(List<Student> l : result){
            l.sort(Comparator.comparingInt(o -> o.index));
            for(int i=0;i<l.size();i++){
                Student student = l.get(i);
                if(i!=l.size()-1)
                    System.out.print(student.index+" ");
                else
                    System.out.print(student.index);
            }
            System.out.println();
        }
    }
}
