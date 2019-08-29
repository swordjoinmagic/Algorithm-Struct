package PAT.GradeAOfficial.test1;

import java.util.Scanner;

public class PAT1042ShufflingMachine_20point {
    public static void main(String[] args){
        PAT1042ShufflingMachine_20point  pat1042 = new PAT1042ShufflingMachine_20point();
        pat1042.Input();
    }

    int repeatTime;
    int[] order = new int[54];

    int[] card = new int[54];

    String[] cardInt2Str;

    public void Input(){
        Scanner in = new Scanner(System.in);

        cardInt2Str = new String[54];
        for(int i=0;i<13;i++)
            cardInt2Str[i] = "S"+(i+1);
        for(int i=13;i<26;i++)
            cardInt2Str[i] = "H"+(i-13+1);
        for(int i=26;i<39;i++)
            cardInt2Str[i] = "C"+(i-26+1);
        for(int i=39;i<52;i++)
            cardInt2Str[i] = "D"+(i-39+1);
        cardInt2Str[52] = "J1";
        cardInt2Str[53] = "J2";

        repeatTime = in.nextInt();
        for(int i=0;i<54;i++) order[i] = in.nextInt()-1;

        Slove();
    }

    public void Slove(){
        for(int i=0;i<54;i++){
            int temp = order[i];
            for(int j=0;j<repeatTime-1;j++)
                temp = order[temp];
            card[temp] = i;
        }

        for(int i=0;i<card.length;i++){
            if(i==0){
                System.out.print(cardInt2Str[card[i]]);
            }else {
                System.out.print(" "+cardInt2Str[card[i]]);
            }
        }
    }
}
