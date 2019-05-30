package PAT.GradeA;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个字符串,找出他们最长的共同后缀
 *
 * 思路:
 *      1. 对所有字符串建立字典树,建完树后,从根节点出发,
 *      到第一个岔路结尾,这一段路程就是最长的共同后缀
 */
public class PAT1012Kuchiguse {

    public static void main(String[] args){
        PAT1012Kuchiguse pat1012 = new PAT1012Kuchiguse();
        pat1012.Input();
    }

    class Node{
        char ch;
        boolean isFinish;
        public Map<Character,Node> child;
        public Node(char ch, boolean isFinish) {
            this.ch = ch;
            this.isFinish = isFinish;
            child = new HashMap<>();
        }
    }

    int N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        // 无意义的根节点
        Node root = new Node(' ',false);

        for(int i=0;i<N;i++){
            String str = in.nextLine();
            MakeTree(root,str);
        }

        String result = GetLongestSuffix(root);
        if(result.length()>0)
            System.out.println(result);
        else
            System.out.println("nai");
    }

    public String GetLongestSuffix(Node root){
        StringBuilder str = new StringBuilder();
        Node node = root;
        while (true){
            if(node.child.size()>1 || node.isFinish) break;
            for(char ch : node.child.keySet()){
                str.insert(0, ch);
                node = node.child.get(ch);
            }
        }
        return str.toString();
    }

    /**
     * 根据一个字符串,建立字典树
     * @param root
     * @param str
     */
    public void MakeTree(Node root,String str){

        Node node = root;

        for(int i=str.length()-1;i>=0;i--){
            char ch = str.charAt(i);

            if (node.child.containsKey(ch)){
                node = node.child.get(ch);
            }else {
                Node n = new Node(ch,false);
                node.child.put(ch,n);
                node = n;
            }

            if(i==0)
                node.isFinish = true;
        }
    }
}
