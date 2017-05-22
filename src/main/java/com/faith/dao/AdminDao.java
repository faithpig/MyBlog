package com.faith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.faith.db.DBConnect;
import com.faith.pojo.Admin;

public class AdminDao implements IBaseDao<Admin>{

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	@Override
	public List<Admin> runSelect(String sql) {
		conn = DBConnect.getConnection();
		List<Admin> alist = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				alist = new ArrayList<Admin>();
				while (rs.next()) {
					Admin a = new Admin();
					a.setUsername(rs.getString("username"));
					a.setType(rs.getInt("type"));
					a.setPwd(rs.getString("pwd"));
					alist.add(a);
				}
			}
		} catch (SQLException e) {
			alist = null;
		}
		finally{
			closeAll();
		}
		return alist;
	}

	@Override
	public int runUpdate(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int runAdd(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	//关闭ResultSet,Statement,Connection
	public void closeAll() {
		DBConnect.close(rs);
		DBConnect.close(stat);
		DBConnect.close(pstat);
		DBConnect.close(conn);
	}
}
