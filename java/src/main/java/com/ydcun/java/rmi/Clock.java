package com.ydcun.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * 继承Remote类
 */
public interface Clock extends Remote {

    /**
     * 必须抛出RemoteException异常
     * @return
     * @throws RemoteException
     */
    Date getDate() throws RemoteException;
}
