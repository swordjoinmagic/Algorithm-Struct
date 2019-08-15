package PAT.GradeAOfficial.test1;


import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一系列登入登出记录,
 *      第一个登录的人会开门,最后一个登出的人会锁门,
 *      求开门和锁门的人
 *
 * 思路:
 *      1. 模拟.
 *      对于这种有时间标准格式的题,一般都是要将标准格式的时间转换成秒.
 *      然后比较即可.
 */
public class PAT1006SignInandSignOut_25point {

    public static void main(String[] args){
        PAT1006SignInandSignOut_25point pat = new PAT1006SignInandSignOut_25point();
        pat.Input();
    }

    int M;

    // 最早登入时间
    int earilestSignInTime;
    // 第一个开门的人
    String firstSignInPerson;

    // 最晚登出时间
    int latestSignOutTime;
    // 最后一个锁门的人
    String lastSignOutPerson;

    public int getTime(String time){
        String[] times = time.split(":");
        int h = Integer.parseInt(times[0]);
        int m = Integer.parseInt(times[1]);
        int s  = Integer.parseInt(times[2]);
        return getTime(h,m,s);
    }

    public int getTime(int h,int m,int s){
        return h*60*60+m*60+s;
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();

        // 初始化
        earilestSignInTime = Integer.MAX_VALUE;
        latestSignOutTime = 0;

        for(int i=0;i<M;i++){
            String name = in.next();
            String signInTime = in.next();
            String signOutTime = in.next();

            int signInTimeWithSeconds = getTime(signInTime);
            int signOutTimeWithSeconds = getTime(signOutTime);

            if(signInTimeWithSeconds<earilestSignInTime){
                earilestSignInTime = signInTimeWithSeconds;
                firstSignInPerson = name;
            }

            if(signOutTimeWithSeconds > latestSignOutTime){
                latestSignOutTime = signOutTimeWithSeconds;
                lastSignOutPerson = name;
            }
        }

        System.out.println(firstSignInPerson+" "+lastSignOutPerson);
    }
}
