package com.faith.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.faith.dao.BlogDao;
import com.faith.pojo.Blog;
import com.faith.tools.HtmlCode;
import com.faith.tools.Pager;
public class BlogService {
	
	private BlogDao dao = new BlogDao();

	public List<Blog> getBlogList(){
		String sql ="select * from blog order by b_id desc;";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Blog>();
		}
	}
	
	//分页查询博客
	public List<Blog> getBlogListByPage(Pager pager){
		String sql =" select * from (select * from blog order by b_id desc) as blog limit "+pager.getM()+","+pager.getN()+";";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Blog>();
		} 
	}
	
	public Blog findBlog(int id){
		String sql ="select * from blog where b_id = "+id+";";
		Blog b = null;
		if(!dao.runSelect(sql).isEmpty()){
			b = dao.runSelect(sql).get(0);
		}
		return b;
	}
	
	//添加博客
	public int addBlog(Blog b){
		String cTime = new SimpleDateFormat("yyyy-MM-dd").format(b.getCreateTime());
		String aTime = new SimpleDateFormat("yyyy-MM-dd").format(b.getAlterTime());
		b.setContent(HtmlCode.encode(b.getContent()));
		String sql = "insert into blog(title,content,createtime,altertime) values('"+b.getTitle()+"','"+b.getContent()+"','"+cTime+"','"+aTime+"');";
		return dao.runAdd(sql);
	}
	
	//删除博客
	public int delBlog(int id){
		String sql = "delete from blog where b_id = "+id+";";
		return dao.runUpdate(sql);
	}
	
	public int delBlogList(ArrayList<Integer> idList){
		int flag = 1;
		for(int i=0;i<idList.size();i++){
			if(delBlog(idList.get(i))<=0) flag=0;
		}
		return flag;
	}

}
