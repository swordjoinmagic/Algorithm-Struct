package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 二项式乘法.
 *      每行多项式格式如下:
 *      K N1 a1 N2 a2 N3 a3
 *      其中N1是指数,a1是系数,
 *
 *      最后要求多项式每一项的指数和系数,这里要注意指数要相加,
 *      如 ab 的指数为2,因为(a的1次方)1+(b的1次方)1=2
 *
 * 思路:
 *      1. map模拟.
 */
public class PAT1009ProductofPolynomials_25point {

    public static void main(String[] args){
        PAT1009ProductofPolynomials_25point pat1009 = new PAT1009ProductofPolynomials_25point();
        pat1009.Input();
    }

    // 指数为键,系数为值
    Map<Integer,Double> map = new HashMap<>();
    Map<Integer,Double> map2 = new HashMap<>();
    TreeMap<Integer,Double> result;

    public void Input(){
        Scanner in = new Scanner(System.in);
        int k1 = in.nextInt();
        for(int i=0;i<k1;i++){
            int a = in.nextInt();
            double b = in.nextDouble();
            map.put(a,b);
        }

        int k2 = in.nextInt();
        for(int i=0;i<k2;i++){
            int a = in.nextInt();
            double b = in.nextDouble();
            map2.put(a,b);
        }

        result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2)
                    return -1;
                else if(o1 < o2)
                    return 1;
                else
                    return 0;
            }
        });

        // 将多项式1的每一项与多项式2的每一项相乘
        for(int a1 : map.keySet()){
            double b1 = map.get(a1);

            for(int a2 : map2.keySet()){

                double b2 = map2.get(a2);

                // 系数相乘,指数相加
                int a = a1+a2;
                double b = b1*b2;

                result.put(a,result.getOrDefault(a,0.0)+b);
            }
        }

        int size = result.size();
        List<Double> values = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();
        for(int key : result.keySet()){
            double value = result.get(key);
            if(value==0) {size--;continue;}
            keys.add(key);
            values.add(value);
        }

        System.out.print(size);
        for(int i=0;i<size;i++){
            System.out.format(" %d %.1f",keys.get(i),values.get(i));
        }
    }


}
