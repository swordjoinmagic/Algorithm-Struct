package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给链表排序,最后需要输出整个链表,包括地址/next指针等等
 * 思路:
 *      1. 用map记录每个key的地址和next指针.
 *      额外开辟一个数组,对所有key进行排序.
 *
 *      然后就是重新组装链表.
 */
public class PAT1052LinkedListSorting_25point {

    public static void main(String[] args){
        PAT1052LinkedListSorting_25point pat1052 = new PAT1052LinkedListSorting_25point();
        pat1052.Input();
    }

    class Node implements Comparable<Node>{
        String address;
        int value;
        String next;

        public Node(String address, int value, String next) {
            this.address = address;
            this.value = value;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value,o.value);
        }
    }

    int N;
    String head;

    Node[] sortedNodes;

    Map<String,Node> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        head = in.next();

        sortedNodes = new Node[N];

        for(int i=0;i<N;i++){
            String address = in.next();
            int value = in.nextInt();
            String nextAddress = in.next();
            Node node = new Node(address,value,nextAddress);
            map.put(address,node);
        }

        int i = 0;
        String temp = head;
        while (!temp.equals("-1")){
            sortedNodes[i++] = map.get(temp);
            temp = map.get(temp).next;
        }

        N = i;
        Slove();
    }

    public void Slove(){
        if(N>0) {
            Arrays.sort(sortedNodes,0,N);
            head = sortedNodes[0].address;
            System.out.println(N + " " + head);
        }else {
            System.out.println("0 -1");
            return;
        }

        for(int i=0;i<N;i++){
            Node node = sortedNodes[i];
            if(i!=N-1) {
                Node nextNode = sortedNodes[i + 1];
                node.next = nextNode.address;
            }else {
                node.next = "-1";
            }

            System.out.println(node.address+" "+node.value+" "+node.next);
        }
    }
}
