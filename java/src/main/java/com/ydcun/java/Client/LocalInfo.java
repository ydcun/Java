package com.ydcun.java.Client;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.hyperic.sigar.SigarException;

/**
 *
 * @author Administrator
 */
public class LocalInfo implements Runnable{


	/** 
	* 字符串工具集合 
	* @author Liudong 
	*/
	private static final String PASSWORD_CRYPT_KEY = "__jDlog_"; 
	private final static String DES = "DES"; 

    //定义存储本地硬件信息的字符串
    private String encryptLocalInfoStr = "";
    private String decryptLocalInfoStr = "";
    Connection con;
    
    /** 
	* 加密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回加密后的数据 
	* @throws Exception 
	*/ 
    public static byte[] encrypt(byte[] src, byte[] key)throws Exception { 
		//DES算法要求有一个可信任的随机数源 
		SecureRandom sr = new SecureRandom(); 
		// 从原始密匙数据创建DESKeySpec对象 
		DESKeySpec dks = new DESKeySpec(key); 
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成 
		// 一个SecretKey对象 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		// Cipher对象实际完成加密操作 
		Cipher cipher = Cipher.getInstance(DES); 
		// 用密匙初始化Cipher对象 
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr); 
		// 现在，获取数据并加密 
		// 正式执行加密操作 
		return cipher.doFinal(src); 
	}
    /** 
	* 解密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回解密后的原始数据 
	* @throws Exception 
	*/ 
	public static byte[] decrypt(byte[] src, byte[] key)throws Exception { 
		// DES算法要求有一个可信任的随机数源 
		SecureRandom sr = new SecureRandom(); 
		// 从原始密匙数据创建一个DESKeySpec对象 
		DESKeySpec dks = new DESKeySpec(key); 
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 
		// 一个SecretKey对象 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		// Cipher对象实际完成解密操作 
		Cipher cipher = Cipher.getInstance(DES); 
		// 用密匙初始化Cipher对象 
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr); 
		// 现在，获取数据并解密 
		// 正式执行解密操作 
		return cipher.doFinal(src); 
	}
	/** 
	* 密码解密 
	* @param data 
	* @return 
	* @throws Exception 
	*/ 
	
	
	public final static String decrypt(String data){ 
		try { 
			
//			return new String(decrypt(hex2byte(data.getBytes()),
//			PASSWORD_CRYPT_KEY.getBytes())); 
			return new String(decrypt(hex2byte(data),
					PASSWORD_CRYPT_KEY.getBytes())); 
		}catch(Exception e) { 
		} 
		return null; 
	} 
	/** 
	* 密码加密 
	* @param password 
	* @return 
	* @throws Exception 
	*/ 
	
	public final static String encrypt(String password){ 
		try { 
			
			return byte2hex(encrypt(password.getBytes(),PASSWORD_CRYPT_KEY.getBytes())); 
		}catch(Exception e) { 
		} 
		return null; 
	}
	
	public static byte[] hex2byte(String strhex) { 
        if (strhex == null) { 
            return null; 
        } 
        int l = strhex.length(); 
        if (l % 2 == 1) { 
            return null; 
        } 
        byte[] b = new byte[l / 2]; 
        for (int i = 0; i != l / 2; i++) { 
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 
                    16); 
        } 
        return b; 
    } 

    public static String byte2hex(byte[] b) { 
        String hs = ""; 
        String stmp = ""; 
        for (int n = 0; n < b.length; n++) { 
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF)); 
            if (stmp.length() == 1) { 
                hs = hs + "0" + stmp; 
            } else { 
                hs = hs + stmp; 
            } 
        } 
        return hs.toUpperCase(); 
    }
//	/**
//	 * 获取机器码，确保唯一，不可更改
//	 * 
//	 */
//    public LocalInfo() {
//        LocalInfoCollection ldc = new LocalInfoCollection();
//        try {
//        	
//        	
//            localInfoStr = "UPDATE manager SET" +
//            		"\n v_hostIP='"+ldc.getIPAddress() + 
//               "',\n v_hostMac='" +ldc.getMacAddress() //+ ","
//               +
//                    "',\n v_hostName='"+ldc.getHostname() +
//                  "',\n v_OS='" + ldc.getOSInfo() +
//                    "',\n v_cpuModel='" +ldc.getCPUModels() +
//                    "',\n v_memorySize='" +
//                    ldc.getMemSize() +
//                    "',\n v_processNum='" +
//                    ldc.getProcessCount() + "',\n v_diskSize='" +
//                    ldc.getDiskTotalSize() + "',\n v_cpuUR='" +
//                    ldc.getCPUUsage() + "',\n v_memoryUR='" +
//                    ldc.getMemUsage() +"',\n  b_hostState=1"+" where b_isteaching=1 and v_studentID= '";//+ClientSystemInfo.stuID +"'" ;
//        } catch (SigarException se) {
//          
//            se.printStackTrace();
//        }
//        
//     
//		 }
    public LocalInfo() {
    	LocalInfoCollection ldc = new LocalInfoCollection();
    	try {
    		encryptLocalInfoStr=LocalInfo.encrypt(ldc.getMacAddress()+"_"+ldc.getCPUModels());
    		
    		decryptLocalInfoStr=LocalInfo.decrypt(encryptLocalInfoStr);
    		
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//+ClientSystemInfo.stuID +"'" ;
    }
    /**
     * @return the localInfoStr
     * 
     */
    public String getLocalInfoStr() {
        return encryptLocalInfoStr;
    }
    public String getLocalMacIP(){
    	return decryptLocalInfoStr;
    }
    
    public static void main(String args[]){
      System.out.println(new LocalInfo().getLocalInfoStr() );
      //System.out.println(new LocalInfo().MacIP);
      System.out.println(new LocalInfo().getLocalMacIP());
    }

	public void run() {
		while(true){
			try {
				//Socket socket=new Socket(ClientSystemInfo.serverIP,ClientSystemInfo.sendUpdateInfoPort );
				Socket socket=new Socket("",0);
				DataOutputStream out=new DataOutputStream(socket.getOutputStream() );
				out.writeUTF( new LocalInfo().getLocalInfoStr());
                //out.writeUTF(new LocalInfo().getLocalMacIP());
				socket.close() ;
				out.close() ;
				
			} catch (UnknownHostException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} 
			try {
				TimeUnit.MINUTES .sleep(1);
			} catch (InterruptedException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}
}
