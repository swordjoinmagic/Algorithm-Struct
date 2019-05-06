package test;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int[] arr = new int[2 * n];
        insertNum(arr, 1, n);
    }


    static boolean insertNum(int[] arr, int insertNum, int endNum) {
        boolean flag = false;
        for (int i = 0; i < arr.length - insertNum - 1; i++) {
            if (arr[i] == 0 && arr[i + insertNum + 1] == 0) {
                arr[i] = insertNum;
                arr[i + insertNum + 1] = insertNum;
                if (insertNum == endNum) {
                    System.out.print(arr[0]);
                    for (int n=1;n<arr.length;n++) {
                        System.out.print(" "+arr[n]);
                    }
                    System.out.println();
                    flag=true;
                } else {
                    if (!insertNum(arr, insertNum + 1, endNum)) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
                arr[i] = 0;
                arr[i + insertNum + 1] = 0;
            }
        }
        return flag;
    }
}