/**
 * 
 */
package com.ydcun.java.enums;

/**
 * @author ydcun_home
 *
 *	PS:用抽象方法定义nextDay 让各个子类自己实现。替换大量的if-else 同样 toString方法也子类重写
 */
public abstract class WeekDay2 {
	private WeekDay2(){
		
	}
	public final static WeekDay2 SUN = new WeekDay2(){
		@Override
		public WeekDay2 nextDay() {
			return MON;
		}
		public String toString() {return "SUN";}};
	public final static WeekDay2 MON = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return TUE;
		}
		public String toString() {return "MON";}};
	public final static WeekDay2 TUE = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return WED;
		}
		public String toString() {return "TUE";}};
	public final static WeekDay2 WED = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return THU;
		}
		public String toString() {return "WED";}};
	public final static WeekDay2 THU = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return FRI;
		}
		public String toString() {return "THU";}};
	public final static WeekDay2 FRI = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return SAT;
		}
		public String toString() {return "FRI";}};
	public final static WeekDay2 SAT = new WeekDay2(){

		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return SUN;
		}
		public String toString() {return "SAT";}};
	
	public abstract WeekDay2 nextDay();
}
