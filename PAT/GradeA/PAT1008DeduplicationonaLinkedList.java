package PAT.GradeA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * 题目描述:
 *      1. 给定一系列链表节点,
 *      删除其中重复节点(即都等于|K|的节点),
 *      删除重复节点后,输出原本链表的一系列节点值和Next值
 *      同时,被删除节点本身也要变成一条链表,根据他们在
 *      原链表中的顺序进行连接
 *
 * 思路:
 *      1. 首先在输入时就找到重复数字
 *      2. 遍历链表,每次遇到重复数字,都去掉当前节点,
 *      将其前面的节点和后面的节点相连,被删除的节点
 *      扔到一个ArrayList中去,最后在遍历一遍ArrayList,
 *      把被删除的节点串起来就好了
 *
 *      时间复杂度O(N)
 *
 * 坑:
 *      1. 删除其中的所有重复节点,即所有重复节点只能出现一次.
 *      而不是只有一个重复节点,被测试数据带坑里去了
 */
public class PAT1008DeduplicationonaLinkedList {

    public static void main(String[] args) throws IOException {
        PAT1008DeduplicationonaLinkedList pat1008 = new PAT1008DeduplicationonaLinkedList();
        pat1008.Input();
    }

    // 头指针的地址
    String initalAdress;
    // 节点数量
    int N;

    // 临时用来找重复数字的
    Set<Integer> set = new HashSet<>();

    class Node{
        // 本节点的地址
        String adress;
        int val;
        // 下一个节点的地址
        String next;

        public Node(String adress, int val, String next) {
            this.adress = adress;
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString(){
            return adress+" "+val+" "+next;
        }
    }

    // 根据这道题的特殊性,可以用Map来存链表
    Map<String,Node> linkList;

    // 临时存放被删除节点
    List<Node> removeList = new ArrayList<>();

    public void Input() throws IOException {
//        Scanner in = new Scanner(System.in);
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        in.nextToken();
        initalAdress = String.format("%05d",(int)in.nval);
        in.nextToken();
        N = (int) in.nval;

        linkList = new HashMap<>();
        // 为了方便起见,将-1定义为Null
        linkList.put("-1",null);
        // 无意义的头指针,方便后续计算
        Node head = new Node("root",0, initalAdress);
        linkList.put("root",head);

        for(int i=0;i<N;i++){
            in.nextToken();
            String address = String.format("%05d",(int)in.nval);
            in.nextToken();
            int val = (int)in.nval;
            in.nextToken();
            String nextAddress = (int)in.nval==-1? "-1" : String.format("%05d",(int)in.nval);
            Node node = new Node(address,val,nextAddress);
            linkList.put(address,node);
        }

        Slove();
    }

    public void Slove(){
        // 从node处向下，每遇到一次重复数字，就进行删除
        Node preNode = linkList.get("root");    // 上一个节点
        Node currentNode = linkList.get(initalAdress); // 当前操作的节点
        while (currentNode!=null){

            // 当前节点是要删除的节点(即第二次以后出现重复节点)
            while (currentNode!=null && set.contains(Math.abs(currentNode.val))){
                // 上一个节点指向当前节点的下一个节点
                preNode.next = currentNode.next;

                // 保险起见,使被删除节点不指向任何节点
                currentNode.next = "-1";

                // 被删除节点加入链表
                removeList.add(currentNode);

                // currentNode节点向下移动
                currentNode = linkList.get(preNode.next);
            }

            // 将当前操作的节点的数加入集合
            if(currentNode!=null)
                set.add(Math.abs(currentNode.val));

            // 两个节点都向下移动
            preNode = linkList.get(preNode.next);
            currentNode = preNode==null ? null : linkList.get(preNode.next);
        }

        // 将被删除的节点串成一条链表
        for(int i=0;i<removeList.size()-1;i++){
            Node RNode = removeList.get(i);
            Node RNextNode = removeList.get(i+1);
            RNode.next = RNextNode.adress;
        }
        removeList.get(removeList.size()-1).next = "-1";


        Node root = linkList.get(initalAdress);
        while (root!=null){
            System.out.println(root);
            root = linkList.getOrDefault(root.next,null);
        }

        for(Node node1 : removeList){
            System.out.println(node1);
        }
    }
}
