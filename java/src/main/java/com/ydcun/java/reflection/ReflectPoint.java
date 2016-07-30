/**
 * 
 */
package com.ydcun.java.reflection;

import java.util.Date;

/**
 * @author ydcun_home
 *
 */
public class ReflectPoint {
	private ReflectPoint p;
	private Date birthday = new Date();
	private int x;
	public int y;
	public String str1 = "box";
	public String str2 = "bus";
	public String str3 = "zoo";
	
	public ReflectPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReflectPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return str1 +":"+ str2 +":"+ str3;
	}
	public static void main(String[] args) {
		for(String str:args){
			System.out.println(str);
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
//	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReflectPoint other = (ReflectPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	/**  
	 * 获取p  
	 * @return p p  
	 */
	public ReflectPoint getP() {
		return p;
	}
	
	/**  
	 * 设置p  
	 * @param p p  
	 */
	public void setP(ReflectPoint p) {
		this.p = p;
	}
	
	/**  
	 * 获取x  
	 * @return x x  
	 */
	public int getX() {
		return x;
	}
	
	/**  
	 * 设置x  
	 * @param x x  
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**  
	 * 获取y  
	 * @return y y  
	 */
	public int getY() {
		return y;
	}
	
	/**  
	 * 设置y  
	 * @param y y  
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**  
	 * 获取birthday  
	 * @return birthday birthday  
	 */
	public Date getBirthday() {
		return birthday;
	}
	
	/**  
	 * 设置birthday  
	 * @param birthday birthday  
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
