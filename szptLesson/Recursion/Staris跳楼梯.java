package szptLesson.Recursion;

public class Staris跳楼梯 {

    public static void main(String[] args){
        Staris跳楼梯 staris = new Staris跳楼梯();
        System.out.println(staris.slove(20));
    }

    public int slove(int n){
        int[] f = new int[n+1];
        f[0] = 1;
        for(int i=1;i<=n;i++){
            if(i>=1) f[i] += f[i-1];
            if(i>=3) f[i] += f[i-3];
            if(i>=4) f[i] += f[i-4];
        }

        return f[n];
    }
}
