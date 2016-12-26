/**
 * 
 */
package com.ydcun.paper.entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * @author ydcun-psjs
 *
 */
public class DblpPaper {
	private String nr;//dblp编号
	private List<String> authorList;//作者 有序
	private String titel;//标题
	private String orgName;//出版机构名
	private String volume;//刊物卷册
	private String pageNumber;//刊登页码
	private String datePublished;//出版年份
	
	public DblpPaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DblpPaper(String nr, List<String> authorList, String titel, String orgName, String volume, String pageNumber,
			String datePublished) {
		super();
		this.nr = nr;
		this.authorList = authorList;
		this.titel = titel;
		this.orgName = orgName;
		this.volume = volume;
		this.pageNumber = pageNumber;
		this.datePublished = datePublished;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(nr);
		sb.append(" ");
		for(String str:authorList){
			sb.append(str);
			sb.append(" ");
		}
		sb.append(orgName).append(" ")
			.append(titel).append(" ")
			.append(volume).append(" ");
		if(pageNumber!=null){
			sb.append(pageNumber).append(" ");
		}
		sb.append("(").append(datePublished).append(")");
		return sb.toString();
	}

	/**  
	 * 获取nr  
	 * @return nr nr  
	 */
	public String getNr() {
		return nr;
	}

	/**  
	 * 设置nr  
	 * @param nr nr  
	 */
	public void setNr(String nr) {
		this.nr = nr;
	}

	/**  
	 * 获取authorList  
	 * @return authorList authorList  
	 */
	public List<String> getAuthorList() {
		return authorList;
	}

	/**  
	 * 设置authorList  
	 * @param authorList authorList  
	 */
	public void setAuthorList(List<String> authorList) {
		this.authorList = authorList;
	}

	/**  
	 * 获取titel  
	 * @return titel titel  
	 */
	public String getTitel() {
		return titel;
	}

	/**  
	 * 设置titel  
	 * @param titel titel  
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**  
	 * 获取orgName  
	 * @return orgName orgName  
	 */
	public String getOrgName() {
		return orgName;
	}

	/**  
	 * 设置orgName  
	 * @param orgName orgName  
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**  
	 * 获取volume  
	 * @return volume volume  
	 */
	public String getVolume() {
		return volume;
	}

	/**  
	 * 设置volume  
	 * @param volume volume  
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**  
	 * 获取pageNumber  
	 * @return pageNumber pageNumber  
	 */
	public String getPageNumber() {
		return pageNumber;
	}

	/**  
	 * 设置pageNumber  
	 * @param pageNumber pageNumber  
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**  
	 * 获取datePublished  
	 * @return datePublished datePublished  
	 */
	public String getDatePublished() {
		return datePublished;
	}

	/**  
	 * 设置datePublished  
	 * @param datePublished datePublished  
	 */
	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}
	
	
	
}
