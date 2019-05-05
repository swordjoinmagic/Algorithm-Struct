package leetcode.StructPractice.QueueAndStack;

import java.util.*;

/**
 *
 * 要求返回的是给定节点的深拷贝
 *
 * 思路：
 *      1. DFS.
 *      设计一个递归函数,输入一个Node参数,返回该Node的深拷贝
 *
 *      1. 递归的临界条件是当 邻居节点数量为0时, 返回其自身的深拷贝.
 *
 *      2. 当前要拷贝的Node的邻居节点数量大于0时,新建列表,将该Node的
 *      所有深拷贝的邻居节点加入列表,最后再将深拷贝的列表当做
 *      新的Node的邻居节点列表.
 *
 */
public class Problem133克隆图 {

    public static void main(String[] args){
        new Problem133克隆图().Test();
    }

    public void Test(){
        Node graph = new Node();
        graph.val = 1;
        graph.neighbors = new ArrayList<>();

        Node cloneGraph = cloneGraph(graph);
        System.out.print(cloneGraph);
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        return CloneWithNode(node);
    }

    private Map<Integer,Node> map = new HashMap<>();

    public Node CloneWithNode(Node node){

        if(map.containsKey(node.val))
            return map.get(node.val);

        if(node.neighbors==null || node.neighbors.size()==0) {
            Node tempNode = new Node();
            tempNode.val = node.val;
            tempNode.neighbors = new ArrayList<>();
            return tempNode;
        }

        // 当前节点的深拷贝
        Node copyNode = new Node();
        copyNode.val = node.val;
        map.put(node.val,copyNode);

        // 当前节点的所有邻居节点
        List<Node> neighbors = new ArrayList<>();

        for(Node tempNode : node.neighbors){
            Node neighoborNode = CloneWithNode(tempNode);
            neighbors.add(neighoborNode);
        }

        copyNode.neighbors = neighbors;

        return copyNode;
    }
}
