package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给出二叉搜索树的前序序列,求他的后序序列.
 *      PS: 给出的可能也是一个二叉树搜索树的镜像的前序序列
 * 思路:
 *      1. 根据二叉搜索树的特性,可以知道他的中序就是一个有序序列.
 *      如果是普通二叉搜索树,那么他的中序是升序,如果是镜像二叉搜索树,
 *      那么它的中序是降序序列
 *
 *      有了中序和前序,自然就能求出后序序列了
 */
public class PAT1043IsItaBinarySearchTree_25point {

    public static void main(String[] args){
        PAT1043IsItaBinarySearchTree_25point pat1043 = new PAT1043IsItaBinarySearchTree_25point();
        pat1043.Input();
    }

    class TreeNode{
        TreeNode left,right;
        int value;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    int N;
    int[] preOrder;
    int[] inOrder;

    boolean reverse;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        preOrder = new int[N];
        inOrder = new int[N];

        for(int i=0;i<N;i++){
            preOrder[i] = in.nextInt();
            inOrder[i] = preOrder[i];
        }

        // 二叉搜索树的中序序列是一个有序序列
        Arrays.sort(inOrder);
        if(preOrder[1]>=preOrder[0]){
            reverse = true;
            for(int i=0;i<N/2;i++){
                int a = inOrder[i];
                inOrder[i] = inOrder[N-i-1];
                inOrder[N-i-1] = a;
            }
        }


        Slove();
    }

    public void Slove(){
        TreeNode root = MakeTree(0,N,0,N);
        PostOrder(root);
        if(list.size()==N) {
            System.out.println("YES");
            for (int i = 0; i < list.size(); i++) {
                if(i==0) System.out.print(list.get(i));
                else System.out.print(" "+list.get(i));
            }
        }else {
            System.out.println("NO");
        }
    }

    // preEnd不包括
    public TreeNode MakeTree(int preStart,int preEnd,int inStart,int inEnd){
        // 前序序列的第一个节点是当前二叉树的根节点
        int rootValue = preOrder[preStart];
        TreeNode node = new TreeNode(rootValue);

        // 在中序序列中找到该根节点
        int index = -1;
        for(int i=inStart;i<inEnd;i++){
            if(inOrder[i]==rootValue){
                if(reverse && i+1<inEnd && inOrder[i+1]==inOrder[i]) continue;
                index = i;
                break;
            }
        }

        if(index==-1) return null;

        // 左子树前序序列的长度
        int leftTreeLen = index - inStart;
        if(leftTreeLen <=0 ) return node;
        // 左子树的中序序列
        int leftTreeInStart = inStart; int leftTreeInEnd = index;
        // 左子树的前序序列
        int leftTreePreStart = preStart+1; int leftTreePreEnd = leftTreePreStart + leftTreeLen;

        // 右子树前序序列的长度
        int rightTreeLen = inEnd - index;
        if(rightTreeLen<=0) return node;
        // 右子树中序序列
        int rightTreeInStart = index+1; int rightTreeInEnd = inEnd;
        // 右子树前序序列
        int rightTreePreStart = leftTreePreEnd; int rightTreePreEnd = preEnd;

        if(leftTreePreEnd - leftTreePreStart > 0 && leftTreeInEnd - leftTreeInStart > 0)
            node.left = MakeTree(leftTreePreStart,leftTreePreEnd,leftTreeInStart,leftTreeInEnd);
        if(rightTreePreEnd - rightTreePreStart>0 && rightTreeInEnd - rightTreeInStart > 0)
            node.right = MakeTree(rightTreePreStart,rightTreePreEnd,rightTreeInStart,rightTreeInEnd);

        return node;
    }

    List<Integer> list = new ArrayList<>();
    public void PostOrder(TreeNode node){
        if(node!=null){
            PostOrder(node.left);
            PostOrder(node.right);
//            System.out.print(node.value+" ");
            list.add(node.value);
        }
    }
}
