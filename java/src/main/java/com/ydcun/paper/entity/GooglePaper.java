/**
 * 
 */
package com.ydcun.paper.entity;

import java.util.Map;

/**
 * @author ydcun-psjs
 *
 */
public class GooglePaper {
	private String refCount;
	private String info;
	private String scirp;
	private Map<String,String> refFormat;
	public String getRefCount() {
		return refCount;
	}
	public void setRefCount(String refCount) {
		this.refCount = refCount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getScirp() {
		return scirp;
	}
	public void setScirp(String scirp) {
		this.scirp = scirp;
	}
	public Map<String, String> getRefFormat() {
		return refFormat;
	}
	public void setRefFormat(Map<String, String> refFormat) {
		this.refFormat = refFormat;
	}
	
	
}
