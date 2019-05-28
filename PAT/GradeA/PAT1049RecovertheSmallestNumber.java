package PAT.GradeA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PAT1049RecovertheSmallestNumber {

    public static void main(String[] args){
        PAT1049RecovertheSmallestNumber pat1049 = new PAT1049RecovertheSmallestNumber();
        pat1049.Input();
    }

    int N;
    String[] strs;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        strs = new String[N];
        for(int i=0;i<N;i++) strs[i] = in.next();

        Arrays.sort(strs,new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return  (o1+o2).compareTo(o2+o1);
            }
        });

        Slove();
    }

    public void Slove(){
        StringBuilder result = new StringBuilder();
        for(String str:strs) result.append(str);

        int i = 0;
        while (i<result.length() && result.charAt(i)=='0') i++;

        if(i!=result.length())
            System.out.println(result.substring(i));
        else
            System.out.println(0);
    }
}
