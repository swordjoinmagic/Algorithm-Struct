package szptOJ.Problem2019_2_24;

import java.util.Scanner;

public class ProblemE {
    public static void main(String[] args){
        ProblemE problemE = new ProblemE();
        problemE.Input();
        problemE.Slove();
    }

    private int n;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
    }

    public void Slove(){
        int a = n/3;
        if(a%3==0)
            a += 1;

        n -= a;

        int b = n/2;
        if(b%3==0)
            b += 1;

        n -= b;

        int c = n;

        System.out.print(a+" "+b+" "+c);
    }
}
