package PAT.GradeAOfficial.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给出N个学生分数,找到女生里的最高分和男生里的最低分,
 *      并求出他们分数的差值
 * 思路:
 *      1. 模拟.
 */
public class PAT1036BoysvsGirls_25point {

    public static void main(String[] args){
        PAT1036BoysvsGirls_25point pat1036 = new PAT1036BoysvsGirls_25point();
        pat1036.Input();
    }

    class Student implements Comparable<Student>{
        String name;
        String gender;
        String id;
        int grade;

        public Student(String name, String gender, String id, int grade) {
            this.name = name;
            this.gender = gender;
            this.id = id;
            this.grade = grade;
        }

        @Override
        public int compareTo(Student o) {
            if(grade > o.grade)
                return -1;
            else if(grade < o.grade)
                return 1;
            else
                return 0;
        }
    }

    int N;

    List<Student> femaleList = new ArrayList<>();
    List<Student> maleList = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        for(int i=0;i<N;i++){
            String name = in.next();
            String gender = in.next();
            String id = in.next();
            int grade = in.nextInt();
            Student student = new Student(name,gender,id, grade);
            if(gender.equals("M"))
                maleList.add(student);
            else
                femaleList.add(student);
        }

        Slove();
    }

    public void Slove(){
        Collections.sort(femaleList);
        Collections.sort(maleList);

        if(femaleList.size()==0 || maleList.size()==0){
            if(femaleList.size()==0)
                System.out.println("Absent");
            Student student = femaleList.size()==0?maleList.get(maleList.size()-1) : femaleList.get(0);
            System.out.println(student.name+" "+student.id);
            if(maleList.size()==0)
                System.out.println("Absent");
            System.out.println("NA");
        }else {
            Student hightestFemale = femaleList.get(0);
            Student lowestMale = maleList.get(maleList.size()-1);
            System.out.println(hightestFemale.name+" "+hightestFemale.id);
            System.out.println(lowestMale.name+" "+lowestMale.id);
            System.out.println(hightestFemale.grade-lowestMale.grade);
        }
    }
}
