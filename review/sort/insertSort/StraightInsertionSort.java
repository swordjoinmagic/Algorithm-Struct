package review.sort.insertSort;

import review.sort.BaseSort;

/**
 * 插入排序----直接插入排序
 */
public class StraightInsertionSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new StraightInsertionSort(-1,81,49,19,38,97,76,13,19).sort();
    }


    public StraightInsertionSort(Integer... array){
        this.array = array;
    }

    @Override
    public void sort() {

        // 下标从1开始,循环n-1次进行排序
        for(int i=1;i<array.length;i++){

            // 当前要进行插入排序的数
            int value = array[i];

            int j=i-1;
            // 从前面已经排好序的数组中,找到要插入的位置
            // 具体来说,按从小到大的顺序,就是,从下标i-1开始,
            // 一直往前面找到第一个小于当前数的数,它的下标+1即为要插入的位置
            for(;j>=0 && array[j]>value;j--){
                // 将前面较大元素向后移动
                array[j+1] = array[j];
            }

            // j+1为当前要插入的位置
            array[j+1] = value;

        }

        print();
    }
}
