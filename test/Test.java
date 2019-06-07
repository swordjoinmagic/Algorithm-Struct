package test;

import PAT.GradeA.PAT1008DeduplicationonaLinkedList;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) throws IOException {
        Generate();

    }

    public static void Generate() throws FileNotFoundException {

        System.setOut(new PrintStream("C:\\Users\\Administrator\\Desktop\\a.txt"));

        int[] len = new int[]{1000,5000,50000};
        int[] K = new int[]{2,3,5};

        for(int L:len){
            for(int k:K){
                StringBuilder str = new StringBuilder();
                    for(int j=0;j<L;j++)
                    str.append((char) ('a' + (int) (Math.random() * k)));
                System.out.println(str.toString());
            }
        }
    }
}
