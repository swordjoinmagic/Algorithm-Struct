package PAT.GradeA;


import java.util.*;

/**
 * 题目描述:
 *      1. 给定Np个老鼠以及他们的排列顺序,
 *      最胖的老鼠在一轮中获得胜利,进入下一轮,
 *      输掉的老鼠排名相同,每Ng个老鼠参加一场比赛,
 *      当人数不够Ng的时候,剩余的全部老鼠都参加
 *      求老鼠最终排名
 *
 * 思路:
 *      1. 模拟题.
 *      用一个数据结构Stack<List<Integer>>保存每局输掉的老鼠,
 *      这样计算出最终赢家的时候,pop这个Stack进行排名赋值就好了
 *      用一个List临时保存每局赢家
 */
public class PAT1016MiceandRice {

    public static void main(String[] args){
        PAT1016MiceandRice pat1016 = new PAT1016MiceandRice();
        pat1016.Input();
    }

    // 老鼠数量
    int Np;
    // 每局有Ng个老鼠参加
    int Ng;

    // 每个老鼠的重量
    int[] weight;

    // 每次比赛的顺序
    int[] order;

    Queue<Queue<Mouse>> queues = new LinkedList<>();

    class Mouse{
        int index;
        int weight;

        public Mouse(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        public String toString(){
            return String.format("(%d:%d)",index,weight);
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);

        Np = in.nextInt();
        Ng = in.nextInt();

        weight = new int[Np];
        order = new int[Np];

        queues.add(new LinkedList<>());

        for(int i=0;i<Np;i++){
            weight[i] = in.nextInt();
        }
        for(int i=0;i<Np;i++){
            order[i] = in.nextInt();
            queues.peek().add(
                    new Mouse(order[i],weight[order[i]])
            );
        }

        Stack<List<Mouse>> stack = Slove();

//        System.out.println(stack);

        Print(stack);
    }

    public void Print(Stack<List<Mouse>> stack){
        int[] orders = new int[Np];
        int trueOrder = 1;
        int order = 1;
        while (!stack.isEmpty()){
            List<Mouse> mice = stack.pop();
            for(Mouse mouse : mice){
                trueOrder++;
                orders[mouse.index] = order;
            }
            order = trueOrder;
        }

        for(int i:orders) System.out.print(i+" ");
    }

    public Stack<List<Mouse>> Slove(){

        // 保存每局的输家
        Stack<List<Mouse>> stack = new Stack<>();

        while (true) {
            // 玩这一局的老鼠
            Queue<Mouse> now = queues.poll();
            // 保存这一局所有赢家
            queues.add(new LinkedList<>());
            // 保存这一局的所有输家
            stack.push(new ArrayList<>());
            while (!now.isEmpty()) {
                int max = 0;
                int maxIndex = 0;
                List<Mouse> list = new ArrayList<>();
                for (int i = 0; i < Ng && !now.isEmpty(); i++) {
                    Mouse mouse = now.poll();
                    int data = mouse.weight;
                    if(data>max){
                        maxIndex = mouse.index;
                        max = data;
                    }
                    list.add(mouse);
                }
                // 赢家进入下一局
                queues.peek().add(new Mouse(maxIndex,max));
                for (Mouse data : list) if (data.weight != max) stack.peek().add(data);
            }

            // 如果只有一个人进入下一局,那么这个老鼠就是最终赢家
            if(queues.peek().size()==1) {
//                System.out.println(queues.poll());
//                System.out.println(stack);
                List<Mouse> temp = new ArrayList<>();
                temp.add(queues.poll().poll());
                stack.push(temp);
                return stack;

            }
        }
    }
}
