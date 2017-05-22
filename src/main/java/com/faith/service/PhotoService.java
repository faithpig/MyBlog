package com.faith.service;

import java.util.ArrayList;
import java.util.List;

import com.faith.dao.PhotoDao;
import com.faith.pojo.Photo;
import com.faith.tools.Pager;

public class PhotoService {

	private PhotoDao dao = new PhotoDao();
	
	public List<Photo> getPhotoList(){
		String sql ="select * from photo order by p_id desc;";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Photo>();
		}
	}
	
	//分页查询图片
	public List<Photo> getPhotoListByPage(Pager pager){
		String sql =" select * from (select * from photo order by p_id desc) as photo limit "+pager.getM()+","+pager.getN()+";";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Photo>();
		} 
	}
	
	public Photo findPhoto(int id){
		String sql ="select * from photo where p_id = "+id+";";
		Photo p = null;
		if(!dao.runSelect(sql).isEmpty()){
			p = dao.runSelect(sql).get(0);
		}
		return p;
	}
	
	//添加图片
	public int addPhoto(Photo p){
		String sql = "INSERT INTO photo(name,`describe`,path) VALUES ('"+p.getName()+"', '"+p.getDescribe()+"', '"+p.getPath()+"');";
		return dao.runAdd(sql);
	}
	
	//删除图片
	public int delPhoto(int id){
		String sql = "delete from photo where p_id = "+id+";";
		return dao.runUpdate(sql);
	}
	
	//获取创建图片id
	public String getPid(){
		String sql = "select * from seq";
		String ans = dao.selectId(sql);
		sql = "update seq set photoNum='"+(Integer.parseInt(ans)+1)+"' where photoNum ='"+ans+"'";
		int flag = dao.runUpdate(sql);
		if(flag==1) return ans;
		else return "100000";
	}
	
}
