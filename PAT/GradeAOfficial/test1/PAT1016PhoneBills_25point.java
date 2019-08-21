package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定24个时段的收费情况,
 *      给定N条记录,每条记录包含顾客名字及其通话时间和通话状态.
 *      求最后每个客户的账单,打印客户通话开始时间结束时间,持续时间和总费用
 * 坑:
 *      1. 不能匹配成一对的online,offline会被ignore,
 *      首先按时间排序,连续出现多个online(但没有与之匹配的offline)时,
 *      取最后一个online进行计算
 *      2. 月份
 *      3. 美分,美元的换算.题目给出的是 美分/分钟 的计费,输出要求的是美元
 * 思路:
 *      1. 模拟.时间题必备法则,将标准时间格式转换成秒再来计算.
 */
public class PAT1016PhoneBills_25point {

    public static void main(String[] args){
        PAT1016PhoneBills_25point pat1016 = new PAT1016PhoneBills_25point();
        pat1016.Input();
    }

    // 24小时收费情况
    double[] rateStruct;

    // 记录条数
    int N;

    class Record{
        public String customName;
        public int month;
        public String timeWithFormat;
        public int time;   // 时间转换为分钟数
        public boolean onLine;
        public int day;
        public int hours;
        public int minute;


        public Record(String customName, String timeWithFormat, String onLine) {
            this.customName = customName;
            this.timeWithFormat = timeWithFormat;
            this.onLine = onLine.equals("on-line");

            getTimeWithTimeFormat(timeWithFormat);
        }

        private void getTimeWithTimeFormat(String timeFormat){
            String[] formats = timeFormat.split(":");
            this.month = Integer.parseInt(formats[0]);

            int dd = Integer.parseInt(formats[1]); this.day = dd;
            int hh = Integer.parseInt(formats[2]); this.hours = hh;
            int mm = Integer.parseInt(formats[3]); this.minute = mm;

            this.time = mm + hh*60 + dd * 60 * 24;
        }
    }

    // 保存所有记录
    Record[] records;


    // 以顾客名为键,保存所有合法几率
    TreeMap<String, List<Record>> map = new TreeMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);

        // 初始化24小时收费情况
        rateStruct = new double[24];

        for(int i=0;i<24;i++) rateStruct[i] = in.nextDouble()/100;

        N = in.nextInt();
        records = new Record[N];

        for(int i=0;i<N;i++){
            String name = in.next();
            String timeFormat = in.next();
            String online = in.next();
            records[i] = new Record(name,timeFormat,online);
        }

        Slove();
    }

    public void Slove(){
        // 首先根据时间对记录进行排序
        Arrays.sort(records,new Comparator<Record>(){
            @Override
            public int compare(Record o1,Record o2){
                if(o1.time>o2.time)
                    return 1;
                else if(o1.time<o2.time)
                    return -1;
                else
                    return 0;
            }
        });

        // 将所有合法记录筛选出来
        for(int i=0;i<N;i++){
            Record record = records[i];
            if(!map.containsKey(record.customName)) map.put(record.customName,new ArrayList<>());

            List<Record> customRecords = map.get(record.customName);

            // 如果该记录是OnLine记录,
            // 判断该顾客上一个记录是不是online的,如果是,
            // 那么抛弃上一条记录,用这一条记录
            if(record.onLine){
                if(customRecords.size()==0){
                    customRecords.add(record);
                }else if(customRecords.get(customRecords.size()-1).onLine){
                    // 抛弃上一条,用这一条
                    customRecords.remove(customRecords.size()-1);
                    customRecords.add(record);
                }else {
                    customRecords.add(record);
                }
            }else {
                // 如果当前数据是offline,判断上一条是不是offline,
                // 如果是,抛弃这一条数据,用上一条的
                if(customRecords.size()==0 ||
                        !customRecords.get(customRecords.size()-1).onLine){
                    // 抛弃该条数据
                    continue;
                }else {
                    customRecords.add(record);
                }
            }
        }

        // 计算每个顾客的每两条on-off记录的时长和消费
        for(String name : map.keySet()){
            List<Record> recordList = map.get(name);
            // 如果最后一条记录是online,那么去掉这条记录
            if(recordList.get(recordList.size()-1).onLine) recordList.remove(recordList.size()-1);

            System.out.format("%s %02d\n",name,recordList.get(0).month);

            double totalCosts = 0;

            for(int i=0;i<recordList.size();i+=2){
                Record record1 = recordList.get(i);
                Record record2 = recordList.get(i+1);

                String startTime = String.format("%02d:%02d:%02d",record1.day,record1.hours,record1.minute);
                String endTime = String.format("%02d:%02d:%02d",record2.day,record2.hours,record2.minute);

                // 持续时长
                int lastingTime = record2.time - record1.time;

                double costs = 0;
                costs += (60-record1.minute)*rateStruct[record1.hours];
                costs += record2.minute * rateStruct[record2.hours];

                for(int t=record1.hours+1;t<=record2.hours-1;t++){
                    costs += rateStruct[t] * 60;
                }

                for(int d=record1.day;d<record2.day;d++){
                    for(int t=0;t<24;t++)
                        costs += rateStruct[t] * 60;
                }

                totalCosts += costs;

                System.out.format("%s %s %d $%.2f\n",startTime,endTime,lastingTime,costs);
            }
            System.out.format("Total amount: $%.2f\n",totalCosts);
        }

    }
}
