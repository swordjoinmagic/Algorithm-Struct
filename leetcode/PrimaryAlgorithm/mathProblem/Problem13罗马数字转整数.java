package leetcode.PrimaryAlgorithm.mathProblem;

/**
 * 思路:
 *      遍历一遍罗马数字,
 *      每次判断下一个字符的罗马数字值是否大于当前字符表示罗马数字值,
 *      如果大于,那么总的值减去这个罗马数字,否则加上
 */
public class Problem13罗马数字转整数 {

    public static void main(String[] args){
        Problem13罗马数字转整数 problem13 = new Problem13罗马数字转整数();
        int result = problem13.romanToInt("MCMXCIV");
        System.out.println(result);
    }

    // 键是英文字母的ascii码-'A'的ascii码,值是该罗马数字表示四的值
    int[] alphet;

    private void Init(){
        alphet = new int[27];
        alphet['I'-'A'] = 1;
        alphet['V'-'A'] = 5;
        alphet['X'-'A'] = 10;
        alphet['L'-'A'] = 50;
        alphet['C'-'A'] = 100;
        alphet['D'-'A'] = 500;
        alphet['M'-'A'] = 1000;

    }

    public int romanToInt(String s) {
        Init();

        int total = 0;;
        for(int i=0;i<s.length()-1;i++){
            int c = s.charAt(i) - 'A';
            int cNext = s.charAt(i+1) - 'A';

            if(alphet[cNext]>alphet[c]){
                total -= alphet[c];
            }else {
                total += alphet[c];
            }
        }
        total += alphet[s.charAt(s.length()-1)-'A'];

        return total;
    }
}
