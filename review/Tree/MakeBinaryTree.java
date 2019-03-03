package review.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据前序和中序或后序和中序或层次和中序来建立二叉树
 */
public class MakeBinaryTree {

    public static void main(String[] args){
        MakeBinaryTree makeBinaryTree = new MakeBinaryTree();

        List<Character> font = new ArrayList<>(Arrays.asList('A','B','D','G','C','E','F','H'));
        List<Character> middle = new ArrayList<>(Arrays.asList('D','G','B','A','E','C','H','F'));

        BinaryNode node =  makeBinaryTree.MakeTree(font,middle);
        makeBinaryTree.BackPrint(node);

    }

    // 打印一个二叉树的后序遍历
    public void BackPrint(BinaryNode node){
        if(node!=null){
            // 左
            BackPrint(node.left);
            // 右
            BackPrint(node.right);
            // 根
            System.out.print(node.data+" ");
        }
    }

    /**
     * 根据前序和中序建树
     * @param font
     * @param midlle
     * @param <T>
     * @return
     */
    public <T> BinaryNode<T> MakeTree(List<T> font, List<T> middle){

        System.out.println("=======================");
        System.out.println("Font:"+font+"\n middle:"+middle);

        if(font==null || font.size()<=0) return null;

        // 得到根
        BinaryNode<T> root = new BinaryNode<T>(font.get(0));

        if(font.size()<=1) return root;

        // 找到根在中序遍历中的位置
        int pos;  // pos即根在中序遍历中的位置
        for(pos=0;pos<middle.size();pos++){
            if(middle.get(pos).equals(root.data)) {
                break;
            }
        }

        System.out.println("根节点在中序遍历中的位置："+pos);

        // 在中序遍历左边的是左子树，右边的是右子树

        // 得到当前节点左子树的前序遍历和中序遍历数组

        List<T> leftTreeFont = font.subList(1,pos+1);
        List<T> leftTreeMiddle = middle.subList(0,pos);

        // 得到当前节点右子树的前序遍历和中序遍历数组
        List<T> rightTreeFont = font.subList(pos + 1, font.size());
        List<T> rightTreeMiddle = middle.subList(pos + 1, font.size());

        System.out.println("左：");
        // 当前节点的左子树为利用上面两个前序中序遍历生成的二叉树
        root.left = MakeTree(leftTreeFont,leftTreeMiddle);

        System.out.println("右：");
        // 当前节点的右子树为利用上面两个前序中序遍历生成的二叉树
        root.right = MakeTree(rightTreeFont,rightTreeMiddle);

        return root;
    }

}
