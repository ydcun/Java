/**
 * 
 */
package com.ydcun.java.enums;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * @author ydcun_home
 *	1.先用普通的类来实现enum 要用大量的if else 在方法中
 *	2.将方法定义成抽象的，在子类中重写抽象方法
 *  3.定义枚举类型，很容易获取枚举的名字，排行（从0开始）,从名字到对象（valueOf）,获取枚举中所有values
 *  4.枚举添加构造方法
 */
public class EnumTest {
	//用java枚举方式实现,
	/* 1.方法要在枚举类型之后
	 * 2.构造方法不能是公有的
	 * 3.在构造方法中参数不能写 WeekDay3.valueOf(name);因为类都还没有初始化完不能调用只能先记住string在进行转换
	 */
	public enum WeekDay3{
		SUN("MON"),MON("TUE"),TUE("WED"),WED("THI"),THI("FRI"),FRI("SAT"),SAT("SUN");
		private String nextDay;
		WeekDay3(){}
		WeekDay3(String nextDay){
			this.nextDay=nextDay;
		}
		public WeekDay3 nextDay(){
			return WeekDay3.valueOf(nextDay);
		}
	}
	public static void main(String[] args) {
		//方法一
		WeekDay weekDay = WeekDay.MON;
		System.out.println(weekDay);
		//方法二
		WeekDay2 weekDay2 = WeekDay2.MON;
		System.out.println(weekDay2);
		//方法三
		WeekDay3 weekDay3 = WeekDay3.FRI;
		System.out.println(weekDay3);
		System.out.println(weekDay3.name());//对象找名字
		System.out.println(weekDay3.ordinal());//对象排行
		System.out.println(WeekDay3.valueOf("SUN").ordinal());//名字找对象
		System.out.println(WeekDay3.values().length);//所有元素转化为数组
		System.out.println(weekDay3.nextDay());
		
	}
}
