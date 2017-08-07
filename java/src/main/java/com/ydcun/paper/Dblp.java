/**
 * 
 */
package com.ydcun.paper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ydcun.java.interview.baidu.main;
import com.ydcun.paper.entity.DblpPaper;

/**
 * @author ydcun-psjs
 * dblp：http://dblp.uni-trier.de/pers/hd/c/Chen:Yiqiang
 */
public class Dblp {
	public static String url_dblp="http://dblp.dagstuhl.de/pers/hd/k/Koshizuka:Noboru";
//	public static String url_dblp="http://dblp.uni-trier.de/pers/hd/c/Chen:Yiqiang";
	final WebClient webClient = new WebClient();
	public void impactFactor() throws Exception {
		final HtmlPage page = webClient.getPage("http://dblp.uni-trier.de/pers/hd/c/Chen:Yiqiang");
	}
	public static void main(String[] args) {
		try {
			List<DblpPaper> list = new Dblp().homePage();
			for(DblpPaper d:list){
				System.out.println(d.getNr());
				System.out.println(d.getTitel());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<DblpPaper> homePage() throws Exception {
	    try{
	        final HtmlPage page = webClient.getPage(url_dblp);
	        HtmlElement paperElement = page.getHtmlElementById("publ-section");
	        System.out.println(paperElement);
	        //期刊文章
	        List<HtmlElement> jlist = paperElement.getElementsByAttribute("li", "class", "entry article");
	        //会议文章
	        List<HtmlElement> clist = paperElement.getElementsByAttribute("li", "class", "entry inproceedings");
	        List<DblpPaper> paperList = new ArrayList<DblpPaper>();
	        DblpPaper paper = null;
			for(HtmlElement el:jlist){
	        	paper  = loadPager(el);
	        	paperList.add(paper);
	        }
			for(HtmlElement el:clist){
	        	paper  = loadPager(el);
	        	paperList.add(paper);
	        }
			
			for(DblpPaper p:paperList){
				//System.out.println(p);
			}
			return paperList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return null;
	}
	
	
	/**
	 * 封装加载dblp中文章条目数据
	 * @param dblp_element
	 * @return
	 */
	private DblpPaper loadPager(HtmlElement dblp_element){
		//dblp编号
		String nr = dblp_element.getElementsByAttribute("div","class","nr").get(0).getTextContent();
		
		//列表里包含作者，题目，出版社
		List<HtmlElement> itemprop = dblp_element.getElementsByAttribute("span","itemprop","name");
		
		//作者有序
		List<String> authorList = new ArrayList<String>();
		for(int i=0;i<itemprop.size()-2;i++){
			authorList.add(itemprop.get(i).getTextContent());
		}
		//出版社
		String orgName = itemprop.get(itemprop.size()-2).getTextContent();
		//题目
		String title = itemprop.get(itemprop.size()-1).getTextContent();
		//卷册
		String volume = null;
		try{dblp_element.getElementsByAttribute("span","itemprop","volumeNumber").get(0).getTextContent();
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("卷册信息未找到");
		}
		//页码
		List<HtmlElement> pageNumberObject = dblp_element.getElementsByAttribute("span","itemprop","pagination");
		String pageNumber = null;
		if(pageNumberObject!=null&&pageNumberObject.size()>0){
			pageNumber = pageNumberObject.get(0).getTextContent();
		}
		//出版年份
		String datePublished = dblp_element.getElementsByAttribute("span","itemprop","datePublished").get(0).getTextContent();
		
		DblpPaper paper = new DblpPaper(nr,authorList,title,orgName,volume,pageNumber,datePublished);
		return paper;
	}
}
