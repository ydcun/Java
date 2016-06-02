package com.ydcun.java.generics;

import java.io.Serializable;

public class BaseDao<T>{
	public void create(T o){};
	public void delete(T o){};
	public void update(T o){};
	public void findById(Serializable id){};
	
}
