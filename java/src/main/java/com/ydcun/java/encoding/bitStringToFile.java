/**
 * 
 */
package com.ydcun.java.encoding;

/**
 * @author ydcun-psjs
 *
 */
public class bitStringToFile {
	public static byte[] byte2String(String binaryByteString){ 
        //假设binaryByte 是01，10，011，00以，分隔的格式的字符串 
        String[] binaryStr=binaryByteString.split(","); 
        byte[] byteArray=new byte[binaryStr.length]; 
        for(int i=0;i<byteArray.length;i++){ 
            byteArray[i]=(byte)parse(binaryStr[i]); 
        } 
        return byteArray; 
    } 
    public static int parse(String str){ 
        //32位 为负数 
        if(32==str.length()){ 
            str="-"+str.substring(1); 
            return -(Integer.parseInt(str, 2)+Integer.MAX_VALUE+1); 
        } 
        return Integer.parseInt(str, 2); 
    }
	public static void main(String[] args) {
		String str = "1010101010101010101010101010101";
		byte[] a = byte2String(str);
		System.out.println(a);
	}
}
