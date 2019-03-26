package szptLesson.Other;

import leetcode.Other.Problem152乘积最大子序列;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class CreateTestData {
    public static void main(String[] args){

        int length = 200;

        PrintWriter printWriterIn;
        PrintWriter printWriterOut;

        Problem152乘积最大子序列 problem152 = new Problem152乘积最大子序列();

        try {
            for (int i = 4; i <= length; ) {
                printWriterIn = new PrintWriter("C:\\Users\\Administrator\\Desktop\\TestData\\" + i + ".in");
                printWriterOut = new PrintWriter("C:\\Users\\Administrator\\Desktop\\TestData\\" + i + ".out");

                int n = getRandom(1000,10000);

                printWriterIn.println(n);

                int[] array = new int[n];

                for(int j=0;j<n;j++){
                    int number = getRandom(-10,10);
                    array[j] = number;
                    printWriterIn.print(number+" ");
                }

                printWriterIn.flush();

                int result = problem152.maxProduct(array);
                if(result<1000000){
                    System.out.println("I:"+i+": "+result);
                    printWriterOut.println(result);
                    printWriterOut.flush();
                    i++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getRandom(int min,int max){
        return (int)(min+(max-min)*Math.random());
    }
}
