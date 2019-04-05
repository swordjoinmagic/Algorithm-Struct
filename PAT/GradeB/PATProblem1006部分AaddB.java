package PAT.GradeB;

import java.util.Scanner;

public class PATProblem1006部分AaddB {

    public static void main(String[] args){
        PATProblem1006部分AaddB problem1006 = new PATProblem1006部分AaddB();
        problem1006.Slove();
    }

    int A,B;
    int DA,DB;

    public void Slove(){
        Scanner in = new Scanner(System.in);
        A = in.nextInt();
        DA = in.nextInt();
        B = in.nextInt();
        DB = in.nextInt();

        String strA = String.valueOf(A);
        String strPa = "";
        for(int i=0;i<strA.length();i++){
            if((strA.charAt(i)-'0')==DA){
                strPa += String.valueOf(DA);
            }
        }
        int PA;
        if(!strPa.equals(""))
            PA = Integer.parseInt(strPa);
        else
            PA = 0;

        String strB = String.valueOf(B);
        String strPb = "";
        for(int i=0;i<strB.length();i++){
            if((strB.charAt(i)-'0')==DB){
                strPb += String.valueOf(DB);
            }
        }
        int PB;
        if(!strPb.equals(""))
            PB = Integer.parseInt(strPb);
        else
            PB = 0;

        System.out.println(PA+PB);
    }
}
