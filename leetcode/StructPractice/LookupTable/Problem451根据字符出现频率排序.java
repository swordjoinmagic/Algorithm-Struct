package leetcode.StructPractice.LookupTable;

import javafx.collections.transformation.SortedList;

import java.util.*;

/**
 * 思路：
 *      1.
 */
public class Problem451根据字符出现频率排序 {

    public static void main(String[] args){
        Problem451根据字符出现频率排序 problem451 = new Problem451根据字符出现频率排序();
        String result = problem451.frequencySort("Aabb");
        System.out.println(result);
    }

    class Letter implements Comparable<Letter> {
        char ch;
        int count;

        @Override
        public int compareTo(Letter o) {
            return Integer.compare(o.count, count);
        }
    }

    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        List<Letter> list = new ArrayList<>(map.size());

        for(Character key : map.keySet()){
            Letter letter = new Letter();
            letter.ch = key;
            letter.count = map.get(key);
            list.add(letter);
        }

        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder();

        for(Letter letter : list){
            for(int i=0;i<letter.count;i++)
                stringBuilder.append(letter.ch);
        }

        return stringBuilder.toString();
    }
}
