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

import com.faith.pojo.Blog;
import com.faith.tools.HtmlCode;
import com.faith.db.DBConnect;

public class BlogDao implements IBaseDao<Blog>{

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	@Override
	public List<Blog> runSelect(String sql) {
		conn = DBConnect.getConnection();
		List<Blog> blist = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				blist = new ArrayList<Blog>();
				try{
					while (rs.next()) {
						Blog b = new Blog();
						b.setAlterTime(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("altertime")));
						b.setB_id(rs.getInt("b_id"));
						b.setContent(HtmlCode.decode(rs.getString("content")));
						b.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("createtime")));
						b.setTitle(rs.getString("title"));
						blist.add(b);
					}
				}
				catch (ParseException e) {
					blist = null;
				}
			}
		} catch (SQLException e) {
			blist = null;
		}
		finally{
			closeAll();
		}
		return blist;
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

	//关闭ResultSet,Statement,Connection
	public void closeAll() {
		DBConnect.close(rs);
		DBConnect.close(stat);
		DBConnect.close(pstat);
		DBConnect.close(conn);
	}
}
