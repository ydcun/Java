/**
 * 
 */
package com.ydcun.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author ydcun-psjs
 *
 */
public class ConnectionDemo {
	@Test
	public void connDemo() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/solo?useUnicode=yes&characterEncoding=utf8","root","123abc");
		PreparedStatement psta = conn.prepareStatement("select * from b3_solo_archivedate");
		ResultSet rs = psta.executeQuery();
		while(rs.next()){
			System.out.println(" "+rs.getString(1));
		}
		rs.close();
		psta.close();
		conn.close();
		
	}
	//@Test
	public void update() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?useUnicode=true&characterEncoding=gb2312","root","123abc");
		PreparedStatement psta = conn.prepareStatement("update article set title='ydcun' where id=2");
		psta.executeQuery();
	} 
}
