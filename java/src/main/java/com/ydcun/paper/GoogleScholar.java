/**
 * 
 */
package com.ydcun.paper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ydcun.java.interview.baidu.main;
import com.ydcun.paper.entity.GooglePaper;

/**
 * @author ydcun-psjs
 *	通过google scholar查询论文信息
 */
public class GoogleScholar {
	private static final WebClient client = new WebClient(BrowserVersion.CHROME);
	public GoogleScholar() {
		client.getOptions().setUseInsecureSSL(true);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setPrintContentOnFailingStatusCode(false);
		client.getOptions().setJavaScriptEnabled(false);
	}
	@Test
	public GooglePaper getPaperFromTitle(String title){
		try {
			HtmlPage paperHtml =client.getPage("https://xue.glgoo.com/scholar?q="+title);
//			HtmlPage paperHtml =client.getPage("https://www.xichuan.pub/scholar?q="+title);
			HtmlElement paperDom = paperHtml.getHtmlElementById("gs_ccl_results");
			List<HtmlElement> paperList = paperDom.getElementsByAttribute("div","class", "gs_ri");//页面所有文章
			if(paperList==null || paperList.size()==0){
				return null;
			}
			HtmlElement paper = paperList.get(0);//暂定搜索的第一篇为目标文章，通过文章内容可以自查
			
			List<HtmlElement> linkDom = paper.getElementsByAttribute("div","class", "gs_fl");//搜索到论文的地下连接列表
			List<HtmlElement> aList = linkDom.get(0).getElementsByTagName("a");//0被引用次数 1 相关文章 2所有版本3 引用4 保存
			String cited_v = aList.get(0).getTextContent();
			String ref_onclick_v = aList.get(3).getAttribute("onclick");
			
			//引用数
			String cited_count = cited_v.substring(cited_v.indexOf("：")+1); 
			//查询引用
			String[] tempArr = ref_onclick_v.split("'");
			String info =tempArr[1];
			String scirp=tempArr[3];
			//Map<String, String> map = getRefFromPaper(info, scirp);
			GooglePaper gPaper = new GooglePaper();
			gPaper.setInfo(info);
			gPaper.setRefCount(cited_count);
			//gPaper.setRefFormat(map);
			gPaper.setScirp(scirp);
			return gPaper;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static void main(String[] args) throws Exception {
		GoogleScholar a = new GoogleScholar();
			File file = new File("papers");
			System.out.println(file.getAbsolutePath());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString;
			int line=0;
			while((tempString = reader.readLine())!=null){
				try{
				System.out.println(""+(++line)+":"+tempString+" 引用数:"+a.getPaperFromTitle(tempString).getRefCount());
				}catch(Exception e){
					e.printStackTrace();
				}
				Thread.sleep(1000);
			}
	}
	/**
	 * @throws Exception
	 * 获取不同格式的文章引用
	 */
	private Map<String,String> getRefFromPaper(String info,String scirp) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String ref_url="https://google8.ga/scholar?q=info:"+info+":google8.ga/scholar/&output=cite&scirp="+scirp+"&hl=zh-CN";
		System.out.println(ref_url);
		HtmlPage ref_page = null;
		try {
			ref_page = client.getPage(ref_url);
			String[] tempArr = ref_page.asText().split("\n");
			for(int i=1;i<tempArr.length-1;i+=2){
				map.put(tempArr[i],tempArr[i+1]);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return map;
	}
}
