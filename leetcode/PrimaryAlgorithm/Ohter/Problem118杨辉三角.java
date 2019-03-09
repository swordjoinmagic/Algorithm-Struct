package leetcode.PrimaryAlgorithm.Ohter;

import java.util.*;

/**
 * 思路：
 *      简单逻辑题
 */
public class Problem118杨辉三角 {

    public static void main(String[] args){
        Problem118杨辉三角 problem118 = new Problem118杨辉三角();
        List<List<Integer>> lists = problem118.generate(5);

        for(List<Integer> l : lists){
            for(Integer i : l){
                System.out.print(i+" ");
            }
            System.out.println("");
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if(numRows==0) return result;

        result.add(Arrays.asList(1));

        if(numRows==1) return result;

        for(int i=1;i<numRows;i++){

            // 上一行
            List<Integer> preRow = result.get(i-1);

            // 当前行
            List<Integer> currentRow = new ArrayList<>();

            // 第一个元素和最后一个元素都是1
            currentRow.add(1);

            for(int j=1;j<i;j++){
                currentRow.add(preRow.get(j-1)+preRow.get(j));
            }

            currentRow.add(1);

            result.add(currentRow);
        }

        return result;
    }
}
