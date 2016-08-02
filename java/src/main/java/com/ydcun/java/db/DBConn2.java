package com.ydcun.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author Linwenliang 数据库操作辅助工具类 功能：获取数据库连接对象，关闭连接，执行更新语句，执执查询语句
 */
public class DBConn2 {
	// private static final String
	// URL="jdbc:sqlserver://S1-062\\SQLEXPRESS:1433;database=news";
	// private static final String USER="sa";
	// private static final String PWD="123";
	private Connection conn = null;// 连接对象
	private PreparedStatement ps = null;// 预编译语句处理对象
	private ResultSet rs = null;// 结果集对象

	/**
	 * @return返回数据库连接对象
	 */
	public Connection getConn() {
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// conn=DriverManager.getConnection(URL,USER,PWD);
			Context context = new InitialContext();
			DataSource source = (DataSource) context
					.lookup("java:comp/env/jdbc/bam");
			conn = source.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭所有数据库操作对象
	 */
	public void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param sql执行的SQL语句
	 * @param prasSQL语句中的参数值的数组
	 * @return
	 */
	public int update(String sql, String[] pras) {
		int resu = 0;
		try {
			// 获取连接对象,同时创建预编译处理对象
			ps = getConn().prepareStatement(sql);
			// 判断 是否有参数，如果有参数循环为参数赋值
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					ps.setString(i + 1, pras[i]);// 循环为参数赋值
				}
			}
			// 执行更新
			resu = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();// 关闭所有对象
		}
		return resu;
	}

	/**
	 * @param sqlSQL执行语句
	 * @param prasSQL参数数组
	 * @return返回结果集
	 */
	public ResultSet query(String sql, String[] pras) {
		try {
			ps = getConn().prepareStatement(sql);
			// 判断 是否有参数，如果有参数循环为参数赋值
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					ps.setString(i + 1, pras[i]);// 循环为参数赋值
				}
			}
			// 执行查询
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
