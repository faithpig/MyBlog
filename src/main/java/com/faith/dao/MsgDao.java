package com.faith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.faith.db.DBConnect;
import com.faith.pojo.Message;

public class MsgDao implements IBaseDao<Message>{

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	@Override
	public List<Message> runSelect(String sql) {
		conn = DBConnect.getConnection();
		List<Message> mlist= null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				mlist = new ArrayList<Message>();
				try{
					while (rs.next()) {
						Message m = new Message();
						m.setDetail(rs.getString("detail"));
						m.setM_id(rs.getInt("m_id"));
						m.setName(rs.getString("name"));
						m.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("createtime")));
						mlist.add(m);
					}
				}
				catch (ParseException e) {
					mlist = null;
				}
			}
		} catch (SQLException e) {
			mlist = null;
		}
		finally{
			closeAll();
		}
		return mlist;
	}

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
	public int runAdd(String sql) {
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
	
	//关闭ResultSet,Statement,Connection
	public void closeAll() {
		DBConnect.close(rs);
		DBConnect.close(stat);
		DBConnect.close(pstat);
		DBConnect.close(conn);
	}
	
}
