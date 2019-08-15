package PAT.GradeAOfficial.test1;

import java.util.Scanner;

public class PAT1011WorldCupBetting_20point {

    public static void main(String[] args){
        PAT1011WorldCupBetting_20point pat1011 = new PAT1011WorldCupBetting_20point();
        pat1011.Input();
    }

    double[][] array;

    public void Input(){
        array = new double[3][3];

        Scanner in = new Scanner(System.in);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                array[i][j] = in.nextDouble();
            }
        }

        double firstOdd = 0;
        int first = 0;
        for(int i=0;i<3;i++){
            if(array[0][i]>firstOdd) {
                firstOdd = array[0][i];
                first = i;
            }
        }

        double secondOdd = 0;
        int second = 0;
        for(int i=0;i<3;i++){
            if(array[1][i]>secondOdd) {
                secondOdd = array[1][i];
                second = i;
            }
        }

        double thirdOdd = 0;
        int third = 0;
        for(int i=0;i<3;i++){
            if(array[2][i]>thirdOdd) {
                thirdOdd = array[2][i];
                third = i;
            }
        }

        String[] result = new String[]{
                "W","T","L"
        };

        System.out.print(result[first]+" "+result[second]+" "+result[third]);

        double sum = 0;
        sum = (firstOdd*secondOdd*thirdOdd*0.65-1)*2;
        System.out.format(" %.2f",sum);

    }
}
