package com.ydcun.java.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFileChooser;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

	/**
	 * 建立与打印机的连接
	 * @author Administrator
	 *
	 */
public class PrintDemo {
	
	public static void main(String[] args) {
		
		print("D:/a.doc");
	}
	
	public static void print(String fileName){
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		if(osName==null){
			return;
		}
		if(osName.toLowerCase().contains("win".toLowerCase())){//window
			System.out.println("win print service");
		}else{//linux
			System.out.println("linux print service");
		}
	}
	public static void print2(String filename){
		try {
			Runtime.getRuntime().exec("start /min winword \"" + filename +
				    "\" /q /n /f /mFilePrint /mFileExit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void printWin2() {
		JFileChooser fileChooser = new JFileChooser(); //创建打印作业
//		int state = fileChooser.showOpenDialog(null);
//		if(state == fileChooser.APPROVE_OPTION){
			File file = new File("D:/a.doc"); //获取选择的文件
			//构建打印请求属性集
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			//设置打印格式，因为未确定类型，所以选择autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			//查找所有的可用的打印服务
//			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			//定位默认的打印服务
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			//显示打印对话框
//			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, 
//					defaultService, flavor, pras);
			PrintService service = defaultService;
			if(service != null){
				try {
					DocPrintJob job = service.createPrintJob(); //创建打印作业
					FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);
					job.print(doc, pras);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//		}
	}
	   public static void printWordWin1(String path) {
	        System.out.println("开始打印");
	        ComThread.InitSTA();
	        ActiveXComponent word=new ActiveXComponent("Word.Application");
	        Dispatch doc=null;
	        Dispatch.put(word, "Visible", new Variant(false));
	        Dispatch docs=word.getProperty("Documents").toDispatch();
	        doc=Dispatch.call(docs, "Open", path).toDispatch();
	        
	        try {
	            Dispatch.call(doc, "PrintOut");//打印
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("打印失败");
	        }finally{
	            try {
	                if(doc!=null){
	                    Dispatch.call(doc, "Close",new Variant(0));
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	            //释放资源
	            ComThread.Release();
	        }
	    }
}
