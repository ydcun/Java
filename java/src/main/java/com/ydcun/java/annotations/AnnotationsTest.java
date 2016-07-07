/**
 *
 */
package com.ydcun.java.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;

/**
 * @author ydcun_home
 * 注解
 * 	1.@interface A{}
 * 	2. 应用注解
 *  3. 对应用了注解的类进行反射操作的类
 *  4. java源文件----class文件----内存字节码 分成这个三个阶段
 *  	@Override   @Deprecated
 */
public class AnnotationsTest {
	public TestAnnotation testAnnotation = new TestAnnotation();
	@SuppressWarnings("deprecation") 
	public static void main(String[] args) {
		System.runFinalizersOnExit(false);
		if(TestAnnotation.class.isAnnotationPresent(YdcunAnnotation.class)){
			YdcunAnnotation annotation = (YdcunAnnotation)TestAnnotation.class.getAnnotation(YdcunAnnotation.class);
			System.out.println(annotation);
			System.out.println(annotation.color());
			System.out.println(annotation.attayAttr().length);
			System.out.println(annotation.lamp());
			System.out.println(annotation.annotationAttr());
			System.out.println(annotation.clazz());
		}
	}
}

/**元注解 注解保留到运行时**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@interface YdcunAnnotation{
	String color() default "blue";
	String value();
	int[] attayAttr() default {1,3,4};//数组类型
	TrafficLamp lamp() default TrafficLamp.RED;//枚举类型
	MetaAnnotation annotationAttr() default @MetaAnnotation("lam");//注解类型
	Class clazz() default Object.class;
}
@YdcunAnnotation(color = "red",value="123",attayAttr={123,3},annotationAttr=@MetaAnnotation("y"))
class TestAnnotation{
	@YdcunAnnotation("ydcun")
	public static void main(String[] args) {

	}
}

@interface MetaAnnotation{
	String value();
}

enum TrafficLamp{
	RED(60){
		@Override
		public TrafficLamp nextLamp() {
			return GREEN;
		}
	},GREEN(70){
		@Override
		public TrafficLamp nextLamp() {
			return YELLOW;
		}
	},YELLOW(10){
		@Override
		public TrafficLamp nextLamp() {
			return RED;
		}
	};
	public abstract TrafficLamp nextLamp();
	private int time;
	private TrafficLamp(int time){this.time = time;}
}
