package com.ydcun.java.pattern.ObserverPattern;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ydcun-pro on 2017/8/7.
 */
public class AndroidApp implements Observer{
    public void update(Observable o, Object arg) {
        System.out.println("dddd");
    }
}
