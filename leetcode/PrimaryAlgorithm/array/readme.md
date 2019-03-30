# leetcode初级算法刷题总结
在LeetCode差不多刷了一周多，最近刚把初级算法栏目的题大致刷完了，想着应该总结一下，毕竟只刷题不总结也是没办法提高的（大一的时候血泪的教训。。）。

下面对初级算法栏目总共49道题进行简单的总结，因为是初级算法的专栏，大部分的标签都是easy，对于思路简单，没什么弯弯绕绕的会在总结的时候简单提一下活络一下思维。对于写的时候，我没有思路的，或者可以更加深入的，会另开博文专研一下~

## 数组

### 从排序数组中删除重复项 -> 题号 [26](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

题目要求仅适用O(1)的额外空1间，所以不能另开数组。遍历一遍放不重复元素进另一个数组的思路就失败了。

思路：

1. 一开始的思路是使用类似冒泡排序的方法删除数组中的重复元素，即将重复元素**冒泡**到**数组的最后**。  
此方法时间复杂度O(n2)，所用时间是遍历一次的数组的时间*将重复元素冒泡到最后的时间。

2. 思路1没有注意到题目条件--给定**数组已排序**。  
对于已排序数组，可以使用**双指针解法**。  
双指针解法关键在于放置两个指针i、j，i是慢指针，j是快指针，代码框架如下。

        int i=0;
        int j=1;
        while(j<nums.length){
            if(nums[j]!=nums[i])
                nums[++i] = nums[j];
            j++;
        }

    简要的说，就是当nums[i]==nums[j]时，跳过重复项。
    此解法只适用于排序数组，对于未排序数组，其重复元素可能是非连续的。举例，对于数组 7 7 8 7 1 3 2，此解法会残留重复元素。

        7 7 8 7 1 3 2
        i j
        
        7 8 8 7 1 3 2
          i   j

        7 8 7 7 1 3 2
            i   j          

        7 8 7 1 1 3 2
              i   j        

        7 8 7 1 3 3 2
                i   j          

        7 8 7 1 3 2 2
                  i   j
    
    双指针解法时间复杂度O(n)，只需让i、j遍历一遍即可

### 买卖股票的最佳时机 II -> 题号 [122](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

思路：

1. DP，设f[i]为前i天能获得的最大利润

        f[i] = max(f[i-1],f[i-1]+(A[i]-A[i-1]));

    表示含义是：前i天的最大利润为Max(前i-1天的最大利润,前i-1天的最大利润+在第i-1天买入,第i天卖出的利润)  
    DP时间复杂度O(N)，只需遍历一遍即可。  
    这个方法在LeetCode上仅击败13%的Java -_-

### 旋转数组 -> 题号 [189](https://leetcode-cn.com/problems/rotate-array/)

题目要求使用空间复杂度为 O(1) 的原地算法。

思路：

1. 模拟，数组右移一位时，记录数组最后一个元素的值，右移完成后第0个元素赋值为之前记录的最后一个元素。  
这道题可以根据**每移动len（数组长度）次，数组回归原样**进行剪枝，即k%=len;  
时间复杂度为O(N),即为数组长度

### 存在重复 -> 题号 [217](https://leetcode-cn.com/problems/contains-duplicate/)

思路：
1. 暴力。HashMap或HashSeet均可以解决，时间复杂度O(N)，HashMap查找时间O(1)

### 只出现一次的数字 -> 题号 [136](https://leetcode-cn.com/problems/single-number/)

算法要求具有线性时间复杂度，O(1)的空间复杂度。

思路：
1. 解法极其机智！！！顺着异或一遍，最后得到的结果就是答案。这是因为 a xor a = 0; 0 xor x = x;而该数组所有元素均出现两次（除了要求的数）。

### 两个数组的交集 II -> 题号 [350](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/)

思路：
    1. 暴力。用HashMap或HashSet，遍历一遍数组1，然后遍历一遍数组2，判断数组2中的元素是否在HashMap或HashSet中出现过，如果出现过，那么就是交集。时间复杂度O（N）
    2. 更暴力的解法。将数组1、2都变成集合set，然后直接用set的求并集方法。

### 加一 -> 题号 [66](https://leetcode-cn.com/problems/plus-one/)

有点类似于大数加法，不过他这个只加1，很好解决。

思路：
    1. 模拟加法流程。对数组尾部元素加1，如果加一后该数大于等于10，那么数组前一个数+1，前一个数也类似，以此类推。

### 移动零 -> 题号 [283](https://leetcode-cn.com/problems/move-zeroes/)

题目要求：
    1. 必须在原数组上操作，不能拷贝额外的数组。
    2. 尽量减少操作次数。

思路：
    1. 类似计数排序的思路。设定一个数index=0，遍历一遍数组，遇到不为0的数num，就让array[index++]=num。当第一遍遍历结束后，顺着刚才的index往后遍历，将每位上的数都变成0。（相当于记住了这个数组一共有多少个0）。时间复杂度O(N)

### 两数之和 -> 题号 [1](https://leetcode-cn.com/problems/two-sum/)

思路：
    1. 暴力。两重循环得到两个整数的所有组合。时间复杂度O(n2)
    2. 用Map或Set改进暴力算法，上面的暴力法实际上不需要第二重循环，一重循环获得第一个整数的值后，将目标值减去得到的整数值，就能得到第二个整数值。这时，只需在Map或Set里查找数组中是否含有这个值就行了（查找的时间复杂度O(1)）。时间复杂度O(N)
    3. 双指针解法。先对数组进行排序（稳定排序时间复杂度O(NLogN)），头指针i指向数组头部，尾指针j指向数组尾部。当array[i]+array[j] > target时，j-1；当array[i]+array[j] < target时，i+1，当相等时，array[i]、array[j]就是目标值。

### 判断数独有效 -> 题号 [36](https://leetcode-cn.com/problems/valid-sudoku/)

题目只需判断数独是否有效，而不是判断数独是否有解，所以还是比较简单的。

思路：
    1. 模拟。根据数独的规则（即数字1-9只能在每一行、每一列、每一个九宫格出现一次）遍历数独即可。

### 旋转图像 -> 题号 [48](https://leetcode-cn.com/problems/rotate-image/)

将一个n*n的二维矩阵顺时针旋转90度。

题目要求：  
你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

思路：
    1. 我寻思着这不就是将矩阵转置么，难道我苦练多年的线代要派上用场了（窃喜）。然后，看了一眼题目要求。。不能用另外的矩阵，行8，不用就不用。实际上如果用矩阵变换，最少也要O(n2)的时间复杂度把，毕竟一次矩阵乘法要同时遍历两个矩阵。
    2. 实在是想不出来了。参考LeetCode评论区大神的思路，

### 数组篇总结
初级算法栏目确实不算太难，总的来说还是偏简单的，在数组篇主要是还是学到了之前没怎么听说过但是挺好用的双指针解法。

双指针解法主要针对已排序的数组，以类似二分法的思想将求解范围一步步缩小直到找到答案（LeetCode相关题号1、26）。

另外还有一个简单的套路，当查找成为一个算法的瓶颈时（如题号1、217、350），可以使用Map或Set来使算法中查找的复杂度降为O(1)。

对于数组的最后一题（题号48），感觉没什么思路啊，感觉应该是数学功底差的问题。