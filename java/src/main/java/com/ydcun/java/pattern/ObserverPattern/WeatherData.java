package com.ydcun.java.pattern.ObserverPattern;

import javafx.beans.InvalidationListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by ydcun-pro on 2017/8/7.
 */
public class WeatherData extends Observable{
    List<Observer> list = new ArrayList<Observer>();


    public void addListener(InvalidationListener listener) {

    }
    public void adb(){
        this.setChanged();
        this.notifyObservers();
    }

    public void removeListener(InvalidationListener listener) {

    }
}
