package com.faith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.faith.db.DBConnect;
import com.faith.pojo.Album;

public class AlbumDao implements IBaseDao<Album>{

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	@Override
	public List<Album> runSelect(String sql) {
		conn = DBConnect.getConnection();
		List<Album> alist = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(null == rs){
			}
			else{
				alist = new ArrayList<Album>();
				while (rs.next()) {
					Album a = new Album();
					a.setA_id(rs.getInt("a_id"));
					a.setAlbumName(rs.getString("albumname"));
					a.setPath(rs.getString("path"));
					a.setSinger(rs.getString("singer"));
					a.setSongName(rs.getString("songname"));
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
		int key = -1; 
		conn = DBConnect.getConnection();
		try {
			pstat = conn.prepareStatement(sql);
			key = pstat.executeUpdate();
		} catch (SQLException e) {}
        return key;
	}

	@Override
	public int runAdd(String sql) {
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
