package test;

import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    List<Pair<Double,Double>> list;

    public void Input() throws FileNotFoundException {
        list = new ArrayList<>();

        System.setOut(new PrintStream("C:\\Users\\Administrator\\Desktop\\a.csv"));
        System.setIn(new FileInputStream("C:\\Users\\Administrator\\Desktop\\meituanwm_restaurant_0701_buchong.csv"));
        Scanner in = new Scanner(System.in);
        in.nextLine();
        while (true){
            try {
                String s = in.nextLine();
                if(s.equals("0")) break;
                String[] str = s.split(",");
                double a = Double.parseDouble(str[1]);
                double b = Double.parseDouble(str[2]);

                list.add(new Pair<>(a,b));
            }catch (Exception e){}
        }

        list.sort((o1, o2) -> {
            if(o1.getKey() > o2.getKey()){
                if(o1.getValue() > o2.getValue()){
                    return 1;
                }else if(o1.getValue() < o2.getValue()){
                    return -1;
                }else {
                    return 0;
                }
            }else if(o1.getKey() < o2.getKey()){
                if(o1.getValue() > o2.getValue())
                    return 1;
                else if(o1.getValue() < o2.getValue())
                    return -1;
                return 0;
            }else{
                return 0;
            }
        });

        for(int i=0;i<list.size();i++){
            Pair<Double,Double> pair = list.get(i);
            System.out.print(pair.getKey()+","+pair.getValue());
            if(i!=list.size()-1){
                double sum1 = pair.getKey()+pair.getValue();
                double sum2 = list.get(i+1).getKey() + list.get(i+1).getValue();
                System.out.println(", different:"+ Math.abs(sum1-sum2));
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().Input();
    }
}