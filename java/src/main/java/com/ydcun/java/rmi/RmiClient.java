package com.ydcun.java.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Date;

public class RmiClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1");
        Clock clock = (Clock)registry.lookup("clock");
        Date dt = clock.getDate();
        System.out.print("RMI result:"+dt);
    }
}
