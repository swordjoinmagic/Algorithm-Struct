package PAT.GradeA;

import java.util.*;

/**
 * 题意:
 *      1. 给定N个记录,每个记录包含:
 *      车牌号 时间 是否在学校(in表示在,out表示不在)
 *      2. 进行k次询问,每次询问一个具体的时间点,求解在该时间点时,有多少车停留在校园内
 *      同时最后一行输出停留时间最长的车的车牌号(不唯一的话,输出全部车牌号)和停留时间
 *      PS: 这里的停留时间是指这辆车在学校一整天的停留时间,也就是说多次停留的时间也是算在内的
 *
 * 思路:
 *      1. 对于时间的题目,靠谱的方法是转换单位,这里把拿到的所有时间都变成xx秒
 *      2. 先对所有记录按照时间顺序排序
 *      3. 对记录的合法性进行验证，每一个"in"记录必须对应"out"记录，且out记录在in记录后面出现
 *      4. 设数组 time[x]=y表示在x时刻有y辆车停留在学校，遍历所有合法记录，如果为"in"记录，那么
 *        time[x]++，如果为"out"记录，那么time[x]--。
 *
 * 坑：
 *      1. 对于in记录，如果连续出现了两个in时间间隔短的记录，那么选取后出现的in记录
 *         对于out记录，如果连续出现了两个out时间间隔短的记录，那么选取先出现的out记录
 */
public class PAT1006CarsonCampus {

    public static void main(String[] args){
        PAT1006CarsonCampus pat1006 = new PAT1006CarsonCampus();
        pat1006.Input();
    }

    class Car{
        int time;
        String plateNumber;

        public Car(int time, String plateNumber) {
            this.time = time;
            this.plateNumber = plateNumber;
        }
    }

    class Record implements Comparable<Record> {
        // 转换为秒的单位
        int time;
        // 目前状态
        boolean in;
        // 车牌号
        String plateNumber;

        @Override
        public int compareTo(Record o) {
            if(time>o.time){
                return 1;
            }else if(time<o.time){
                return -1;
            }else {
                return 0;
            }
        }

        public Record(String plateNumber,String time,String status){
            this.plateNumber = plateNumber;
            String[] times = time.split(":");
            int s = Integer.parseInt(times[2]);
            int m = Integer.parseInt(times[1]);
            int h = Integer.parseInt(times[0]);
            this.time = s+m*60+h*3600;
            this.in = status.equals("in");
        }

        @Override
        public String toString(){
            int s = time%60;
            int m = time/60%60;
            int h = time/60/60%60;
            String t = h+":"+m+":"+s;
            return this.plateNumber+" "+t+" "+(in?"in":"out");
        }
    }


    // 记录每个时刻车的数量
    TreeMap<Integer,Integer> time = new TreeMap<>();
    // 记录的个数
    int N;
    // 询问次数
    int K;
    // 保存记录
    Record[] records;

    // 用于查询目标车牌号最后一次的状态是什么（in or out）
    Map<String,Stack<Record>> map = new HashMap<>();

    List<Record> recordList = new ArrayList<>();

    // 每个车辆的持续时间
    Map<String,Integer> maxLastTimeMap = new HashMap<>();
    int maxLastTime;
    Deque<Car> maxLastTimeCar = new LinkedList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();

        records = new Record[N];
        for(int i=0;i<N;i++){
            String number = in.next();
            String time = in.next();
            String status = in.next();

            Record record = new Record(number,time,status);

            records[i] = record;
        }

        Slove(in);
    }

    /**
     * 验证记录合法性
     */
    public void Valid(){
        Arrays.sort(records);

        for(Record record : records){

            Stack<Record> stack = map.getOrDefault(record.plateNumber,null);
            if(stack==null){
                Stack<Record> s1 = new Stack<>();
                s1.push(record);
                map.put(record.plateNumber,s1);
                continue;
            }

            // 获得该车牌号最后一次在校园的记录
            // 如果两条记录的在学校的状态相同,那么上一条记录为非法记录
            // 抛弃上一条记录
            Record lastRecord = stack.peek();
            if(lastRecord.in != record.in) {
                // 两条记录状态不同,且两条记录的相隔时间是最短的,
                // 那么不抛弃该条记录
                stack.push(record);
            }else if(record.in){
                // 对于连续出现的in记录，抛弃出现较早的
                // 抛弃上一条记录
                stack.pop();
                stack.push(record);
            }else if(!record.in){
                // 对于连续出现的out记录，抛弃出现较晚的
            }

        }

        for(Stack<Record> s : map.values()){
            recordList.addAll(s);
        }
    }

    /**
     * 记录每个时刻的在学校的车辆
     */
    private void ComputeTime(){

        Collections.sort(recordList);

        for(Record record : recordList){

            // 记录车辆
            if(record.in)
                maxLastTimeMap.put(record.plateNumber, maxLastTimeMap.getOrDefault(record.plateNumber,0)-record.time);
            else {
                maxLastTimeMap.put(record.plateNumber, maxLastTimeMap.getOrDefault(record.plateNumber, 0) + record.time);
                if(maxLastTime<=maxLastTimeMap.get(record.plateNumber)){
                    maxLastTime = maxLastTimeMap.get(record.plateNumber);

                    Car car = new Car(maxLastTime,record.plateNumber);
                    while (!maxLastTimeCar.isEmpty() && maxLastTimeCar.getLast().time!=maxLastTime){
                        maxLastTimeCar.pollLast();
                    }
                    maxLastTimeCar.add(car);
                }
            }

            // 如果此时刻的车辆数量尚未被记录,那么该时刻的车辆数量=上一时刻车辆数目
            if(!time.containsKey(record.time)){
                Integer preRecordKey = time.lowerKey(record.time);
                if(preRecordKey!=null)
                    time.put(record.time,time.getOrDefault(preRecordKey,0));
                else
                    time.put(record.time,0);
            }

            if(record.in){
                time.put(record.time,time.get(record.time)+1);
            }else {
                time.put(record.time,time.get(record.time)-1);
            }
        }
    }

    public void Slove(Scanner in){
        Valid();
        ComputeTime();

        for(int i=0;i<K;i++){
            String queryTime = in.next();
            String[] times = queryTime.split(":");
            int s = Integer.parseInt(times[2]);
            int m = Integer.parseInt(times[1]);
            int h = Integer.parseInt(times[0]);
            int t = s+m*60+h*3600;

            int carNumber;
            if(!time.containsKey(t)) {
                Integer lowerKey = time.lowerKey(t);
                if (lowerKey != null) {
                    carNumber = time.get(lowerKey);
                } else {
                    carNumber = 0;
                }
            }else {
                carNumber = time.get(t);
            }
            System.out.println(carNumber);
        }


        List<Car> cars = new ArrayList<>(maxLastTimeCar);
        cars.sort((o1, o2) -> o1.plateNumber.compareTo(o2.plateNumber));
        // 找持续时间最长的车辆
        for(Car car: cars){
            System.out.print(car.plateNumber+" ");
        }
        System.out.println(toTime(maxLastTime));
    }

    public String toTime(int time){
        int s = time%60;
        int m = time/60%60;
        int h = time/60/60%60;
        String t = String.format("%02d:%02d:%02d",h,m,s);
        return t;
    }
}
