package review.sort.selectSort;

import review.sort.BaseSort;

/**
 * 选择排序-堆排序
 *
 * 在直接选择排序中,每次都要遍历一遍未排序数组来找到当前未排序数组的最大值/最小值,
 * 遍历的复杂度为O(N),而堆排序则解决了这个问题.
 *
 * 通过堆调整,来通过一个数组建立一个最小堆/最大堆,复杂度仅需O(logN),
 * 这样就将直接选择排序的O(N2)复杂度降为了O(NLog2N)
 */
public class HeapSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new HeapSort(-1,81,49,19,38,97,76,13,19).sort();
    }

    public HeapSort(Integer... array){
        this.array = array;
        length = array.length;
    }

    private int length;
    private int getLen(){
        return length;
    }

    /**
     * 以下标为adjustIndex的节点为最小堆的根节点,
     * 对该最小堆进行调整
     * @param adjustIndex
     */
    private void heapAdjust(int adjustIndex){
        // 左节点
        int left = adjustIndex+adjustIndex;
        // 右节点
        int right = adjustIndex+adjustIndex+1;

        if(left>=getLen()) return;

        int minIndex = adjustIndex; // 最小的那个数在数组的下标
        // 对左节点与根节点进行大小比较
        minIndex = array[adjustIndex]<array[left] ? adjustIndex : left;

        // 对右节点与当前最小的数进行比较
        if(right<getLen()){
            minIndex = array[minIndex]<array[right] ? minIndex : right;
        }

        // 将最小的数交换至父节点AdjustIndex
        if(minIndex!=adjustIndex) {
            swap(minIndex, adjustIndex);

            // 以被调整的子节点(左节点或右节点)作为另一个堆的根节点,调整此最小堆
            heapAdjust(minIndex);
        }
    }

    /**
     * 从下标n/2开始建堆,即最后一个元素的下标/2开始
     */
    private void makeHeap(){
        for(int i=(getLen()-1)/2;i>=1;i--){
            heapAdjust(i);
        }
    }

    @Override
    public void sort() {
        while (getLen()>1) {
            // 将当前未排序数组建立为最小堆
            makeHeap();

            // 将最小堆根节点交换到数组的最后，
            // 也就是,将当前未排序数组的最小值放到未排序数组的最后(类似直接选择排序)
            swap(1, getLen() - 1);   // 1是根节点

            // 未排序数组长度减一
            length -= 1;
        }
        print();
    }
}
