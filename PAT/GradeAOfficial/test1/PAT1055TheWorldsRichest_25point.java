package PAT.GradeAOfficial.test1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个富人和K个查询.
 *      每个查询查询一个年龄段内的所有富人,按他们的财产降序排列
 * 思路:
 *      1. 首先对所有人进行一次排序.
 *      接着用一个数组ages[i]记录
 *      年龄正好大于等于i的富人在数组中的下标
 */
public class PAT1055TheWorldsRichest_25point {

    public static void main(String[] args){
        PAT1055TheWorldsRichest_25point pat1055 = new PAT1055TheWorldsRichest_25point();
        pat1055.Input();
    }

    class People implements Comparable<People>{
        String name;
        int age;
        int worth;

        public People(String name, int age, int worth) {
            this.name = name;
            this.age = age;
            this.worth = worth;
        }

        @Override
        public int compareTo(People o) {
            if(worth>o.worth)
                return -1;
            else if(worth<o.worth)
                return 1;
            else {
                if(age>o.age)
                    return 1;
                else if(age<o.age)
                    return -1;
                else {
                    return name.compareTo(o.name);
                }
            }
        }
    }

    // 人数
    int N;
    // 查询数量
    int K;

    People[] peopleList;
    int[] ages = new int[201];

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        peopleList = new People[N];

        for(int i=0;i<N;i++){
            String name = in.next();
            int age = in.nextInt();
            int worth = in.nextInt();
            People people = new People(name,age,worth);
            peopleList[i] = people;
        }

        Arrays.sort(peopleList);

        Arrays.fill(ages,9999999);

        // 设置ages
        for(int index=0;index<N;index++){
            People people = peopleList[index];

            // ages[age]=index表示年龄正好大于等于age的人在数组中的下标
            for(int age=people.age;age<=200;age++){
                ages[age] = Math.min(ages[age],index);
            }
        }

        Slove(in);
    }

    public void Slove(Scanner in){

    }
}
