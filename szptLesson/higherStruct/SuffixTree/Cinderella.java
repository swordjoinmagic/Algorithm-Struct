package szptLesson.higherStruct.SuffixTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 思路：
 *      1. 建立后缀树。
 *      对于样例
 *      3
 *      ella
 *      Arcella
 *      la
 *
 *      建立后缀树如下：
 *
 *                  a
 *               /
 *             /
 *            l*
 *         /
 *        l
 *       /
 *      e(*)
 *       /
 *      c
 *      /
 *     r
 *    /
 *   A(*)
 *
 *   当要查找某个单词是另外一个单词后缀的次数时,
 *   首先,在后缀树中找到该单词(DFS/循环亦可)的结束位置(即*),
 *   然后,根据这个单词的结束节点,向下DFS,每当遇到一个*号时,
 *   是另一个单词的后缀次数+1
 *
 *   找后缀的时间复杂度为O(m),m是节点数
 *
 */
public class Cinderella {

    public static void main(String[] args) throws FileNotFoundException {
        Cinderella cinderella = new Cinderella();
        cinderella.Input();
    }

    // 后缀树节点
    class Node{
        char ch;
        boolean isOver;
        // 孩子节点
        Map<Character, Node> childs;
        public Node(char ch){
            this.ch = ch;
            childs = new HashMap<>();
        }
    }

    // 单词数量
    int n ;
    // 用于存储单词的数组
    String[] words;

    public void Input() throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\Administrator\\Downloads\\testdata\\C\\7in.txt"));
        Scanner in = new Scanner(System.in);
        while (true){
            n = in.nextInt();
            if(n==0) break;
            words = new String[n];
            for(int i=0;i<n;i++){
                words[i] = in.next();
            }

            Slove();
        }
    }

    public void Slove(){
        // 总后缀次数
        int totalCount = 0;

        Node root = BuildTree();

        for(int i=0;i<n;i++){
            String word = words[i];

            // 第一步,在后缀树中找到该单词(结尾)所在节点
            Node wordNode = FindWord(root,word);

            // 第二步,从该节点出发,向下面进行DFS,每遇到一个
            // 结束符号,后缀次数+1
            totalCount += CountSuffixWord(wordNode);
        }

        System.out.println(totalCount);
    }


    public Node FindWord(Node root,String word){
        Node wordNode = root;
        for(int i=word.length()-1;i>=0;i--){
            char ch = word.charAt(i);
            // 向下移动
            wordNode = wordNode.childs.get(ch);
        }
        return wordNode;
    }

    /**
     * 根据该单词的节点,
     * 计算该单词是其他单词后缀的次数
     * @param wordNode
     * @return
     */
    public int CountSuffixWord(Node wordNode){
        int result = 0;
        for(Node node : wordNode.childs.values()){
            if(node.isOver)
                result ++;
            result += CountSuffixWord(node);
        }
        return result;
    }

    // 建立后缀树
    public Node BuildTree(){
        // 创建一个不存储值的根节点
        Node root = new Node('\0');
        for(int i=0;i<n;i++){
            String word = words[i];
            Node wordNode = root;

            for(int j=word.length()-1;j>=0;j--){
                char ch = word.charAt(j);
                if(!wordNode.childs.containsKey(ch)){
                    wordNode.childs.put(ch,new Node(ch));
                }
                // 向下移动
                wordNode = wordNode.childs.get(ch);
                // 设置结束符
                if(j==0) wordNode.isOver = true;
            }
        }
        return root;
    }
}
