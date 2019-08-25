package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个学生,还有需要排序的属性.
 *      求排序后的结果
 *
 * 思路:
 *      1. 直接模拟.
 */

public class PAT1028ListSorting_25point {

    public static void main(String[] args){
        PAT1028ListSorting_25point pat1028 = new PAT1028ListSorting_25point();
        pat1028.Input();
    }

    class Student{
        String id;
        String name;
        int grade;

        int idWithInt;

        public Student(String id, String name, int grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;

            idWithInt = Integer.parseInt(id);
        }

        public String toString(){
            return String.format("%s %s %d",id,name,grade);
        }
    }

    // 记录总数
    int N;

    // 要对第几列进行排序
    int C;

    Student[] students;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        C = in.nextInt();

        students = new Student[N];

        for(int i=0;i<N;i++){
            String id = in.next();
            String name = in.next();
            int grade = in.nextInt();
            students[i] = new Student(id,name, grade);
        }

        Slove();
    }

    public void Slove(){
        switch (C){
            case 1:
                // 根据id排序
                Arrays.sort(students,new Comparator<Student>(){
                    @Override
                    public int compare(Student o1,Student o2){
                        return Integer.compare(o1.idWithInt,o2.idWithInt);
                    }
                });
                break;
            case 2:
                // 根据name排序
                Arrays.sort(students,new Comparator<Student>(){
                    @Override
                    public int compare(Student o1,Student o2){
                        int result = o1.name.compareTo(o2.name);
                        if(result > 0)
                            return 1;
                        else if(result < 0)
                            return -1;
                        else {
                            return Integer.compare(o1.idWithInt,o2.idWithInt);
                        }
                    }
                });
                break;
            case 3:
                // 根据成绩排序
                Arrays.sort(students,new Comparator<Student>(){
                    @Override
                    public int compare(Student o1,Student o2){
                        if(o1.grade>o2.grade){
                            return 1;
                        }else if(o1.grade<o2.grade){
                            return -1;
                        }else {
                            return Integer.compare(o1.idWithInt,o2.idWithInt);
                        }
                    }
                });
                break;
        }

        for(Student student : students){
            System.out.println(student);
        }
    }
}
