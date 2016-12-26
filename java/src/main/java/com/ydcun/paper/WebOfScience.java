/**
 * 
 */
package com.ydcun.paper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.ydcun.paper.entity.Journal;
import com.ydcun.paper.entity.JournalProfile;


/**
 * @author ydcun-psjs
 * 
 */
public class WebOfScience {
	private static String url_complement="https://jcr.incites.thomsonreuters.com/SearchJournalsJson.action";
	private static String url_factor="https://jcr.incites.thomsonreuters.com/JournalProfileDataJson.action";
	final WebClient webClient = new WebClient();
	/**
	 * web of science 自动补全查询框模拟实现
	 * @return
	 * @throws Exception 
	 */
	public List<Journal>  complement(String text) throws Exception{
		String param = "query=acm";
		String urlStr = url_complement+"?"+param;
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setUseInsecureSSL(true);
        client.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        Set<Cookie> cookies = new HashSet<Cookie>();
        cookies.add(new Cookie(".jcr.incites.thomsonreuters.com","PSSID","B2-unYWiwpEWoER7xxZg9YH42wCMfprqO3Yi-18x2dAzkytRMrUCt6ZPQtUZbfUAx3Dx3DtLRytUIAWcRP6Syoe337DQx3Dx3D-9vvmzcndpRgQCGPd1c2qPQx3Dx3D-wx2BJQh9GKVmtdJw3700KssQx3Dx3D"));  
        Iterator<Cookie> i = cookies.iterator();
        while(i.hasNext()){
        	client.getCookieManager().addCookie(i.next());
        }
        Page page = client.getPage(urlStr);
        String context = page.getWebResponse().getContentAsString();
//        System.out.println(context);
        
        ComplementBean com = JSON.parseObject(context, new TypeReference<ComplementBean>(){});
        
		return com.getData();
	}
	public List<JournalProfile> factor(String abbrJournal, String edition) throws Exception{
		String tempUrl = url_factor+"?abbrJournal="+abbrJournal+"&edition="+edition;
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setUseInsecureSSL(true);
        client.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        Set<Cookie> cookies = new HashSet<Cookie>();
        cookies.add(new Cookie(".jcr.incites.thomsonreuters.com","PSSID","B2-unYWiwpEWoER7xxZg9YH42wCMfprqO3Yi-18x2dAzkytRMrUCt6ZPQtUZbfUAx3Dx3DtLRytUIAWcRP6Syoe337DQx3Dx3D-9vvmzcndpRgQCGPd1c2qPQx3Dx3D-wx2BJQh9GKVmtdJw3700KssQx3Dx3D"));  
        Iterator<Cookie> i = cookies.iterator();
        while(i.hasNext()){
        	client.getCookieManager().addCookie(i.next());
        }
        Page page = client.getPage(tempUrl);
		String context = page.getWebResponse().getContentAsString();
//		System.out.println(context);
		FactorBean factor = JSON.parseObject(context, new TypeReference<FactorBean>(){});
		return factor.getData();
		
	}
    public static void main(String[] args) throws Exception {
//		new WebOfScience().complement("acm");
		new WebOfScience().factor("BIOL RES","SCIE");
	}
    
}
class FactorBean{
	private String totalCount;
	private String status;
	private List<JournalProfile> data;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<JournalProfile> getData() {
		return data;
	}
	public void setData(List<JournalProfile> data) {
		this.data = data;
	}
	
}
class ComplementBean{
	private String totalCount;
	private String status;
	private List<Journal> data;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Journal> getData() {
		return data;
	}
	public void setData(List<Journal> data) {
		this.data = data;
	}
}
