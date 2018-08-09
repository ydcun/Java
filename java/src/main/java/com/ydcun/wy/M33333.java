package com.ydcun.wy;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class CaseSub{
    int index;//星期几
    int count;//每天有几次活动
    List<Date> sList = new ArrayList<Date>(),
            eList = new ArrayList<Date>();//活动开始时间结束时间
}
class Case{
    SimpleDateFormat sb = new SimpleDateFormat("HH:mm:ss");
    int count;//每周有几次
    List<CaseSub> subList = new ArrayList<CaseSub>();

    //活动当天
    public void addLine(String line) throws ParseException {
       String[] array = line.split(" ");
       CaseSub sub = new CaseSub();
       sub.index = Integer.parseInt(array[0]);
       sub.count = Integer.parseInt(array[1]);
       for(int i=2; i<array.length; i++){
           String[] tempDatas = array[i].split("-");//开始时间结束时间
           sub.sList.add(sb.parse(tempDatas[0]));
           sub.eList.add(sb.parse(tempDatas[1]));
        }
        subList.add(sub);
    }
}


public class M33333 {
    SimpleDateFormat sb = new SimpleDateFormat("HH:mm:ss");
    static Map<Integer, Map<String,String>> caseMap = new HashMap<Integer, Map<String,String>>();
    public static void init() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String temp;
        List<Case> cList = new ArrayList<>();

        int caseSum = Integer.parseInt(scanner.nextLine());//样例数
        Case c;//保存单个样例
        for(int i=0; i<caseSum; i++){//获取没个样例
            temp = scanner.nextLine();//每个样例内有几天有活动
            c = new Case();
            c.count = Integer.parseInt(temp);//每个样例内有几天活动
            for(int j=0;j<c.count;j++){
                temp = scanner.nextLine();
                c.addLine(temp);
            }
            cList.add(c);//样例保存在list中
        }
        //读取查询册书
        temp = scanner.nextLine();
        int selectCount = Integer.parseInt(temp);
        for(int i=0; i<selectCount; i++){//读取每次查询时间并判断输出
            String[] tempArray = scanner.nextLine().split(" ");
            int index = Integer.parseInt(tempArray[0]);//要查询的星期几
            String time = tempArray[1];//查询的时间

        }
    }
    //输出每次查询的结果
    public void vei(List<Case> cList, int index, String time) throws Exception {
        for(int i=0; i<cList.size(); i++){
            Case c = cList.get(i);
            List<CaseSub> subList = c.subList;
            for(int j=0; j<subList.size();j++){
                CaseSub sub = subList.get(j);
                if(sub.index == index){
                    Long s = duibi(sub, time);
                    System.out.println(s);
                }
            }
        }
    }
    public Long duibi(CaseSub sub, String time) throws Exception {
        List<Date> sList = sub.sList;
        List<Date> eList = sub.eList;
        long tempDataLong = sb.parse(time).getTime();
        long mix = Long.MAX_VALUE;
        for(int m=0; m<sList.size();m++){
            if(tempDataLong>=sList.get(m).getTime() && tempDataLong<=eList.get(m).getTime()){
                return 0L;
            }else{
                long tempMix = sList.get(m).getTime()-tempDataLong;
                if(tempMix < mix){
                    mix = tempMix;
                }
            }
        }
        if(mix>=0){
            return mix;
        }else{
            return
        }
    }


    public static void main(String[] args) {

    }
}
