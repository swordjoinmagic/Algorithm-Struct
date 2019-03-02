package review.otherStruct;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import java.util.Scanner;
import java.util.concurrent.FutureTask;

/**
 * 并查集
 */
public class FindAndUnionSet {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();

        FindAndUnionSet findAndUnionSet = new FindAndUnionSet(n);

        for(int i=1;i<=m;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            if(findAndUnionSet.Find(a) != findAndUnionSet.Find(b)){
                findAndUnionSet.Union(a,b);
            }
        }

        for (int i=1;i<=p;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            if(findAndUnionSet.Find(a)==findAndUnionSet.Find(b)){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }
    }

    // 父节点数组,father[x] = y表示x的父节点是y
    private int[] father;
    // 节点数量
    private int n;

    /**
     * 初始化并查集,需要给出节点数量的参数
     * @param n
     */
    public FindAndUnionSet(int n){
        this.n = n;

        // 初始化父节点数组
        father = new int[n+1];

        // 初始父节点数组每个节点都指向-1
        for(int i=0;i<=n;i++){
            father[i] = -1;
        }
    }

    /**
     * 查集操作,返回x元素所属集合的根节点
     * @param x
     * @return
     */
    public int Find(int x){
        int root = x;

        // 一直找到x元素的根节点
        // (根节点因为没有父节点,所以其父节点是个负数)
        while(father[root] >= 0){
           root = father[root];
        }

        // 路径压缩
        // 从节点x处,一路向上到根节点,
        // 把路上经过的每一个节点的父节点都变成根节点
        while (root!=x){
            // 获得当前节点的父节点
            int fx = father[x];
            // 将当前节点的父节点设为根节点
            father[x] = root;
            // 当前节点继续向上追溯
            x = fx;
        }

        return root;
    }

    /**
     * 并集操作,将元素x和y合并到同一个集合中
     * @param x
     * @param y
     */
    public void Union(int x,int y){
        int fx = Find(x);
        int fy = Find(y);
        // 如果要合并的两个节点处于同一个集合
        // 那么无需进行合并
        if(fx==fy)
            return;

        // temp表示x集合和y集合内元素的节点的总和
        // 即要合并的这两棵树的总节点数
        int temp = father[fx]+father[fy];

        // 将节点数小的变成大的树的子树
        // 这一步的目的是为了减少在路径压缩的时候向上追溯的次数
        if(father[fx] > father[fy]){
            father[fy] = fx;
            father[fx] = temp;
        }else {
            father[fx] = fy;
            father[fy] = temp;
        }
    }
}
