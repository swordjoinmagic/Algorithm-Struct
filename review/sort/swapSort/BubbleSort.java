package review.sort.swapSort;

import review.sort.BaseSort;

/**
 *
 * 交换排序-冒泡排序
 *
 * 算法思想:
 *      冒泡排序通过不断的对比相邻元素的大小,来将当前未排序数组的最大/最小值交换到未排序数组的最后,
 *      然后未排序数组的长度-1,后面的操作以此类推
 *
 *
 *
 */
public class BubbleSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new BubbleSort(-1,81,49,19,38,97,76,13,19).sort();
    }

    public BubbleSort(Integer... array){
        this.array = array;
    }

    @Override
    public void sort() {

        // 循环n-1次
        for(int i=0;i<array.length-1;i++){

            for(int j=1;j<array.length-i;j++){
                // 将最大值交换到最后去
                if(array[j]<array[j-1]){
                    swap(j,j-1);
                }
            }

        }

        print();
    }
}
