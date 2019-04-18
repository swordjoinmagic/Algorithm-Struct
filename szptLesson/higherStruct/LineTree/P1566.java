package szptLesson.higherStruct.LineTree;

import java.util.Scanner;

public class P1566 {

    public static void main(String[] args){
        P1566 p1566 = new P1566();
        p1566.Input();

    }

    class TreeNode{

        // 该区间的下限与上限
        int l,r;

        // 该区间保存的最大值
        int max;;

        // 左孩子右孩子
        TreeNode left,right;

        public TreeNode(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    // 线段树树根
    private TreeNode root;

    // 学生人数
    private int N;
    // 操作的数目
    private int M;
    // 保存学生成绩
    private int[] scores;

    /**
     * 初始化线段树,区间[l,r]，包含r
     */
    public TreeNode MakeLineTree(int l,int r){
        TreeNode node = new TreeNode(l,r);
        if(l==r){
            node.max = scores[l];
            return node;
        }

        int mid = (l+r)/2;

        node.left = MakeLineTree(l,mid);
        node.right = MakeLineTree(mid+1,r);

        node.max = Math.max(node.left.max,node.right.max);

        return node;
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            N = in.nextInt();
            M = in.nextInt();
            scores = new int[N];

            for (int i = 0; i < N; i++) {
                scores[i] = in.nextInt();
            }

            // 初始化线段树
            root = MakeLineTree(0, N - 1);

            Slove(in);
        }
    }


    /**
     * 更新区间内下标为index的数为Value
     * @param index
     * @param value
     * @param currentNode
     */
    public void Update(int index,int value,TreeNode currentNode){

        if(index==currentNode.l && index==currentNode.r){
            currentNode.max = value;
            return;
        }

        int mid = (currentNode.l+currentNode.r)/2;

        // 判断该数在左区间还是右区间
        if(index<=mid){
            // 进入左区间继续更新
            Update(index,value,currentNode.left);

            // 要更新的数在左区间
            currentNode.max  = Math.max(currentNode.left.max, currentNode.right.max);

        }else if(index>mid){

            // 进入右区间进行更新
            Update(index,value,currentNode.right);

            // 要更新的数在右区间
            currentNode.max = Math.max(currentNode.left.max, currentNode.right.max);
        }

        currentNode.max = Math.max(currentNode.left.max,currentNode.right.max);
    }

    /**
     * 查询区间 [l,r] 内的最大值
     * @param l
     * @param r
     * @return
     */
    public int Query(int l,int r,TreeNode currentNode){

        // 当前区间是要查询的区间的子区间
        if(l<=currentNode.l && r>=currentNode.r)
            return currentNode.max;

        int mid = (currentNode.l+currentNode.r)/2;

        if(r<=mid){
            // 查询区间在当前区段的左区间

            // 进入左区间进行查找
            return Query(l,r,currentNode.left);

        }else if(l>mid){
            // 查询区间在当前区段的右区间

            // 进入右区间查找
            return Query(l,r, currentNode.right);

        }else {
            // 查询区间在当前区段的中间
            return Math.max(Query(l,mid,currentNode.left),Query(mid+1,r,currentNode.right));
        }
    }

    public void Slove(Scanner in){

        in.nextLine();
        for(int i=0;i<M;i++){

            String line = in.nextLine();
            char command = line.charAt(0);
            String[] params = line.split(" ");
            int a = Integer.parseInt(params[1]);
            int b = Integer.parseInt(params[2]);

            switch (command){
                case 'Q':
                    a--;b--;
                    System.out.println(Query(a,b,root));
                    break;
                case 'U':
                    a--;
                    Update(a,b,root);
                    break;
            }

        }
    }
}
