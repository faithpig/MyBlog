package com.faith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.faith.pojo.Photo;
import com.faith.db.DBConnect;

public class PhotoDao implements IBaseDao<Photo>{
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	@Override
	public List<Photo> runSelect(String sql) {
		conn = DBConnect.getConnection();
		List<Photo> plist = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				plist = new ArrayList<Photo>();
				while (rs.next()) {
					Photo p = new Photo();
					p.setDescribe(rs.getString("describe"));
					p.setName(rs.getString("name"));
					p.setP_id(rs.getInt("p_id"));
					p.setPath(rs.getString("path"));
					plist.add(p);
				}
			}
		} catch (SQLException e) {
			plist = null;
		}
		finally{
			closeAll();
		}
		return plist;
	}

	//删除操作,flag 0为失败，1为成功
	@Override
	public int runUpdate(String sql) {
		int flag = 0;
		conn = DBConnect.getConnection();
		try {
			pstat = conn.prepareStatement(sql);
			flag = pstat.executeUpdate();
		} catch (SQLException e) {
		}
		return flag;
	}
	
	@Override
	public int runAdd(String sql){
		int key = -1; 
		conn = DBConnect.getConnection();
		try {
			pstat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstat.executeUpdate();
			rs = pstat.getGeneratedKeys();
	        if (rs.next()) {    
	            key = rs.getInt(1);    
	          }  
		} catch (SQLException e) {}
        return key; 
	}
	
	public String selectId(String sql) {
		conn = DBConnect.getConnection();
		String ans = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				while (rs.next()) {
					ans = rs.getString("photoNum");
				}
			}
		} catch (SQLException e) {
			ans = null;
		}
		finally{
			closeAll();
		}
		return ans;
	}
	
	//关闭ResultSet,Statement,Connection
	public void closeAll() {
		DBConnect.close(rs);
		DBConnect.close(stat);
		DBConnect.close(pstat);
		DBConnect.close(conn);
	}

}
