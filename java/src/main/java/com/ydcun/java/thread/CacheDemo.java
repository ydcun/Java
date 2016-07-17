package com.ydcun.java.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ydcun_home
 *
 */
public class CacheDemo {
 private Map<String,Object> cache = new HashMap<String,Object>();
 public static void main(String[] args) {
  
 }
 private ReadWriteLock rwl = new ReentrantReadWriteLock();
 public Object get(String name){
  rwl.readLock().lock();
  Object value =null;
  try{
   value = cache.get(name);
   if(value==null){
    rwl.readLock().unlock();
    rwl.writeLock().lock();
    try{
     if(value==null){
      value="aaa";//去数据库里取值
     }
    }finally{
     rwl.writeLock().unlock();
    }
    rwl.readLock().lock();
   }
  }finally{
   rwl.readLock().unlock();
  }
  return value;
 }
}
