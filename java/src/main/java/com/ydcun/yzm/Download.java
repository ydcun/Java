package com.ydcun.yzm;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {

	public void saveToFile(String destUrl,String path,String name) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(path+"/"+name+".jpg");
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String pathImage = Download.class.getResource(".").toString().replaceAll("target/classes","src/main/java").substring(6)+"image";
		Download dw = new Download();
		for(int i =0;i<1000;i++){
			dw.saveToFile("http://xjcainfo.miitbeian.gov.cn/getVerifyCode?91",pathImage,""+i);
			if(i%100==0){
				System.out.println(i);
			}
			Thread.sleep(100);
		}
		System.out.println(pathImage);
	}
}