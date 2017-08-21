package com.ydcun.java.pattern.ObserverPattern;

/**
 * Created by ydcun-pro on 2017/8/7.
 */
public class TestMain {
    public static void main(String[] arge){
        WeatherData wd = new WeatherData();
        AndroidApp androidApp = new AndroidApp();
        wd.addObserver(androidApp);

        wd.adb();
    }
}
