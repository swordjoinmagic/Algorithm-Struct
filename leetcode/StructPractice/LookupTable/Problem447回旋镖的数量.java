package leetcode.StructPractice.LookupTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路:
 *      1. 暴力.遍历i、j、k，
 *      判断distance(A[i],A[j])==distance(A[i],A[k])?
 *      时间复杂度O(n3)
 *
 *      2.
 */
public class Problem447回旋镖的数量 {

    public static void main(String[] args){
        Problem447回旋镖的数量 problem447 = new Problem447回旋镖的数量();

        int[][] points = new int[][]{
                {0,0},
                {1,0},
                {2,0}
        };

        int result = problem447.numberOfBoomerangs(points);
        System.out.println(result);
    }

    /**
     * 计算两点之间距离的平方
     * @param a
     * @param b
     * @return
     */
    private int distance(int[] a,int[] b){
        return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]);
    }

    public int numberOfBoomerangs(int[][] points) {

        Map<Integer,Integer> map = new HashMap<>();

        int result = 0;

        for(int i=0;i<points.length;i++){
            map.clear();
            for(int j=0;j<points.length;j++){
                int distancce = distance(points[i],points[j]);

                map.put(distancce,map.getOrDefault(distancce,0)+1);
            }
            for(int key:map.keySet()){
                int total = map.get(key);
                result += total*(total-1);
            }
        }

        return result;
    }
}
