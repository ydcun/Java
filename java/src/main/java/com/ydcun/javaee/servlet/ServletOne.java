package com.ydcun.javaee.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletOne extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		OutputStream out = res.getOutputStream();
		out.write("hello world!".getBytes());
		System.out.println(this.getInitParameter("data"));
		
		//请求转发
	//	this.getServletContext().getRequestDispatcher("/java2").forward(req, res);
		//重定向
	//	res.sendRedirect("/java3");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("destory");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("init");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	

}