package com.ydcun.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {
    public static void main(String[] args) throws Exception {
        Clock clock = new ClockImpl();
        Clock stub = (Clock) UnicastRemoteObject.exportObject( clock,1099);
        LocateRegistry.createRegistry(1099);
        Registry registry= LocateRegistry.getRegistry();
        registry.bind("clock",stub);
        System.out.print("Clock service send ok");
    }
}
