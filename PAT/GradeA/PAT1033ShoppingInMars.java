package PAT.GradeA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题意：
 *      1. 给定N个数字，和一个目标值M，
 *      求这N个数字有多少个连续和等于目标值，
 *      如a[1]+a[2]...+a[3]=M,则输出结果为1-3，
 *      有多个结果的情况下，按左边界升序的顺序，顺序输出。
 *
 *      找不到等于M的连续子序列和的情况下，找到正好大于等于M的
 *      连续子序列和，和上面一样，
 *      如果这个正好大于等于M的连续子序列和的结果有多个，按升序输出
 *
 * 思路：
 *      1. 滑动窗口。定义窗口内的数的和<=M.
 *      对于样例1,以滑动窗口举例:
 *      N = 16; M = 15;
 *      array = 3 2 1 5 4 6 8 7 16 10 15 11 9 12 14 13
 *
 *          窗口              left        right
 *      1. 3 <= 15              1           1
 *      2. 3+2 <= 15            1           2
 *      ...
 *      3. 3+2+1+5+4 <= 15      1           5
*
 *      4. 3+2+1+5+4+6 > 15     1           6
 *      ↓
 *      //此时窗口内的数和>M,则窗口左指针右移
 *
 *      5. 2+1+5+4+6 > 15       2           6
 *      ...
 *      6. 5+4+6 <= 15          4           6
 *      7. 5+4+6+8 > 15         4           7
 *      8. 4+6+8 > 15           5           7
 *      9. 6+8 < 15             6           7
 *      10. 6+8+7 > 15          6           8
 *      11. 8+7 <= 15           7           8
 *      ....
 *
 *      以此类推
 *
 *      维护滑动窗口内的数的总值sum,当sum刚好等于M的时候,记录窗口的左右指针
 *
 *      对于没有总值=M的情况,每当Sum刚好大于等于M的时候也会进行记录
 *      (刚好大于等于M的时候,也就是Sum与M的大小关系发生反转那一刻)
 */
public class PAT1033ShoppingInMars {

    class Pair<Key,Value>{
        Key key;
        Value value;

        public Pair(Key k, Value v) {
            this.key = k;
            this.value = v;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }
    }

    public static void main(String[] args){
        PAT1033ShoppingInMars pat1033 = new PAT1033ShoppingInMars();
        pat1033.Input();
    }

    int N,M;

    int[] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        array = new int[N+1];
        for(int i=1;i<=N;i++) array[i] = in.nextInt();

        Slove();
    }

    public void Slove(){
        // 窗口左边界
        int left = 1;
        // 窗口右边界
        int right = 1;

        // 滑动窗口维护的和值
        int sum = array[right];

        List<Pair<Integer,Integer>> result = new ArrayList<>();
        List<Pair<Integer,Integer>> maxMinResult = new ArrayList<>();

        boolean isEqual = false;

        // 小于M的最大值
        int maxMinimized = Integer.MAX_VALUE;

        while (right<=N){
            if(sum==M) {result.add(new Pair<>(left,right));isEqual = true;}
            if(sum<=M){
                right++;
                if(right<=N) sum += array[right];
            }else {
                if(sum<maxMinimized){
                    maxMinimized = sum;
                    maxMinResult.clear();
                    maxMinResult.add(new Pair<>(left,right));
                }else if(sum==maxMinimized){
                    maxMinResult.add(new Pair<>(left,right));
                }
                sum -= array[left];
                left++;
            }
        }

        if(isEqual) {
            for (Pair<Integer, Integer> pair : result) {
                System.out.println(pair.getKey() + "-" + pair.getValue());
            }
        }else {
            for(Pair<Integer,Integer> pair : maxMinResult){
                System.out.println(pair.getKey() + "-" + pair.getValue());
            }
        }
    }
}
