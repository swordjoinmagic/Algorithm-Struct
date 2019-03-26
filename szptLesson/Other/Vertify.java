package szptLesson.Other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Vertify {
    public static void main(String[] args){

        BufferedReader readerIn;
        BufferedReader readerOut;

        Solution solution = new Solution();

        try {
            for (int i = 1; i <= 200; i++) {
                readerIn = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\TestData\\" + i + ".in")));
                readerOut = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\TestData\\" + i + ".out")));

                int n = Integer.parseInt(readerIn.readLine());
                int[] array = new int[n];
                String[] strs = readerIn.readLine().split(" ");
                for(int j=0;j<strs.length;j++){
                    array[j] = Integer.parseInt(strs[j]);
                }
                int result = solution.maxProduct(array);

                int outResult = Integer.parseInt(readerOut.readLine());

                System.out.println((result==outResult)+"  result:"+result+" outResult:"+outResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
