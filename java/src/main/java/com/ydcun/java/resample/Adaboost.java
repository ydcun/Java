package com.ydcun.java.resample;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/*
 * 参考资料
 * http://www.cnblogs.com/sunrye/p/4568647.html
 * */
class Stump{
    public int dim;
    public double thresh;
    public String condition;
    public double error;
    public ArrayList<Integer> labelList;
    double factor;
    
    public String toString(){
        return "dim is "+dim+"\nthresh is "+thresh+"\ncondition is "+condition+"\nerror is "+error+"\nfactor is "+factor+"\nlabel is "+labelList;
    }
}

class Utils{
    //加载数据集
    public static ArrayList<ArrayList<Double>> loadDataSet(String filename) throws IOException{
        ArrayList<ArrayList<Double>> dataSet=new ArrayList<ArrayList<Double>>();
        FileInputStream fis=new FileInputStream(filename);
        InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
        BufferedReader br=new BufferedReader(isr);
        String line="";
        
        while((line=br.readLine())!=null){
            ArrayList<Double> data=new ArrayList<Double>();
            String[] s=line.split(" ");
            
            for(int i=0;i<s.length-1;i++){
                data.add(Double.parseDouble(s[i]));
            }
            dataSet.add(data);
        }
        return  dataSet;
    }
    
    //加载类别
    public static ArrayList<Integer> loadLabelSet(String filename) throws NumberFormatException, IOException{
        ArrayList<Integer> labelSet=new ArrayList<Integer>();
        
        FileInputStream fis=new FileInputStream(filename);
        InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
        BufferedReader br=new BufferedReader(isr);
        String line="";
        
        while((line=br.readLine())!=null){
            String[] s=line.split(" ");
            labelSet.add(Integer.parseInt(s[s.length-1]));
        }
        return labelSet;
    }
    //测试用的
    public static void showDataSet(ArrayList<ArrayList<Double>> dataSet){
        for(ArrayList<Double> data:dataSet){
            System.out.println(data);
        }
    }
    //获取最大值，用于求步长
    public static double getMax(ArrayList<ArrayList<Double>> dataSet,int index){
        double max=-9999.0;
        for(ArrayList<Double> data:dataSet){
            if(data.get(index)>max){
                max=data.get(index);
            }
        }
        return max;
    }
    //获取最小值，用于求步长
    public static double getMin(ArrayList<ArrayList<Double>> dataSet,int index){
        double min=9999.0;
        for(ArrayList<Double> data:dataSet){
            if(data.get(index)<min){
                min=data.get(index);
            }
        }
        return min;
    }
    
    //获取数据集中以该feature为特征，以thresh和conditions为value的叶子节点的决策树进行划分后得到的预测类别
    public static ArrayList<Integer> getClassify(ArrayList<ArrayList<Double>> dataSet,int feature,double thresh,String condition){
        ArrayList<Integer> labelList=new ArrayList<Integer>();
        if(condition.compareTo("lt")==0){
            for(ArrayList<Double> data:dataSet){
                if(data.get(feature)<=thresh){
                    labelList.add(1);
                }else{
                    labelList.add(-1);
                }
            }
        }else{
            for(ArrayList<Double> data:dataSet){
                if(data.get(feature)>=thresh){
                    labelList.add(1);
                }else{
                    labelList.add(-1);
                }
            }
        }
        return labelList;
    }
    //求预测类别与真实类别的加权误差
    public static double getError(ArrayList<Integer> fake,ArrayList<Integer> real,ArrayList<Double> weights){
        double error=0;
        
        int n=real.size();

        for(int i=0;i<fake.size();i++){
            if(fake.get(i)!=real.get(i)){
                error+=weights.get(i);

            }
        }
        
        return error;
    }
    //构造一棵单节点的决策树，用一个Stump类来存储这些基本信息。
    public static Stump buildStump(ArrayList<ArrayList<Double>> dataSet,ArrayList<Integer> labelSet,ArrayList<Double> weights,int n){
        int featureNum=dataSet.get(0).size();
        
        int rowNum=dataSet.size();
        Stump stump=new Stump();
        double minError=999.0;
        System.out.println("第"+n+"次迭代");
        for(int i=0;i<featureNum;i++){
            double min=getMin(dataSet,i);
            double max=getMax(dataSet,i);    
            double step=(max-min)/(rowNum);
            for(double j=min-step;j<=max+step;j=j+step){
                String[] conditions={"lt","gt"};//如果是lt，表示如果小于阀值则为真类，如果是gt，表示如果大于阀值则为正类
                for(String condition:conditions){
                    ArrayList<Integer> labelList=getClassify(dataSet,i,j,condition);
                    
                    double error=Utils.getError(labelList,labelSet,weights);
                    if(error<minError){
                        minError=error;
                        stump.dim=i;
                        stump.thresh=j;
                        stump.condition=condition;
                        stump.error=minError;
                        stump.labelList=labelList;
                        stump.factor=0.5*(Math.log((1-error)/error));
                    }
                    
                }
            }
            
        }
        
        return stump;
    }
    
    public static ArrayList<Double> getInitWeights(int n){
        double weight=1.0/n;
        ArrayList<Double> weights=new ArrayList<Double>();
        for(int i=0;i<n;i++){
            weights.add(weight);
        }
        return weights;
    }
    //更新样本权值
    public static ArrayList<Double> updateWeights(Stump stump,ArrayList<Integer> labelList,ArrayList<Double> weights){
        double Z=0;
        ArrayList<Double> newWeights=new ArrayList<Double>();
        int row=labelList.size();
        double e=Math.E;
        double factor=stump.factor;
        for(int i=0;i<row;i++){
            Z+=weights.get(i)*Math.pow(e,-factor*labelList.get(i)*stump.labelList.get(i));
        }
        
        
        for(int i=0;i<row;i++){
            double weight=weights.get(i)*Math.pow(e,-factor*labelList.get(i)*stump.labelList.get(i))/Z;
            newWeights.add(weight);
        }
        return newWeights;
    }
    //对加权误差累加
    public static ArrayList<Double> InitAccWeightError(int n){
        ArrayList<Double> accError=new ArrayList<Double>();
        for(int i=0;i<n;i++){
            accError.add(0.0);
        }
        return accError;
    }
    
    public static ArrayList<Double> accWeightError(ArrayList<Double> accerror,Stump stump){
        ArrayList<Integer> t=stump.labelList;
        double factor=stump.factor;
        ArrayList<Double> newAccError=new ArrayList<Double>();
        for(int i=0;i<t.size();i++){
            double a=accerror.get(i)+factor*t.get(i);
            newAccError.add(a);
        }
        return newAccError;
    }
    
    public static double calErrorRate(ArrayList<Double> accError,ArrayList<Integer> labelList){
        ArrayList<Integer> a=new ArrayList<Integer>();
        int wrong=0;
        for(int i=0;i<accError.size();i++){
            if(accError.get(i)>0){
                if(labelList.get(i)==-1){
                    wrong++;
                }
            }else if(labelList.get(i)==1){
                wrong++;
            }
        }
        double error=wrong*1.0/accError.size();
        return error;
    }
    
    public static void showStumpList(ArrayList<Stump> G){
        for(Stump s:G){
            System.out.println(s);
            System.out.println(" ");
        }
    }
}


public class Adaboost {

    /**
     * @param args
     * @throws IOException 
     */
    
    public static ArrayList<Stump> AdaBoostTrain(ArrayList<ArrayList<Double>> dataSet,ArrayList<Integer> labelList){
        int row=labelList.size();
        ArrayList<Double> weights=Utils.getInitWeights(row);
        ArrayList<Stump> G=new ArrayList<Stump>();
        ArrayList<Double> accError=Utils.InitAccWeightError(row);
        int n=1;
        while(true){
            Stump stump=Utils.buildStump(dataSet,labelList,weights,n);//求一棵误差率最小的单节点决策树
            G.add(stump);
            weights=Utils.updateWeights(stump,labelList,weights);//更新权值
            accError=Utils.accWeightError(accError,stump);//将加权误差累加，因为这样不用再利用分类器再求了
            double error=Utils.calErrorRate(accError,labelList);
            if(error<0.001){
                break;
            }
            n++;
        }
        return G;
    }
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
    	String path = Adaboost.class.getResource(".").toString().substring(6);
        String file=path+"AdaBoost.txt";
        ArrayList<ArrayList<Double>> dataSet=Utils.loadDataSet(file);
        ArrayList<Integer> labelSet=Utils.loadLabelSet(file);
        ArrayList<Stump> G=AdaBoostTrain(dataSet,labelSet);
        Utils.showStumpList(G);
        System.out.println("finished");
    }

}