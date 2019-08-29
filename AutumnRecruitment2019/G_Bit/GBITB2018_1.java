package AutumnRecruitment2019.G_Bit;

import java.util.Scanner;

public class GBITB2018_1 {
    public static void main(String[] args){
        GBITB2018_1 gbitb2018_1 = new GBITB2018_1();
        gbitb2018_1.Input();
    }

    int a,b;
    public void Input(){
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b = in.nextInt();

        String bita = Integer.toString(a,2);
        String bitb = Integer.toString(b,2);

        int d = Math.abs(bita.length()-bitb.length());
        // 给短的那个二进制补足0
        if(bita.length()<bitb.length()){
            for(int i=0;i<d;i++)
                bita = "0"+bita;
        }else if(bitb.length()<bita.length()){
            for(int i=0;i<d;i++)
                bitb = "0"+bitb;
        }

        int diff = 0;
        for(int i=0;i<bita.length();i++)
            if(bita.charAt(i)!=bitb.charAt(i))
                diff ++;
        System.out.println(diff);
    }
}
