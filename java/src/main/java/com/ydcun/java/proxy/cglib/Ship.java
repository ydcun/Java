package com.ydcun.java.proxy.cglib;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Ship {
    public void travel(){
        System.out.println("轮船正在行驶");
    }
}

class ShipProxy implements MethodInterceptor{
    private Enhancer enhancer = new Enhancer();

    public Object getObject(Class c){
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        methodProxy.invokeSuper(o, objects);
        System.out.println("end");
        return null;
    }
}

class Demo{
    public static void main(String[] arge){
        ShipProxy proxy = new ShipProxy();
        Ship ship = (Ship)proxy.getObject(Ship.class);
        ship.travel();
        System.out.println((Integer)127==(Integer)127);
    }
}