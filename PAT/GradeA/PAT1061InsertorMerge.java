package PAT.GradeA;

/**
 * 题目描述:
 *      1. 给定一个未排序序列和一个进行了x趟排序的序列,
 *      输出该序列是使用了插入排序还是归并排序,
 *      并输出x+1趟排序后的序列
 * 思路:
 *      1. 模拟.
 *      额外开辟两个数组用来存插入排序的序列和归并排序的序列,
 *      每趟排序后进行比较
 *
 *      插入排序的思路:
 *      假设有序列 5, 3, 7 ,9 ,1, 10
 *          1. 初始状态 5 为已排序序列,此时待插入数据是 3,7,9,1,10
 *          2. 向前查找3的插入位置,为0,将3插入下标0的位置,以此类推
 */
public class PAT1061InsertorMerge {
    /**
     * 一趟插入排序
     * @param sortedLength 已排序的序列的长度(表示未排序序列的第一个元素在序列中的下标)
     * @param array 待排序序列
     * @return 返回新的已排序序列的长度
     */
    public int OneInsertSort(int sortedLength,int[] array){
        // 待插入数据
        int data = array[sortedLength];

        // 从后往前,找插入位置,也就是当array[i-1]<data时,此时i就是插入位置
        for(int i=sortedLength;i>=1;i--){
            if(array[i-1]>data){

                // 交换
//                int temp =

            }
        }

        return 0;
    }
}
