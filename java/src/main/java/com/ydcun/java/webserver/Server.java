/**
 * 
 */
package com.ydcun.java.webserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 * @author ydcun-psjs
 *
 */
public class Server {
	public static void main(String[] args) throws Exception {
		 URL url = new URL("http://localhost:8080/a.txt");
		 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 
		 conn.setRequestProperty("Range","bytes=5-");
		 InputStream in = conn.getInputStream();
		 int leng=0;
		 byte buffer[] = new byte[1024];
		 FileOutputStream out = new FileOutputStream("d:/a.txt",true);
		 while((leng=in.read(buffer))>0){
			 out.write(buffer, 0, leng);
		 }
		 
		 
	}

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void demo1() throws IOException, FileNotFoundException {
		ServerSocket server = new ServerSocket(9999);
		Socket sock = server.accept();
		
		FileInputStream in = new FileInputStream("d:/a.html");
		OutputStream out = sock.getOutputStream();
		
		int len = 0;
		byte buffer[] = new byte[1024];
		while((len = in.read(buffer))>0){
			out.write(buffer,0,len);
		}
		out.flush();
		in.close();
		out.close();
		sock.close();
		server.close();
	}
}
