package PAT.GradeA;

import java.util.*;

/**
 * 题意：
 *      1. 给定N个集合，求两个集合的相似度。
 *      两个集合的相似度被定义为：
 *          similarity = 交集 / 并集 * 100%
 */
public class PAT1021SetSimilarity {

    public static void main(String[] args){
        PAT1021SetSimilarity pat1021 = new PAT1021SetSimilarity();
        pat1021.Input();
    }

    // 集合数量
    int N;
    // 询问次数
    int K;

    List<Set<Integer>> list;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        list = new ArrayList<>();
        list.add(null);

        for(int i=0;i<N;i++){
            int m = in.nextInt();

            Set<Integer> l = new HashSet<>();

            for(int j=0;j<m;j++){
                l.add(in.nextInt());
            }

            list.add(l);
        }

        K = in.nextInt();
        for(int i=0;i<K;i++){
            int s1 = in.nextInt();
            int s2 = in.nextInt();
            Slove(s1,s2);
        }
    }

    public void Slove(int s1,int s2){
        Set<Integer> set1 = list.get(s1);
        Set<Integer> set2 = list.get(s2);

        // 并集
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);

        // 交集
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        double result = ((double) intersection.size() / union.size()) * 100;

        System.out.format("%.1f%%",result);
    }
}
