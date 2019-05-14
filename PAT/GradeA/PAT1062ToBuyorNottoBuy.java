package PAT.GradeA;

import java.util.*;

public class PAT1062ToBuyorNottoBuy {

    public static void main(String[] args){
        PAT1062ToBuyorNottoBuy pat1062 = new PAT1062ToBuyorNottoBuy();
        pat1062.Input();

    }

    private String str;
    private String needStr;

    public void Input(){
        Scanner in = new Scanner(System.in);
        str = in.next();
        needStr = in.next();

        Slove();
    }

    public void Slove(){

        Map<Character,Integer> strMap = new HashMap<>();
        Map<Character,Integer> needStrMap = new HashMap<>();

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            strMap.put(ch,strMap.getOrDefault(ch,0)+1);
        }
        for(int i=0;i<needStr.length();i++){
            char ch = needStr.charAt(i);
            needStrMap.put(ch,needStrMap.getOrDefault(ch,0)+1);
        }

        boolean result = true;

        for(char key : needStrMap.keySet()){
            if(!needStrMap.get(key).equals(strMap.getOrDefault(key, 0))){
                result = false;
            }
            int d = needStrMap.get(key) - strMap.getOrDefault(key,0);
            d = d<0?0:d;
            needStrMap.put(key,d);
        }

        String resultStr = result?"Yes":"No";
        if(result)
            System.out.println(resultStr+" "+(str.length()-needStr.length()));
        else {
            int total = 0;
            for(int data : needStrMap.values()){
                total += data;
            }
            System.out.println(resultStr+" "+(total));
        }
    }
}
