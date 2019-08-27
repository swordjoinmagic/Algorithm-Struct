package PAT.GradeAOfficial.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定M串字符串,将字符串中的字符替换 ,规则如下:
 *
 *      1 -> @
 *      0 -> %,
 *      l -> L,
 *      O -> o
 *
 * 思路:
 *
 */
public class PAT1035Password_20point {

    public static void main(String[] args){
        PAT1035Password_20point pat1035 = new PAT1035Password_20point();
        pat1035.Input();
    }

    class Account{
        String name;
        String password;

        public Account(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    int M;

    boolean[] alphet;

    public void GetAlphet(String str){
        Arrays.fill(alphet,false);
        for(int i=0;i<str.length();i++){
            alphet[str.charAt(i)] = true;
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        alphet = new boolean[250];
        M = in.nextInt();

        int count = 0;
        List<Account> list = new ArrayList<>();

        for(int i=0;i<M;i++){
            String name = in.next();
            String password = in.next();
            GetAlphet(password);
            if(alphet['1'] || alphet['l'] || alphet['O'] || alphet['0']){
                count++;
                password = password.replace('1','@');
                password = password.replace('l','L');
                password = password.replace('0','%');
                password = password.replace('O','o');

                list.add(new Account(name,password));

            }
        }

        if(count==0){
            if(M==1){
                System.out.println("There is 1 account and no account is modified");
            }else {
                System.out.println("There is "+M+" accounts and no account is modified");
            }
        }else {
            System.out.println(count);
            for(Account account : list){
                System.out.println(account.name + " "+account.password);
            }
        }
    }
}
