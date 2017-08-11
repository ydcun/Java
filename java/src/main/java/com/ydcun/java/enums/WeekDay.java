/**
 * 
 */
package com.ydcun.java.enums;

/**
 * @author ydcun_home
 *
 */
public class WeekDay {
	private WeekDay(){
		
	}
	public final static WeekDay SUN = new WeekDay();
	public final static WeekDay MON = new WeekDay();
	public final static WeekDay TUE = new WeekDay();
	public final static WeekDay WED = new WeekDay();
	public final static WeekDay THU = new WeekDay();
	public final static WeekDay FRI = new WeekDay();
	public final static WeekDay SAT = new WeekDay();
	
	public WeekDay nextDay(){
		if(this==SUN){
			return MON;
		}else if(this==MON){
			return TUE;
		}else if(this==TUE){
			return WED;
		}else if(this==WED){
			return THU;
		}else if(this==THU){
			return FRI;
		}else if(this==FRI){
			return SAT;
		}else{
			return SUN;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(this==SUN){
			return "SUN";
		}else if(this==MON){
			return "MON";
		}else if(this==TUE){
			return "TUE";
		}else if(this==WED){
			return "WED";
		}else if(this==THU){
			return "THU";
		}else if(this==FRI){
			return "FRI";
		}else{
			return "SAT";
		}
	}
}
