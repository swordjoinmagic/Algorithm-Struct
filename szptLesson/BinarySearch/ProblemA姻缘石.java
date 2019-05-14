package szptLesson.BinarySearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 思路:
 *      1. 二分搜索.
 *      对于N个给出的石头进行排序,
 *      然后问题就转化为 找到两个石头 a,b,
 *      求解在数组中找到两个数 使得
 *      (a+b)%L 尽量大 (显而易见最大为L-1)
 *
 *      这时可以固定一个数a,二分查找b,
 *      此时应该二分查找 正好 <= L-1-a 的数
 *      (事实上 a+b = (L+L-1),最后 (a%b)依然等于L-1)
 *      ( 但是后面将a.b限制在[0,L-1]的范围内,
 *        所以a+b永远不可能等于(L+L-1),固二分找b,
 *        这个b应该 <= L-1-a  )
 *
 * 优化策略:
 *      1. 当相同质量的石头出现次数>2时,多出来的石头会变得没有意义
 *      假设相同质量的石头为x,另一个不同质量的石头为y,那么这两种石头(暂且不论数量),
 *      相加求余的结果只可能是:
 *
 *          1) (x+x)%L
 *          2) (y+y)%L
 *          3) (x+y)%L
 *
 *      可以看到如果x数量>2或者y数量>2对最终结果是没有影响的,所以可以在输入时剔除相同质量石头数>2的多出的石头
 *
 *      2. 为了避免溢出,可以将输入的数据全部都限制在[0,L-1]的范围内,
 *      即对输入数据%=L,对计算结果没有影响
 *
 *      为什么没影响?参考百度百科证明:
 *      (a+b) % L = (a%L + b%L) % L;
 *
 *      3. 老班的交叉二分查找?这个不懂,之后研究.
 *
 *
 */
public class ProblemA姻缘石 {

    public static void main(String[] args) throws FileNotFoundException {
        ProblemA姻缘石 problemA = new ProblemA姻缘石();
        problemA.Input();
    }

    // 石头数量
    int N;
    // 余数
    int L;

    // 石头
    List<Integer> stones = new ArrayList<>();

    public void Input() throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\Administrator\\Downloads\\testdata\\A\\1in.txt"));
        Scanner in = new Scanner(System.in);

        while (true){
            N = in.nextInt();
            L = in.nextInt();

            if(N==0 && L==0) break;

            stones.clear();
            Map<Integer,Integer> map = new HashMap<>();

            for(int i = 0; i< N; i++){
                int weight = in.nextInt() % L ;
                map.put(weight,map.getOrDefault(weight,0)+1);
                if(map.get(weight)<=2){
                    stones.add(weight);
                }
            }

            if(stones.size()>2)
                Slove();
            else if(stones.size()==2){
                // 特殊考虑只有两个元素的情况
                int a = stones.get(0);
                int b = stones.get(1);
                System.out.println((a+b)%L);
            }else {
                System.out.println(0);
            }

        }
    }

    public void Slove(){

        int max = 0;

        // 先对数组进行排序
        Collections.sort(stones);

        // 固定其中一个对另一进行二分查找
        for(int i=0;i<stones.size()-2;i++){
            int a = stones.get(i);

            // 二分查找正好 <= L-1-a 的数
            int index = binarySearch(L-1-a,i+1,stones.size());
            index = index<0? -(index) - 2 : index;
            int b = stones.get(index);

            max = Math.max(max,(a+b)%L);
        }

        System.out.println(max);
    }

    /**
     * 返回第一个大于等于 target 的目标数在数组中的下标
     * @param target 要查找的目标数
     * @param start  查找开始位置
     * @param end    查找结束位置
     * @return
     */
    public int binarySearch(int target,int start,int end){
        int low = start;
        int high = end - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = stones.get(mid);

            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
