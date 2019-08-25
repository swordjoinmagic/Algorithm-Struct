package AutumnRecruitment2019.G_Bit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GBITA2018_1 {

    public static boolean isNum(char ch){
        if(ch<='9' && ch>='0')
            return true;
        return false;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        Character[] array = new Character[str.length()];
        for(int i=0;i<str.length();i++) array[i] = str.charAt(i);

        Arrays.sort(array,new Comparator<Character>(){
            @Override
            public int compare(Character o1,Character o2){
                if((isNum(o1) && isNum(o2)) || (!isNum(o1) && !isNum(o2))){
                    if(o1>o2)
                        return 1;
                    else if(o1<o2)
                        return -1;
                    else
                        return 0;
                }else{
                    if(isNum(o1))
                        return 1;
                    else
                        return -1;
                }
            }
        });

        for(char ch : array)
            System.out.print(ch);
    }
}
