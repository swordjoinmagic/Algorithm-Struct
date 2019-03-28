package leetcode.IntermediateAlgorithm.SortAndFind;

import java.util.*;

/**
 * 思路:
 *      1. 对左端点进行排序,然后遍历一遍
 */
public class Problem56合并区间 {

    public static void main(String[] args){
        Problem56合并区间 problem56合并区间 = new Problem56合并区间();
        problem56合并区间.Slove();
    }

     class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
         @Override
         public String toString(){
             return String.format("[%d,%d]",start,end);
         }
     }

    public void Slove(){
         List<Interval>list = new ArrayList<>();
         list.add(new Interval(2,3));
         list.add(new Interval(2,2));
         list.add(new Interval(3,3));
         list.add(new Interval(1,3));
         list.add(new Interval(5,7));
         list.add(new Interval(2,2));
        list.add(new Interval(4,6));
         System.out.println(merge(list));
    }

    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size()<=1) return intervals;

        // 先排序
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start>o2.start){
                    return 1;
                }else if(o1.start<o2.start){
                    return -1;
                }else{
                    if(o1.end>o2.end)
                        return 1;
                    else if(o1.end<o2.end)
                        return -1;
                    else
                        return 0;
                }
            }
        });

        System.out.println(intervals);

        // 从下标1开始遍历
        for(int i=1;i<intervals.size();){

            // 如果左边可以与他合并,那么就合并,并更改当前值
            Interval left = intervals.get(i-1);
            Interval now = intervals.get(i);
            if(now.start<=left.end){
                // 合并
                now.start = left.start;
                now.end = left.end>now.end ? left.end : now.end;

                intervals.remove(i-1);

                System.out.println("===========");
                System.out.println(intervals);

            }else i++;

        }

        return intervals;
    }
}
