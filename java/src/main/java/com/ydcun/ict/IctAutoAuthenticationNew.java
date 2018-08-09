package com.ydcun.ict;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

/**
 * @author ydcun-psjs
 */
public class IctAutoAuthenticationNew {
	private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
	public IctAutoAuthenticationNew() {
//		client.getOptions().setUseInsecureSSL(true);
//		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
//		client.getOptions().setThrowExceptionOnScriptError(false);
//		client.getOptions().setPrintContentOnFailingStatusCode(false);
//		client.getOptions().setJavaScriptEnabled(true);
//		client.getOptions().setRedirectEnabled(true);

		// 1 启动JS
		webClient.getOptions().setJavaScriptEnabled(true);
		// 2 禁用Css，可避免自动二次请求CSS进行渲染
		webClient.getOptions().setCssEnabled(false);
		// 3 启动客户端重定向
		webClient.getOptions().setRedirectEnabled(true);

		// 4 js运行错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		// 5 设置超时
		webClient.getOptions().setTimeout(50000);

	}
	public void getPaperFromTitle(String name,String password){
		try {
			HtmlPage paperHtml =webClient.getPage("http://159.226.39.22/srun_portal_pc.php?ac_id=1&");
			//System.out.println(paperHtml.asXml());

			// 等待JS驱动dom完成获得还原后的网页
			webClient.waitForBackgroundJavaScript(50000);
			HtmlForm form = (HtmlForm)paperHtml.getHtmlElementById("form2");
			System.out.println(form.asXml());

			HtmlTextInput username = (HtmlTextInput)form.getInputByName("username");
			username.setValueAttribute(name);
			HtmlPasswordInput userpassword = (HtmlPasswordInput)form.getInputByName("password");
			userpassword.setValueAttribute(password);
			HtmlSubmitInput button = (HtmlSubmitInput)paperHtml.getElementById("button");
			System.out.println(form.asXml());
			System.out.println(button.asXml());
			HtmlPage page = (HtmlPage) button.click();

			System.out.println(page.asXml());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception {
		IctAutoAuthenticationNew ict = new IctAutoAuthenticationNew();
		ict.getPaperFromTitle("yudiancun","123456789");
	}
}
