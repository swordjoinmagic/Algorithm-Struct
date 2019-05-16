package PAT.GradeA;

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
 */
public class PAT1008DeduplicationonaLinkedList {

    public static void main(String[] args){
        PAT1008DeduplicationonaLinkedList pat1008 = new PAT1008DeduplicationonaLinkedList();
        pat1008.Input();
    }

    // 头指针的地址
    String initalAdress;
    // 节点数量
    int N;

    // 重复数字
    int deduplicationKey;

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

    public void Input(){
        Scanner in = new Scanner(System.in);
        initalAdress = in.next();
        N = in.nextInt();

        linkList = new HashMap<>();
        // 为了方便起见,将-1定义为Null
        linkList.put("-1",null);

        for(int i=0;i<N;i++){
            String address = in.next();
            int val = in.nextInt();
            String nextAddress = in.next();
            Node node = new Node(address,val,nextAddress);
            linkList.put(address,node);

            if(set.contains(Math.abs(val))){
                deduplicationKey = Math.abs(val);
            }else {
                set.add(Math.abs(val));
            }
        }

        Slove();
    }

    public void Slove(){
        // 遍历链表,将重复数字删掉(保留第一个遇到的)
        Node node = linkList.get(initalAdress);

        // 先来到第一个遇到的重复数字处，从该处向下，
        // 每遇到一个重复数字节点就删除
        while (Math.abs(node.val)!=deduplicationKey)
            node = linkList.get(node.next);

        // 从node处向下，每遇到一次重复数字，就进行删除
        Node preNode = node;    // 上一个节点
        Node currentNode = linkList.get(node.next); // 当前操作的节点
        while (currentNode!=null){
            // 当前节点是要删除的节点
            while (currentNode!=null && Math.abs(currentNode.val)==deduplicationKey){
                // 上一个节点指向当前节点的下一个节点
                preNode.next = currentNode.next;

                // 保险起见,使被删除节点不指向任何节点
                currentNode.next = "-1";

                // 被删除节点加入链表
                removeList.add(currentNode);

                // currentNode节点向下移动
                currentNode = linkList.get(preNode.next);
            }
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
