package leetcode.PrimaryAlgorithm.array;

public class Problem36有效的数独 {

    public static void main(String[] args){
        Problem36有效的数独 problem36 = new Problem36有效的数独();

        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','3','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(problem36.CheckPalace(board));
    }

    // 检查行和列
    public boolean CheckRowAndCol(char[][] board){
        for(int i=0;i<9;i++){
            // 用于检查行
            boolean[] rowVisited = new boolean[10];
            // 用于检查列
            boolean[] colVisited = new boolean[10];

            for(int j=0;j<9;j++){

                // 检查行
                //==============================
                int index = board[i][j]-'0';
                if(index>0) {
                    if (rowVisited[index])
                        return false;
                    else
                        rowVisited[index] = true;
                }
                //===============================

                // 检查列
                int colIndex = board[j][i] - '0';
                if(colIndex>0){
                    if(colVisited[colIndex])
                        return false;
                    else
                        colVisited[colIndex] = true;
                }
            }
        }
        return true;
    }

    // 检查九宫
    private boolean CheckPalace(char[][] board){
        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){

                boolean[] visisted = new boolean[10];

                for(int a=i;a<i+3;a++){
                    for(int b=j;b<j+3;b++){

                        int number = board[a][b] - '0';
                        if(number>0){
                            if(visisted[number])
                                return false;
                            else
                                visisted[number] = true;
                        }

                    }
                }

            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        return CheckRowAndCol(board) && CheckPalace(board);
    }
}
