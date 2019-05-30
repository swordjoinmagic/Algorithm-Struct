package PAT.GradeA;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个序列及该序列部分排序后的结果,
 *      输出该序列是使用插入排序还是堆排序
 * 思路:
 *      1. 对于升序序列,插入排序从前往后排序,
 *      堆排序则采用大顶堆,从后往前排序,
 *      还是很好区分的.
 *      对于一个序列,前面有序就是插排
 *      后面有序就是堆排
 */
public class PAT1009InsertionorHeapSort {

    public static void main(String[] args){
        PAT1009InsertionorHeapSort pat1009 = new PAT1009InsertionorHeapSort();
        pat1009.Input();
    }

    int N;

    int[] array;
    int[] sortedArray;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        array = new int[N];
        sortedArray = new int[N];
        for(int i=0;i<N;i++) array[i] = in.nextInt();
        for (int i=0;i<N;i++) sortedArray[i] = in.nextInt();
        Slove();
    }

    /**
     * 一趟插入排序
     * @param sortedLength 已排好序的序列的长度,也就是第一个无序的数的下标
     * @return 返回新的已排好序的序列的长度
     */
    public int InsertSortOnePass(int[] array,int sortedLength){
        // 第一个无序的数
        int number = array[sortedLength];

        // 如果当前无序的数是数组中最后一个数,那么直接返回
        if(sortedLength==array.length-1) return array.length;

        // 将这个数插入到前面已排序好的序列
        for(int i=sortedLength;i>=1;i--){
            // 找到第一个小于number的数
            if(array[i-1]>array[i]){
                // 交换
                int temp = array[i];
                array[i] = array[i-1];
                array[i-1] = temp;
            }else break;
        }

        return sortedLength+1;
    }

    /**
     * 调整以array[index]作为根节点的大顶堆
     * @param rootIndex  小顶堆根节点下标
     * @param heapLength 当前堆的大小
     */
    public void HeapAdjust(int[] array,int rootIndex,int heapLength){

        // 较大数
        int max = 0;
        // 较大数的下标
        int maxIndex = -1;

        int left = rootIndex+rootIndex+1;
        int right = rootIndex+rootIndex+2;

        // 与左节点进行比较
        if(left<heapLength && array[left]>array[rootIndex]){
            max = array[left];
            maxIndex = left;
        }else {
            max = array[rootIndex];
            maxIndex = rootIndex;
        }

        // 与右节点进行比较
        if(right<heapLength){
            if(array[right]>max){
                max = array[right];
                maxIndex = right;
            }
        }

        if(maxIndex!=rootIndex) {
            // 交换根节点和最大数
            int temp = array[rootIndex];
            array[rootIndex] = array[maxIndex];
            array[maxIndex] = temp;

            // 此时minIndex处的数为之前根节点的数,也就是说
            // 这个位置是刚刚被换下来的,还没调整过,对该位置继续进行调整
            HeapAdjust(array,maxIndex, heapLength);
        }
    }

    /**
     * 一趟堆排序,
     * 这里对于升序序列来说,
     * 就是对 [ 0 , N-sorteedLength) 建立大顶堆
     * @param array
     * @param sortedLength 已排序数组的长度
     * @return 返回新的已排序数组的长度
     */
    public int HeapSortOnePass(int[] array,int sortedLength){
        // 对序列 [ 0 , N-sorteedLength) 建立小顶堆
        // 即从最后一个有孩子的节点开始,向上一路调整

        // 堆大小
        int heapLength = N - sortedLength;

        for(int i=(heapLength-1)/2;i>=0;i--){
            HeapAdjust(array,i,heapLength);
        }

        // 此时根节点是最大值,将该根节点交换到最后,且堆大小-1
        int temp = array[0];
        array[0] = array[heapLength-1];
        array[heapLength-1] = temp;

        sortedLength += 1;
        heapLength = N - sortedLength;
        for(int i=(heapLength-1)/2;i>=0;i--){
            HeapAdjust(array,i,heapLength);
        }

        return sortedLength+1;
    }

    public void Slove(){
        // 已排序数组从前往后，有序序列长度
        int sortedLengthFrontToBack = 1;
        // 已排序数组从后往前，有序序列长度
        int sortedLengthBackToFront = 1;

        for(int i=1;i<N;i++){
            if(sortedArray[i]>sortedArray[i-1])
                sortedLengthFrontToBack++;
            else
                break;
        }

        for(int i=N-1;i>=1;i--){
            if(sortedArray[i]>sortedArray[i-1])
                sortedLengthBackToFront++;
            else
                break;
        }
        while (true){
            int temp = N-sortedLengthBackToFront;
            if(temp<0 || temp>=N) break;
            boolean T = true;
            for(int i=0;i<temp;i++){
                if(sortedArray[i]>sortedArray[temp]) {
                    sortedLengthBackToFront--;
                    T = false;
                    break;
                }
            }
            if(T) break;
        }

        if(sortedLengthFrontToBack > sortedLengthBackToFront){
            // 插入排序
            System.out.println("Insertion Sort");
            InsertSortOnePass(sortedArray,sortedLengthFrontToBack);
            for(int i:sortedArray) System.out.print(i+" ");
        }else {
            // 归并排序
            System.out.println("Heap Sort");
            HeapSortOnePass(sortedArray,sortedLengthBackToFront);
            for(int i:sortedArray) System.out.print(i+" ");
        }
    }

}
