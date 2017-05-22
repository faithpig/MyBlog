package com.faith.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.faith.pojo.Album;
import com.faith.pojo.Blog;
import com.faith.pojo.Message;
import com.faith.pojo.Photo;
import com.faith.service.AlbumService;
import com.faith.service.BlogService;
import com.faith.service.MsgService;
import com.faith.service.PhotoService;
import com.faith.tools.Pager;
import com.opensymphony.xwork2.ActionContext;

public class HomeAction {
	
	private PhotoService ptoSer = new PhotoService();
	private BlogService bloSer = new BlogService();
	private AlbumService albSer = new AlbumService();
	private MsgService msgSer = new MsgService();
	
	private int id;
	private Blog blog;
	private Photo photo;
	private Album al;
	private Message msg;


	private Pager pager;
	private String p_method;
	private String k; //跳页的页码
	
	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public String getP_method() {
		return p_method;
	}

	public void setP_method(String p_method) {
		this.p_method = p_method;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Album getAl() {
		return al;
	}

	public void setAl(Album al) {
		this.al = al;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String home(){
		pager = new Pager(bloSer.getBlogList().size(),7);
		if("before".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.before();
		}
		else if("next".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.next();
		}
		else if("jump".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.jump(Integer.parseInt(k));
		}
		al = albSer.findAlbum(1);
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		List<Blog> bl = bloSer.getBlogListByPage(pager);
		int bl_size = bl.size();
		for(int i = 0; i<bl_size ; i++){
			//去除html标签
			bl.get(i).setContent(((bl.get(i).getContent().replaceAll("</?[^>]+>", "")).replaceAll("<a>\\s*|\t|\r|\n</a>", "")).replaceAll("&nbsp;", ""));
		}
		ActionContext.getContext().put("blist", bl);
		return "home";
	}

	public String viewBlog(){
		al = albSer.findAlbum(1);
		blog = bloSer.findBlog(id);
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		if(null != blog){
			return "blog";
		}
		return "not found";
	}
	
	public String photoList(){
		al = albSer.findAlbum(1);
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		return "plist";
	}
	
	public String viewPhoto(){
		al = albSer.findAlbum(1);
		photo = ptoSer.findPhoto(id);
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		if(null != photo){
			return "photo";
		}
		return "not found";
	}
	
	public String msg(){
		pager = new Pager(msgSer.getMsg().size(),5);
		if("before".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.before();
		}
		else if("next".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.next();
		}
		else if("jump".equals(p_method)){
			pager.setM(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_m")));
			pager.setCurrentPage(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_c")));
			pager.setTotal(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_t")));
			pager.jump(Integer.parseInt(k));
		}
		al = albSer.findAlbum(1);
		ActionContext.getContext().put("mlist", msgSer.getMsgListByPage(pager));
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		return "message";
	}
	
	public String leaveMsg(){
		int entity_id = msgSer.addMsg(msg);
		if(entity_id > 0){
			ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('插入成功！')</script>");
		}
		else{
			ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('插入失败！')</script>");
		}
		ActionContext.getContext().put("mlist", msgSer.getMsg());
		ActionContext.getContext().put("plist", ptoSer.getPhotoList());
		return msg();
	}
	
}
