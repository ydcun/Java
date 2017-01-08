/**
 * 
 */
package com.ydcun.java.util;

import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author ydcun-psjs
 *
 */
public class MachineCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMac());
	}
	private static String getMac() {
	    try {
	       Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
	      while (el.hasMoreElements()) {
	        byte[] mac = el.nextElement().getHardwareAddress();
	        if (mac == null)
	          continue;

	         StringBuilder builder = new StringBuilder();
	        for (byte b : mac) {
	           builder.append(b);
	           builder.append("-");
	         }
	         builder.deleteCharAt(builder.length() - 1);
	        return builder.toString();

	       }
	     }
	    catch (Exception exception) {
	       exception.printStackTrace();
	     }
	    return null;
	   }

}
