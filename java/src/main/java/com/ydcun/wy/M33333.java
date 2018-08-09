package com.ydcun.wy;

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
    SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
           sub.sList.add(sb.parse("2018-01-0"+sub.index+" "+tempDatas[0]));
           sub.eList.add(sb.parse("2018-01-0"+sub.index+" "+tempDatas[1]));
        }
        subList.add(sub);
    }
}


public class M33333 {
    static  SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            //对每个case内星期进行排序
            Collections.sort(c.subList, new Comparator<CaseSub>() {
                @Override
                public int compare(CaseSub o1, CaseSub o2) {
                    return o1.index-o2.index;
                }
            });
//            for(CaseSub sub: c.subList){
//                System.out.print(sub.index+">");
//            }
//            System.out.println();

            cList.add(c);//样例保存在list中

            //读取查询册书
            temp = scanner.nextLine();
            int selectCount = Integer.parseInt(temp);
            for(int j=0; j<selectCount; j++){//读取每次查询时间并判断输出
                String[] tempArray = scanner.nextLine().split(" ");
                int index = Integer.parseInt(tempArray[0]);//要查询的星期几
                String time = tempArray[1];//查询的时间
                vei(c,index, time);//执行一次查询下
            }
        }
    }
    //输出每次查询的结果
    public static void vei(Case c, int index, String time) throws Exception {
        List<CaseSub> subList = c.subList;
        Date indexData = sb.parse("2018-01-0"+index+" "+time);
        boolean isFindIndex=false;
        for(int j=0; j<subList.size();j++){
            CaseSub sub = subList.get(j);
            if(sub.index == index){
                isFindIndex = true;//匹配到星期
                Long s = duibi(c,sub,index, time);
                System.out.println(s/1000);
            }
        }
        if(isFindIndex==false){//没有匹配到星期，则找后一天有活动的第一个时间
            CaseSub tempSub =null;//存放后一天的sub
            Date tempData = null;
            for(int i=0;i<subList.size();i++){
                CaseSub sub = subList.get(i);
                if(sub.index>index){
                    tempSub = sub;
                    tempData = new Date(tempSub.sList.get(0).getTime());

                    break;
                }
            }
            if(tempSub == null){
                tempSub = subList.get(0);

                tempData = new Date(tempSub.sList.get(0).getTime()+ 7*24*60*60*1000);
            }
            long l = tempData.getTime()-indexData.getTime();
            System.out.println(l/1000);
        }
    }
    public static Long duibi(Case c,CaseSub sub,int index, String time) throws Exception {
        boolean isFindIndex=false;
        Date indexData = sb.parse("2018-01-0"+index+" "+time);
        List<Date> sList = sub.sList;
        List<Date> eList = sub.eList;
        long tempDataLong = indexData.getTime();
        long mix = Long.MAX_VALUE;
        for(int m=0; m<sList.size();m++){
            if(tempDataLong>=sList.get(m).getTime() && tempDataLong<=eList.get(m).getTime()){
                return 0L;
            }else{

                long tempMix = sList.get(m).getTime()-tempDataLong;
                if(tempMix < mix && tempMix>0){
                    mix = tempMix;
                    return mix;
                }
            }
        }
        if(mix>=0){
            return mix;
        }else{
            List<CaseSub> subList = c.subList;
            CaseSub tempSub =null;//存放后一天的sub
            Date tempData = null;
            for(int i=0;i<subList.size();i++){
                sub = subList.get(i);
                if(sub.index>index){
                    tempSub = sub;
                    tempData = tempSub.sList.get(0);
                }
            }
            if(tempSub == null){
                tempSub = subList.get(0);

                tempData = new Date(tempSub.sList.get(0).getTime()+ 7*24*60*60*1000);
            }
            long l = tempData.getTime()-indexData.getTime();
            return l;
        }
    }


    public static void main(String[] args) throws Exception {
//        1
//        4
//        3 1 10:00:00-15:00:00
//        1 2 08:00:00-14:00:00 18:00:00-20:00:00
//        6 3 09:00:00-11:00:00 13:00:00-14:00:00 17:00:00-22:00:00
//        7 3 09:00:00-10:30:00 13:30:00-14:00:00 17:30:00-22:00:00
//        5
//        1 14:03:30
//        3 14:02:23
//        1 02:00:00
//        5 17:00:00
//        4 13:13:13


        init();
    }
}
