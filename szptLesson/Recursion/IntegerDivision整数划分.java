package szptLesson.Recursion;

public class IntegerDivision整数划分 {

    public static void main(String[] args){
        IntegerDivision整数划分 integerDivision = new IntegerDivision整数划分();
        integerDivision.Init();
        integerDivision.Slove(6,0);
    }

    int[] array;

    public void Init(){
        array = new int[10000];
    }

    /**
     *
     * @param k 要分解的数
     * @param i 当前下标
     */
    public void Slove(int k,int index){

        // 分解完毕
        if(k==0){
            for(int i=0;i<index;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=k;i>=1;i--){
            array[index] = i;
            // 分解过后的数要比分解前的小
            if(index>=1 && array[index]>array[index-1]) continue;
            // 继续分解k-i
            Slove(k - i, index + 1);
        }
    }
}
