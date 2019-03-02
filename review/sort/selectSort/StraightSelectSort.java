package review.sort.selectSort;

import review.sort.BaseSort;

/**
 * 选择排序算法的核心思想是,每次都找到当前未排序数组中的最大/最小值,并将其放到未排序数组的最前面/最后面,
 * 然后,未排序数组的长度-1,继续寻找未排序数组的最大值/最小值,以此类推.
 *
 * 算法的瓶颈在于查找最大/最小值时所耗费的时间
 *
 * 选择排序-直接选择排序
 *
 * 算法思想:第一趟从n个元素的数据序列中选出关键字最小/大的元素并放到最前/后的位置,
 * 下一趟再从n-1个元素中选出最小/大的元素并放到次前/后的位置,以此类推,经过n-1趟完成排序
 *
 * 算法复杂度: O(n2)
 *
 * 稳定性: 不稳定
 */
public class StraightSelectSort extends BaseSort<Integer> {

    public static void main(String[] args){
        StraightSelectSort sort = new StraightSelectSort(38,97,26,19,38,15);
        sort.sort();
        sort.print();
    }

    public StraightSelectSort(Integer...array){
        this.array = array;
    }


    /**
     * 直接选择排序,每一趟都将目前未排序数组中最大的元素放到最后
     */
    @Override
    public void sort(){
        for(int i=0;i<array.length-1;i++){

            int maxIndex = 0;   // 该趟最大元素在数组中的下标

            // 找出该趟未排序数组中最大的元素(目前已排序元素数量是i个)
            for(int j=0;j<array.length-i;j++){
                if(array[j]>=array[maxIndex]){
                    maxIndex = j;
                }
            }

            // 将该趟未排序数组中最大的数交换到未排序数组的末端
            // 未排序数组的末端即 数组总长度 - 已排序元素个数 - 1(减一操作是因为数组下标从0开始)
            swap(maxIndex, array.length-1-i);

        }

    }


}
