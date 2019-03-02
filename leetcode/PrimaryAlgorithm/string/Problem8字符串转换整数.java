package leetcode.PrimaryAlgorithm.string;

//=============================================
// 本答案错误
//public class Problem8字符串转换整数 {
//
//    public static void main(String[] args){
//        Problem8字符串转换整数 problem8 = new Problem8字符串转换整数();
//        String s = "+-2";
//        System.out.println(problem8.myAtoi(s));
//    }
//
//    public int myAtoi(String str) {
//        StringBuffer stringBuffer = new StringBuffer();
//
//        for(int i=0;i<str.length();i++){
//            if(IsNumOrSign(str.charAt(i))){
//                if(stringBuffer.length()>=1 && (str.charAt(i)=='+'||str.charAt(i)=='-')) return 0;
//                stringBuffer.append(str.charAt(i));
//            }else if(stringBuffer.length()==0 && str.charAt(i)!=' '){
//                // 第一个非空字符不是数字或符号
//                return 0;
//            }
//        }
//
//        // 无任何有效字符的情况下
//        if(stringBuffer.length()==0)
//            return 0;
//
//        if(stringBuffer.length()==1 && (stringBuffer.charAt(0)=='+' || stringBuffer.charAt(0)=='-'))
//            return 0;
//
//        // 判断正负数
//        boolean IsNegative = false;
//
//        if(stringBuffer.charAt(0)=='-')
//            IsNegative = true;
//
//        if(stringBuffer.charAt(0)=='-' || stringBuffer.charAt(0)=='+'){
//            while (stringBuffer.charAt(1)=='0' && stringBuffer.length()>2) stringBuffer.deleteCharAt(1);
//        }
//
//        String s ;
//
//        if(IsNegative || stringBuffer.charAt(0)=='+') s = stringBuffer.substring(1);
//        else s = stringBuffer.toString();
//
//        boolean first = false;
//
//        if(stringBuffer.charAt(0)!='-' && stringBuffer.charAt(0)!='+'){
//            // 第一个是数字，则直接将连续的数字组合起来
//            StringBuffer sb = new StringBuffer();
//            for(int i=0;i<str.length();i++){
//                if(str.charAt(i)>='0' && str.charAt(i)<='9') {
//                    sb.append(str.charAt(i));
//                    first = true;
//                }else if(first) break;
//            }
//            while (sb.charAt(0)=='0' && sb.length()>1) sb.deleteCharAt(0);
//            s = sb.toString();
//            stringBuffer = new StringBuffer(s);
//        }
//
//        if(!IsNegative) {
//            String maxValue = String.valueOf(Integer.MAX_VALUE);
//            int result = Compare(s.toCharArray(), maxValue.toCharArray());
//            if (result == 1)
//                return Integer.MAX_VALUE;
//        }else {
//            String minValue = "2147483648";
//            int result = Compare(s.toCharArray(), minValue.toCharArray());
//            if (result == 1)
//                return Integer.MIN_VALUE;
//        }
//        return Integer.parseInt(stringBuffer.toString());
//    }
//
//    private int Compare(char[] s1,char[] s2){
//        if(s1.length<s2.length) return -1;
//        if(s1.length>s2.length) return 1;
//        for(int i=0;i<s1.length;i++){
//            if(s1[i]>s2[i])
//                return 1;
//            else if(s1[i]<s2[i])
//                return -1;
//        }
//        return 0;
//    }
//
//    private boolean IsNumOrSign(char c){
//        if((c>='0'&&c<='9') || c=='+' || c=='-')
//            return true;
//        else
//            return false;
//    }
//}
