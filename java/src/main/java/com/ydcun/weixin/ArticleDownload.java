/**
 * 
 */
package com.ydcun.weixin;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author ydcun-psjs
 *
 */
public class ArticleDownload {
	private static WebClient client = new WebClient(BrowserVersion.CHROME);
	public ArticleDownload() {
		client.getOptions().setUseInsecureSSL(false);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setPrintContentOnFailingStatusCode(false);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setJavaScriptEnabled(true);
	}
	@Test
	public void  gitSearchList() throws Exception{
		String url = "http://weixin.sogou.com/weixin?query=AI%E7%A7%91%E6%8A%80%E8%AF%84%E8%AE%BA&_sug_type_=&_sug_=n&type=2&page=1&ie=utf8";
		int allCount = 0;
		int page=1;
		while(true){
			System.out.println("开始第"+page+"页");
			HtmlPage paperHtml =client.getPage(url);
			HtmlElement dom = paperHtml.getDocumentElement();
			//获取页码
			String allText = dom.asText();
			System.out.println(allText);
			if(allText.indexOf("验证码")>0){
				HtmlImage valiCodeImg = (HtmlImage)paperHtml.getElementById("seccodeImage");
				ImageReader imageReader = valiCodeImg.getImageReader();
				BufferedImage bufferedImage = imageReader.read(0);

				JFrame f2 = new JFrame();
				JLabel l = new JLabel();
				l.setIcon(new ImageIcon(bufferedImage));
				f2.getContentPane().add(l);
				f2.setSize(200, 100);
				f2.setTitle("验证码");
				f2.setVisible(true);
				String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");
				f2.setVisible(false);
				//将验证码设置道进去
				HtmlElement valiCode = (HtmlElement) paperHtml.getElementById("seccodeInput");
				valiCode.click();
				valiCode.type(valicodeStr);
				HtmlElement submit = (HtmlElement) paperHtml.getElementById("submit");
				submit.click();
				System.out.print("访问太频繁需要休息!");
//				Thread.sleep(2000);
				System.out.println("休息结束开始干活");
				continue;
			}
			//获取文章列表
			List<HtmlElement> list = dom.getElementsByAttribute("div","class","news-box");
			if(list==null || list.size()<=0){
				System.out.println("没有发现 new-box");
				continue;
			}
			list = dom.getElementsByAttribute("ul","class","news-list");
			page++;
			String publisher=null;
			String title_url=null;
			String title = null;
			for(HtmlElement e :list.get(0).getElementsByTagName("li")){
				try{
					title_url = e.getElementsByTagName("h3").get(0).getElementsByTagName("a").get(0).getAttribute("href");
				}catch(Exception e1){e1.printStackTrace();}
				title = e.getElementsByTagName("h3").get(0).getElementsByTagName("a").get(0).asText();
				publisher = e.getElementsByAttribute("a", "class", "account").get(0).asText();
//				if(!"AI科技评论".equals(publisher)){
//					continue;
//				}
				System.out.println(++allCount);
				System.out.println(title_url);
				System.out.println(title);
				System.out.println(publisher);
				System.out.println();
			}
			List<HtmlElement> nextUrl = dom.getElementsByAttribute("a", "id", "sogou_next");
			if(nextUrl == null || nextUrl.size()<=0){
				break;
			}
			url = url.substring(0, url.indexOf("?"))+nextUrl.get(0).getAttribute("href");
			System.out.println(url);
			Thread.sleep(5000);
		}
		System.out.println("总共收集了"+allCount+"篇文章");
	}
	@Test
	public void printPage(){
		String url = "";
		
	}
}
