package com.faith.service;

import com.faith.dao.AlbumDao;
import com.faith.pojo.Album;

public class AlbumService {
	
	private AlbumDao dao = new AlbumDao();
	
	public int updateAlbum(Album al){
		String sql = "update album set songname = '"+al.getSongName()+"',singer = '"+al.getSinger()+"',albumname = '"+al.getAlbumName()+"',path = '"+al.getPath()+"' where a_id = "+al.getA_id()+";";
		return dao.runUpdate(sql);
	}
	
	public Album findAlbum(int id){
		Album al = null;
		String sql ="select * from album where a_id = "+id+";";
		if(!dao.runSelect(sql).isEmpty()){
			al = dao.runSelect(sql).get(0);
		}
		return al;
	}
}
