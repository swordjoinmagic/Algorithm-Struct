package leetcode.PrimaryAlgorithm.string;


public class Problem7整数反转 {

    public static void main(String[] args){
        Problem7整数反转 problem7 = new Problem7整数反转();
        System.out.print(problem7.reverse(-123121243));
    }

    public int reverse(int x) {
        boolean isNegative = false;
        char[] str = String.valueOf(x).toCharArray();
        if(str[0]=='-'){
            isNegative = true;
            str = String.valueOf(x).substring(1).toCharArray();
            reverseString(str);
        }else {
            reverseString(str);
        }

        // 范围在[1<<31,1<<31-1]之间,
        // 即[-2147483648,2147483647]
        char[] min = {'2','1','4','7','4','8','3','6','4','8'};
        char[] max = {'2','1','4','7','4','8','3','6','4','7'};

        if(isNegative){
            int result = Compare(str, min);
            if(result==1)
                return 0;
            else
                return -Integer.parseInt(String.valueOf(str));
        }else {
            int result = Compare(str, max);
            if(result==1)
                return 0;
            else
                return Integer.parseInt(String.valueOf(str));
        }
    }


    private int Compare(char[] s1,char[] s2){
        if(s1.length<s2.length) return -1;
        for(int i=0;i<s1.length;i++){
            if(s1[i]>s2[i])
                return 1;
            else if(s1[i]<s2[i])
                return -1;
        }
        return 0;
    }

    public void reverseString(char[] s) {
        for(int i=0;i<s.length/2;i++){
            swap(s,i,s.length-1-i);
        }
    }

    private void swap(char[] s,int i,int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
