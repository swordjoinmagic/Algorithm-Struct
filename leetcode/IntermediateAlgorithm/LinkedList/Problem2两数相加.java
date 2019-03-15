package leetcode.IntermediateAlgorithm.LinkedList;

public class Problem2两数相加 {

    public static void main(String[] args){
        Problem2两数相加 problem2两数相加 = new Problem2两数相加();
        ListNode root1 = new ListNode(9);
        root1.next = new ListNode(9);
        root1.next.next = new ListNode(9);

        ListNode root2 = new ListNode(9);
        root2.next = new ListNode(9);
        root2.next.next = new ListNode(9);

        problem2两数相加.addTwoNumbers(root1,root2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tempL1Node = l1;
        ListNode tempL2Node = l2;

        // 两数之和
        int result = 0;

        // 位数
        int index = 0;

        while (tempL1Node!=null && tempL2Node!=null){
            int temp = (tempL1Node.val + tempL2Node.val) * pow(10,index);
            if((tempL1Node.val + tempL2Node.val)>=10) {
                System.out.println("进位:"+pow(10,index+1));
                result += pow(10,index+1);
                // 进位
                temp = (tempL1Node.val + tempL2Node.val)%10;
            }
            result += temp;
            index++;

            System.out.println("temp:"+temp+"\n result:"+result);


            tempL1Node = tempL1Node.next;
            tempL2Node = tempL2Node.next;
        }

        System.out.println("result:"+result+"\nindex:"+index);

        return null;
    }

    int pow(int n,int p){
        int result = 1;
        for(int i=0;i<p;i++){
            result *= n;
        }
        return result;
    }
}
