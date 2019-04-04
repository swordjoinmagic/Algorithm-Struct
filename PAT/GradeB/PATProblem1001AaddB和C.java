package PAT.GradeB;

import java.util.Scanner;

public class PATProblem1001AaddB和C {
    public static void main(String[] args){
        PATProblem1001AaddB和C patProblem1001 = new PATProblem1001AaddB和C();
        patProblem1001.Input();
    }

    private long a,b,c;
    private int T;

    public void Input(){
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for(int i=1;i<=T;i++) {
            a = in.nextLong();
            b = in.nextLong();
            c = in.nextLong();
            System.out.println("Case #"+i+": "+Slove(a,b,c));
        }
    }

    public boolean Slove(long a,long b,long c){
        return a+b>c;
    }
}
