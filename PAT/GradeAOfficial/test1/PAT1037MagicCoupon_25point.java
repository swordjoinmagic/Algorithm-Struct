package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定N个coupons,每个coupons都有一定数量,和
 *      Np个产品的价格,当使用coupons买单时,可以获得
 *      coupons[i]*product[j]的价格.
 *      coupons和product的价格都有可能是负数
 *
 * 思路:
 *      1. 贪心.
 *      先将coupons和product都按数量/价格排序.
 *      然后顺序遍历coupons,设遍历变量为i,
 *      那么一直遍历到 coupons[i]*product[i] < 0为止,
 *      同理,从后往前遍历,直到
 *      coupons[i]<0 && product[i]>0 或者
 *      coupons[i]>0 && product[i]<0 或者
 *      coupons[i]>0 && product[i]>0
 *      为止
 */
public class PAT1037MagicCoupon_25point {

    public static void main(String[] args){
        PAT1037MagicCoupon_25point pat1037 = new PAT1037MagicCoupon_25point();
        pat1037.Input();
    }

    int Nc,Np;

    List<Integer> couponsPositive = new ArrayList<>();
    List<Integer> couponsNegative = new ArrayList<>();
    List<Integer> productsPositive = new ArrayList<>();
    List<Integer> productsNegative = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        Nc = in.nextInt();

        for(int i=0;i<Nc;i++) {
            int num = in.nextInt();
            if(num>0) couponsPositive.add(num);
            else couponsNegative.add(num);
        }
        Np = in.nextInt();

        for(int i=0;i<Np;i++) {
            int num = in.nextInt();
            if(num>0) productsPositive.add(num);
            else productsNegative.add(num);
        }

        Slove();
    }

    public void Slove(){
        couponsPositive.sort((o1, o2) -> -Integer.compare(o1, o2));
        productsPositive.sort((o1, o2) -> -Integer.compare(o1, o2));
        Collections.sort(couponsNegative);
        Collections.sort(productsNegative);

        int total = 0;

        int minLength = couponsPositive.size()>productsPositive.size() ? productsPositive.size() : couponsPositive.size();
        for(int i=0;i<minLength;i++)
            total += couponsPositive.get(i) * productsPositive.get(i);

        minLength = couponsNegative.size() > productsNegative.size() ? productsNegative.size() : couponsNegative.size();
        for(int i=0;i<minLength;i++)
            total += couponsNegative.get(i) * productsNegative.get(i);

        System.out.println(total);
    }
}
