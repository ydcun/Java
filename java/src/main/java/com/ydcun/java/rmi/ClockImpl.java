package com.ydcun.java.rmi;

import java.rmi.RemoteException;
import java.util.Date;

public class ClockImpl implements Clock{
    @Override
    public Date getDate() throws RemoteException {
        return new Date();
    }
}
