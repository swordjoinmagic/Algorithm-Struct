## 链表问题常用套路之——快慢指针
### 概述
使用多个指针是解决链表问题的常用套路（诸如反转链表需要三个指针前中后等），其中有两个比较特殊的指针分别是slow指针和fast指针，也叫快慢指针。
### 原理
快慢指针顾名思义，即一个移动的比较快的指针和一个移动的比较慢的指针。

实际运用中可以这么写：

    slow = slow.next;
    fast = fast.next.next;

假设快慢指针原来都指向头结点，这样的话，fast指针移动速度就是slow指针的两倍，这是很有用的设计。

### 用途

举两个例子来说明快慢指针的用途.

#### 1.找到链表中点

快慢指针可以很快的找到链表的中点。在不知道链表长度的情况下，找到链表的中点，一个比较慢的方法是下面这样做。

    int length = 0;
    ListNode node = head;
    // 遍历一遍链表得到链表长度
    while(node!=null){
        node = node.next;
        length ++;
    }
    // 根据得到的链表长度,可以遍历长度/2次来找到终点
    ListNode centerNode = head;
    for(int i=0;i<length/2-1;i++){
        centerNode = centerNode.next;
    }

上面的方法遍历了N+N/2次,且代码略显复杂,最后遍历长度/2次时,要注意centerNode节点实际上是中点的下一个节点,所以可以让遍历次数-1来得到中点.

下面是使用了快慢节点的做法:

    // 快慢指针起点相同
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next!=null && fast.next.next!=null){
        slow = slow.next;
        // 快指针移动速度为慢指针两倍
        fast = fast.next.next;
    }
    // 当快指针到达链表表尾时,此时慢指针指向链表中点
    ListNode centerNode = slow;

可以看到,上述代码仅遍历N/2此就找到了链表的中点.

例题:

判断回文链表:https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/45/

问题要求O(N)时间复杂度与O(1)空间复杂度,思路是:

    1. 找到链表中点
    2. 根据中点将链表掰成前后两段
    3. 对链表后段进行翻转
    4. 根据链表前段和经过翻转后的后段来判断回文

找中点的过程即可以使用快慢指针来查找.

#### 2.链表判环
判断一个链表中是否有环,使用快慢指针思路会非常简单.

简单来说,就是,让快慢指针起点相同,快指针移动速度是慢指针两倍,当快指针与慢指针相遇的时候说明此链表有环,否则没环.

例题:

环形链表:https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/46/