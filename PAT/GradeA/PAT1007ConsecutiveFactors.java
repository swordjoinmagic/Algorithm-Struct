package PAT.GradeA;

import java.util.Scanner;

/**
 */
public class PAT1007ConsecutiveFactors {

    public static void main(String[] args){
        PAT1007ConsecutiveFactors pat1007 = new PAT1007ConsecutiveFactors();
        pat1007.Input();
    }

    int n;
    int len;
    int startMin;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        startMin = n;

        Slove();
    }

    public void Slove(){
        int sqrtN = (int) Math.sqrt(n);
        for(int start=2;start<=sqrtN;start++){

            int tempN = n;

            int l = 0;
            for(;l<=12;l++){
                if(tempN%(start+l) != 0) break;
                tempN /= (start+l);
            }

            if(l==len && start<startMin){
                startMin = start;
            }
            if(l>len){
                startMin = start;
                len = l;
            }
        }

        if(len!=0) {
            System.out.println(len);
            for (int i = startMin; i < startMin + len; i++) {
                if (i == startMin + len - 1)
                    System.out.print(i);
                else
                    System.out.print(i + "*");
            }
        }else {
            System.out.println(1);
            System.out.println(n);
        }
    }
}
