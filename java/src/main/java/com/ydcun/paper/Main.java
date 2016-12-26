/**
 * 
 */
package com.ydcun.paper;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author ydcun-psjs
 * 查询陈老师dblp文章影响因子
 * 1.获取dblp文章列表
 * 2.查询文章的影响因子（文章发表；最新）
 * 3.查询文章引用数
 */

public class Main {
	public static String url_dblp="http://dblp.uni-trier.de/pers/hd/c/Chen:Yiqiang";
	public static void main(String[] args) throws Exception{
		
		WebClient webclient = new WebClient(); 
		HtmlPage htmlpage = webclient.getPage(url_dblp); 
	}
}


