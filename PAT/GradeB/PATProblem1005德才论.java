package PAT.GradeB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 思路：
 *
 *      1.
 */
public class PATProblem1005德才论 {

    public static void main(String[] args){
        PATProblem1005德才论 problem1005 = new PATProblem1005德才论();
        problem1005.Slove();
    }

    private class Student{
        // 准考证号
        int ID;
        // 德分
        int de;
        // 才分
        int cai;
        // 哪类考生
        int rank;

        public Student(int ID,int de,int cai){
            this.ID = ID;
            this.de = de;
            this.cai = cai;
        }

        public String toString(){
            return String.format("%d %d %d",ID,de, cai);
        }
    }

    private class StudentCompartor implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {

            if(o1.rank<o2.rank){
                return -1;
            }else if(o1.rank>o2.rank){
                return 1;
            }else {
                int total1 = o1.cai + o1.de;
                int total2 = o2.cai + o2.de;
                if(total1>total2){
                    return -1;
                }else if(total1<total2){
                    return 1;
                }else {
                    // 总分相同时,按德分降序
                    if(o1.de>o2.de){
                        return -1;
                    }else if(o1.de<o2.de){
                        return 1;
                    }else {
                        // 德分相同时,按准考证号升序
                        if(o1.ID>o2.ID){
                            return 1;
                        }else if(o1.ID<o2.ID){
                            return -1;
                        }else
                            return 0;
                    }
                }
            }
        }
    }

    // 考生总数
    private int N;
    //录取最低分数线
    private int L;
    // 优先录取线
    private int H;

    private List<Student> students;


    public void Slove(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        L = in.nextInt();
        H = in.nextInt();

        students = new ArrayList<>(N);

        int studentsNumber = N;
        for(int i=0;i<N;i++){
            int ID = in.nextInt();
            int de = in.nextInt();
            int cai = in.nextInt();

            Student student = new Student(ID,de, cai);

            if(de>=H && cai>=H){
                student.rank = 1;
                students.add(student);
            }else if(de>=H && cai>=L){
                student.rank = 2;
                students.add(student);
            }else if(de<H && cai<H && de>=cai &&
                     de>=L && cai>=L){
                student.rank = 3;
                students.add(student);
            }else if(de>=L && cai>=L){
                student.rank = 4;
                students.add(student);
            }else
                studentsNumber--;
        }

        StudentCompartor compartor = new StudentCompartor();
        students.sort(compartor);

        System.out.println(studentsNumber);
        for(Student student : students) System.out.println(student);


    }
}
