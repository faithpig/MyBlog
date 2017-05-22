package com.faith.db;

import java.sql.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author cry elephant
 * @version 1.0  数据库连接
 */
public class DBConnect {
	
	public final static String DRIVER; 
	public final static String URL;
	public final static String USER;
	public final static String PASSWORD;

	//读取资源文件
	static{
		Properties prop=new Properties();
		InputStream inStream = null;
		inStream = PropertyUtils.class.getResourceAsStream("/com/faith/db/conninfo.properties");
		//inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conninfo.properties");
		try {
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DRIVER = prop.get("driver").toString();
		URL = prop.get("url").toString();
		USER = prop.get("user").toString();
		PASSWORD = prop.get("password").toString();
	}
	
	//获取连接
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(DRIVER);//加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWORD);//获取连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
	public static void close(Connection conn){
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				conn=null;
		}
	}
	
	// 关闭语句
	public static void close(Statement stat) {
		if (null != stat) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				stat=null;
		}
	}

	// 关闭结果
	public static void close(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
	}
	
}
