package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定两个英文单词(链表形式),
 *      求他们共同后缀的第一个单词在链表中的地址
 * 思路:
 *      1. 用Map<string,node>记录所有节点,其中键是他们的地址.
 *      每个节点都额外增加一个变量parent,表示父节点.
 *      如果在输入的时候,发现一个节点的父节点已经被输入过了,
 *      就说明该节点已经访问过了,那么该节点就是共同后缀的第一个节点
 */
public class PAT1032Sharing_25point {

    public static void main(String[] args){
        PAT1032Sharing_25point pat1032 = new PAT1032Sharing_25point();
        pat1032.Input();
    }

    class Node{
        String address;
        String nextAddress;
        String previousAddress = null;
        String ch;

        public Node(String address, String nextAddress, String ch) {
            this.address = address;
            this.nextAddress = nextAddress;
            this.ch = ch;
        }

        public String toString(){
            return String.format("%s(%s)",address,ch);
        }
    }

    String addressA,addressB;
    int N;

    String commonSuffixAddress;

    Map<String,Node> link = new HashMap<>();
    Set<String> set = new HashSet<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        addressA = in.next();
        addressB = in.next();
        N = in.nextInt();

        for(int i=0;i<N;i++){
            String address = in.next();
            String ch = in.next();
            String next = in.next();
            Node node = new Node(address,next,ch);
            link.put(address,node);
        }
        Slove();
    }

    public void DFS(String initAddress){
        String temp = initAddress;
        while (!temp.equals("-1")){
            Node node = link.get(temp);
            if(!set.contains(node.address))
                set.add(node.address);
            else {
                commonSuffixAddress = node.address;
                return;
            }
            temp = node.nextAddress;
        }
    }

    public void Slove(){
        DFS(addressA);
        DFS(addressB);

        System.out.println(commonSuffixAddress!=null?commonSuffixAddress : -1);
    }
}
