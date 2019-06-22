package szptLesson.higherStruct.LineTree;

import java.io.*;

public class ProblemD炒股王面试_猫树优化 {
    public String ID = "16240011";
    public String Problem = "D";

    // 优化输入
    class Scanner{

        StreamTokenizer in;

        public Scanner(InputStream inputStream){
            in = new StreamTokenizer(new BufferedReader(new InputStreamReader(inputStream)));
        }

        public int nextInt() throws IOException {
            in.nextToken();
            return (int) in.nval;
        }
    }

    public static void main(String[] args) throws IOException {
        ProblemD炒股王面试_猫树优化 problemD = new ProblemD炒股王面试_猫树优化();
        problemD.Input();
    }

    // 天数
    int N;
    // 查询次数
    int M;

    int[] array;

    public void Input() throws IOException {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        array = new int[N];

        for(int i=0;i<N;i++){
            array[i] = in.nextInt();
        }

        Slove(in);
    }

    public void Slove(Scanner in){

    }
}
