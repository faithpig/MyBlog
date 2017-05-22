package com.faith.service;

import java.util.List;

import com.faith.dao.AdminDao;
import com.faith.pojo.Admin;

public class AdminService {
	
	private AdminDao dao = new AdminDao();
	
	public Admin auth(String username,String pwd,int type){
		String sql ="select * from admin where username='"+username+"' and pwd='"+pwd+"' and type="+type;
		List<Admin> list = dao.runSelect(sql);
		if(list.isEmpty()) return null;
		Admin a = list.get(0);
		if(null == a) return null;
		return a;
	}
}
