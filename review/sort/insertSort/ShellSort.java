package review.sort.insertSort;

import review.sort.BaseSort;

/**
 * 插入排序---希尔排序
 *
 * 算法核心思想:
 *      分组的插入排序,根据两个思想来优化直接插入排序
 *          1. 越接近有序的数组,插入排序效率越高
 *          2. 插入排序中,n越小，排序效率越高
 */
public class ShellSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new ShellSort(-1,81,49,19,38,97,76,13,19).sort();
    }


    public ShellSort(Integer... array){
        this.array = array;
    }

    @Override
    public void sort() {
        // 增量delta
        for(int delta=array.length/2;delta>=1;delta/=2){

            // array[i]为当前待插入有序数数组的元素，
            for(int i=delta;i<array.length;i++){

                // 待插入元素
                int number = array[i];

                int j=i-delta;
                // 在前面的有序数组中,找到要插入的位置
                // 因为是分组的插入排序,所以前面以delta为增量的数组一定是有序数组
                // 找到第一个小于带插入元素的值为止
                for(;j>=0 && array[j]>number;j-=delta){

                    // 将当前元素向后移动,给待插入元素腾出位置来
                    array[j+delta] = array[j];

                }
                // 将待插入元素插入目标位置
                array[j+delta] = number;

            }
        }

        print();
    }
}
