package review.sort.swapSort;

import review.sort.BaseSort;

/**
 * 交换排序-快速排序
 */
public class QuickSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new QuickSort(-1,81,49,19,38,97,76,13,19).sort();
    }

    public QuickSort(Integer... array){
        this.array = array;
    }

    /**
     *
     * @param begin 要排序的数组开始的位置
     * @param end   要排序的数组结束的位置
     */
    private void quickSort(int begin,int end){
        // 当前要排序的序列是有效序列的情况下,进行快排
        if(begin>=0 && begin<array.length && end>=0 && end<array.length && begin<end) {
            // 基准,以数组的开始位置的元素为基准
            int standard =  array[begin];

            int i = begin;      // 左指针,指向开始的位置
            int j = end;        // 右指针,指向结束的位置

            while (i != j) {
                // 从右往左找,直到找到比基准元素小的元素
                while (i < j && array[j] > standard)
                    j--;
                if (i < j) {
                    // 将比基准小的元素放到前面
                    array[i++] = array[j];
                }
                // 从左往右找,直到找到比基准大的元素
                while (i < j && array[i] < standard)
                    i++;
                if (i < j)
                    array[j--] = array[i];
            }
            // i==j时,基准放在下标为i的位置上(也就是中间,保证左边的所有数都比基准小,右边所有数都比基准大)
            array[i] = standard;

            // 继续对左边的序列进行快排
            quickSort(begin, j - 1);
            // 对右边的序列进行快排
            quickSort(i + 1, end);
        }
    }

    @Override
    public void sort() {
        quickSort(0,array.length-1);
        print();
    }
}
