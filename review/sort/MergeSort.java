package review.sort;

/**
 * 归并排序
 */
public class MergeSort extends BaseSort<Integer> {

    public static void main(String[] args){
        new MergeSort(-1,81,49,19,38,97,76,13,19).sort();
    }

    public MergeSort(Integer... array){
        this.array = array;
    }

    /**
     * (条件begin1<begin2)
     * 一次归并,将X数组中的[begin1,begin1+n-1]的无序元素
     * 和X数组中的[begin2,begin2+n-1]的无序元素合并为
     * 一个有序序列,放到Y数组的[begin1,begin1+n+n-1]位置上去
     * @param X
     * @param Y
     * @param begin1
     * @param begin2
     * @param n
     */
    private void merge(Integer[] X,Integer[] Y,int begin1,int begin2,int n){
        int i = begin1,k = begin1;
        int j = begin2;

        while (i<begin1+n && j<begin2+n && j< X.length){
            if(X[i]<X[j]){
                Y[k++] = X[i++];
            }else {
                Y[k++] = X[j++];
            }
        }

        // 将子序列中剩余元素赋值到Y中
        while (i<begin1+n && i< X.length){
            Y[k++] = X[i++];
        }

        // 将另一个子序列剩余元素复制到Y中
        while (j<begin2+n && j< X.length){
            Y[k++] = X[j++];
        }
    }

    /**
     * 一趟归并,将X中若干相邻子序列两两归并到Y中,子序列长度为n
     * @param X
     * @param Y
     * @param n
     */
    private void mergePass(Integer[] X,Integer[] Y,int n){
        for(int i=0;i< X.length;i+=2*n){
            merge(X,Y,i,i+n, n);
        }
        print();
    }

    @Override
    public void sort() {
        Integer[] Y = new Integer[array.length];
        int n = 1;
        while (n<array.length){
            mergePass(array,Y,n);
            n *= 2;
            if(n<array.length){
                mergePass(Y,array,n);
                n *= 2;
            }
        }
    }


}
