package test;

import PAT.GradeA.PAT1008DeduplicationonaLinkedList;

import java.io.*;
import java.util.Random;

public class Test {
    public static String CreateFiveNumber(){
        Random random = new Random();
        return random.nextInt(10)+""+random.nextInt(10)+""+
                random.nextInt(10)+""+random.nextInt(10)+""+
                random.nextInt(10);
    }

    public static void main(String[] args) throws IOException {

        System.setOut(new PrintStream("testData.in"));

        Random random = new Random();
        int n = random.nextInt(7)+3;
        int repeateNumber = random.nextInt(20);
        int repeateCount = random.nextInt(n-2)+2;
        String root = CreateFiveNumber();

        System.out.println(root+" "+n);

        int rootVal = random.nextInt(10);
        String rootNext = CreateFiveNumber();

        System.out.println(root+" "+rootVal+" "+rootNext);

        String preNext = rootNext;
        for(int i=0;i<n-repeateCount-1;i++){
            System.out.print(preNext+" ");
            String next = CreateFiveNumber();
            preNext = next;
            int val = random.nextInt(10);
            System.out.println(val+" "+next);
        }
        for(int i=0;i<repeateCount;i++){
            System.out.print(preNext+" ");
            String next = CreateFiveNumber();
            preNext = next;
            if(i==repeateCount-1)
                System.out.println(repeateNumber+" "+(-1));
            else
                System.out.println(repeateNumber+" "+next);
        }
    }
}
