package review.sort;

/**
 * 所有排序类的基类,定义了简单的交换数组元素,打印数组的方法
 */
public abstract class BaseSort<T> {

    protected T[] array;

    /**
     * 交换下标为i和j的元素
     * @param i
     * @param j
     */
    protected void swap(int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 打印数组元素的方法
     */
    public void print(){
        for(T data : array){
            System.out.print(data+" ");
        }
        System.out.println();
    }

    public abstract void sort();
}
