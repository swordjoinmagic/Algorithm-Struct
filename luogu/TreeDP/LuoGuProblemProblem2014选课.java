package luogu.TreeDP;

/**
 * 思路：
 *     1. 树形DP。
 *
 *     设 f[i][j] 为 以i为根的树，选修j门课时能获得的最大学分。
 *
 *     则动态转移方程如下：
 *
 *          f[i][j] = max{ f[i.child1][]  }
 *
 *     在子树1上选修k门课程，在子树2上选修
 */
public class LuoGuProblemProblem2014选课 {

}
