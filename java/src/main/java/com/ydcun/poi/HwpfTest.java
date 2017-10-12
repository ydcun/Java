package com.ydcun.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HwpfTest {
    public static String path = HwpfTest.class.getClassLoader().getResource("com/ydcun/poi/").getPath();

    public static void main(String[] arge) throws Exception {
       System.out.println(path);
       new HwpfTest().testWrite();
    }
    public void testWrite2() throws Exception{

    }
   public void testWrite() throws Exception {  
      String templatePath = path+"/reportDate.doc";
      InputStream is = new FileInputStream(templatePath);
      HWPFDocument doc = new HWPFDocument(is);
      Range range = doc.getRange();
      //把range范围内的${reportDate}替换为当前的日期  
      range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
      OutputStream os = new FileOutputStream(path+"/reportDate_write.doc");
      //把doc输出到输出流中  
      doc.write(os);  
      this.closeStream(os);  
      this.closeStream(is);  
   }  
    
   /** 
    * 关闭输入流 
    * @param is 
    */  
   private void closeStream(InputStream is) {  
      if (is != null) {  
         try {  
            is.close();  
         } catch (IOException e) {
            e.printStackTrace();  
         }  
      }  
   }  
   
   /** 
    * 关闭输出流 
    * @param os 
    */  
   private void closeStream(OutputStream os) {  
      if (os != null) {  
         try {  
            os.close();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
      }  
   }  
    
   
}  