/**
 * 
 */
package com.ydcun.paper.entity;

import java.util.List;

/**
 * @author ydcun-psjs
 * Web of science 查询因子使用
 */
public class Journal {
	public String abbrJournal;
	public String journalTitle;
	public String issn;
	public String eissn;
	public String edition;
	public List<String> jcrCoverageYears;
	/**  
	 * 获取addrJournal  
	 * @return addrJournal addrJournal  
	 */
	public String getAbbrJournal() {
		return abbrJournal;
	}
	/**  
	 * 设置addrJournal  
	 * @param addrJournal addrJournal  
	 */
	public void setAbbrJournal(String abbrJournal) {
		this.abbrJournal = abbrJournal;
	}
	/**  
	 * 获取journalTitle  
	 * @return journalTitle journalTitle  
	 */
	public String getJournalTitle() {
		return journalTitle;
	}
	/**  
	 * 设置journalTitle  
	 * @param journalTitle journalTitle  
	 */
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	/**  
	 * 获取issn  
	 * @return issn issn  
	 */
	public String getIssn() {
		return issn;
	}
	/**  
	 * 设置issn  
	 * @param issn issn  
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}
	/**  
	 * 获取eissn  
	 * @return eissn eissn  
	 */
	public String getEissn() {
		return eissn;
	}
	/**  
	 * 设置eissn  
	 * @param eissn eissn  
	 */
	public void setEissn(String eissn) {
		this.eissn = eissn;
	}
	/**  
	 * 获取edition  
	 * @return edition edition  
	 */
	public String getEdition() {
		return edition;
	}
	/**  
	 * 设置edition  
	 * @param edition edition  
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}
	/**  
	 * 获取jcrCoverageYears  
	 * @return jcrCoverageYears jcrCoverageYears  
	 */
	public List<String> getJcrCoverageYears() {
		return jcrCoverageYears;
	}
	/**  
	 * 设置jcrCoverageYears  
	 * @param jcrCoverageYears jcrCoverageYears  
	 */
	public void setJcrCoverageYears(List<String> jcrCoverageYears) {
		this.jcrCoverageYears = jcrCoverageYears;
	}
	
	
	
}
