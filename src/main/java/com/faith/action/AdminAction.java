package com.faith.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


import org.apache.struts2.ServletActionContext;

import com.faith.pojo.Album;
import com.faith.pojo.Blog;
import com.faith.pojo.Message;
import com.faith.pojo.Photo;
import com.faith.service.AdminService;
import com.faith.service.AlbumService;
import com.faith.service.BlogService;
import com.faith.service.MsgService;
import com.faith.service.PhotoService;
import com.faith.tools.Pager;
import com.opensymphony.xwork2.ActionContext;

import net.coobird.thumbnailator.Thumbnails;

public class AdminAction {
	
	private PhotoService ptoSer = new PhotoService();
	private BlogService bloSer = new BlogService();
	private AlbumService albSer = new AlbumService();
	private MsgService msgSer = new MsgService();
	private AdminService admSer = new AdminService();
	
	private Blog blog;
	private String pwd;
	private int id;
	private Photo photo;
	private Album album;
	private Message msg;
	private String say;
	private ArrayList<Integer> idList = new ArrayList<Integer>();
	
	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	public ArrayList<Integer> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<Integer> idList) {
		this.idList = idList;
	}

	private File file;
    private String fileFileName;
    private String fileContentType;
    
	private Pager pager;
	private String p_method;
	private String k; //跳页的页码

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

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public Map<String, Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public String login(){
		if(admSer.auth("faithfish", pwd, 2)!=null){
			getSession().put("username", "faithfish");
			return admin();
		}
		else{
			say ="提示：密码错误！";
			return "log_json";
		}
	}
	
	public String clear(){
		getSession().clear();
		return "login";
	}
	
	public String admin(){
		if(getSession().get("username")!=null) return "welcome_admin";
		else {
			return "login";
		}
	}
	
	public String listBlog(){
		if(getSession().get("username")!=null){
			pager = new Pager(bloSer.getBlogList().size(),15);
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
			ActionContext.getContext().put("blist", bloSer.getBlogListByPage(pager));
			return "listBlog";
		}
		else{
			return "login";
		}
	}
	
	public String addBlog(){
		if(getSession().get("username")!=null){
			Boolean tag = false;
			blog.setCreateTime(new Date());
			blog.setAlterTime(new Date());
			int entity_id = bloSer.addBlog(blog);
			if(entity_id > 0){
				tag =true;
			}
			if(tag == false){
				return "addBlog";
			}
			else{
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('插入成功！')</script>");
				return listBlog();
			}
		}
		else{
			return "login";
		}
	}
	
	public String delBlog(){
		if(getSession().get("username")!=null){
			int tag = bloSer.delBlog(id);
			if(tag == 0){
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除失败！')</script>");
				return listBlog();
			}
			else{
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除成功！')</script>");
				return listBlog();
			}
		}
		else{
			return "login";
		}
	}
	
	public String listPhoto(){
		if(getSession().get("username")!=null){
			pager = new Pager(ptoSer.getPhotoList().size(),5);
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
			ActionContext.getContext().put("plist", ptoSer.getPhotoListByPage(pager));
			return "listPhoto";
		}
		else{
			return "login";
		}
	}
	
	public String addPhoto(){
		if(getSession().get("username")!=null){
			//判断成功失败的tag
			Boolean tag = false;
			int entity_id = 0;
			String savePath ="1";
	
		    //得到工程保存图片的路径
	        @SuppressWarnings("deprecation")
			String root = ServletActionContext.getRequest().getRealPath("/photo");
		    @SuppressWarnings("deprecation")
	        String miniroot = ServletActionContext.getRequest().getRealPath("/miniphoto");
	        savePath = String.valueOf(Integer.parseInt(ptoSer.getPid())+1)+".jpg";
			photo.setPath(savePath);
		
	        //上传文件
	        InputStream is;
			try {
				is = new FileInputStream(file);
			    //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
		        File destFile = new File(root,savePath);
		        //把图片写入到上面设置的路径里
		        OutputStream os = new FileOutputStream(destFile);
		        byte[] buffer = new byte[400];
		        int length  = 0 ;
			    while((length = is.read(buffer))>0){
			        os.write(buffer, 0, length);
			    }
			    is.close();
			    os.close();
			    //生成footer缩略图
			    Thumbnails.of(root+"/"+savePath).size(67, 67).toFile(miniroot+"/foot/"+savePath);
			    //生成manage缩略图
			    Thumbnails.of(root+"/"+savePath).size(160, 80).toFile(miniroot+"/manage/"+savePath);
			    //生成list缩略图
			    Thumbnails.of(root+"/"+savePath).size(168, 170).toFile(miniroot+"/list/"+savePath);
			    
			} catch (FileNotFoundException e1) {
				return "addPhoto";
			}
	        catch (IOException e2) {
	        	return "addPhoto";
			}
			entity_id = ptoSer.addPhoto(photo);
			if(entity_id > 0){
				tag =true;
			}
			if(tag == false){
				File delfile=new File(root,savePath);
				File delfile1=new File(miniroot+"/foot",savePath);
				File delfile2=new File(miniroot+"/manage",savePath);
				File delfile3=new File(miniroot+"/list",savePath);
				if(delfile.exists()){  
		            delfile.delete();  
		        }
				if(delfile1.exists()){  
		            delfile1.delete();  
		        }
				if(delfile2.exists()){  
		            delfile2.delete();  
		        }
				if(delfile3.exists()){  
		            delfile3.delete();  
		        }
				return "addPhoto";
			}
			else{
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('插入成功！')</script>");
				return listPhoto();
			}
		}
		else{
			return "login";
		}
	}
	
	public String delPhoto(){
		if(getSession().get("username")!=null){
		    //得到工程保存图片的路径
	        @SuppressWarnings("deprecation")
			String root = ServletActionContext.getRequest().getRealPath("/photo");
		    @SuppressWarnings("deprecation")
	        String miniroot = ServletActionContext.getRequest().getRealPath("/miniphoto");
	        Photo dp = ptoSer.findPhoto(id);
			File delfile=new File(root,dp.getPath());
			File delfile1=new File(miniroot+"/foot",dp.getPath());
			File delfile2=new File(miniroot+"/manage",dp.getPath());
			File delfile3=new File(miniroot+"/list",dp.getPath());
			boolean tag1 =false;
			boolean mtag1 =false;
			boolean mtag2 =false;
			boolean mtag3 =false;
			if(delfile.exists()){  
	            tag1 = delfile.delete();  
	        }
			if(delfile1.exists()){  
	            mtag1 = delfile1.delete();  
	        }
			if(delfile2.exists()){  
	            mtag2 = delfile2.delete();  
	        }
			if(delfile3.exists()){  
	            mtag3 = delfile3.delete();  
	        }
			int tag2 = ptoSer.delPhoto(id);
			if(tag2 == 0 || tag1 == false || mtag1 == false || mtag2 == false|| mtag3 == false){
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除失败！')</script>");
				return listPhoto();
			}
			else{
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除成功！')</script>");
				return listPhoto();
			}
		}
		else{
			return "login";
		}
	}
	
	public String updAlbum(){
		if(getSession().get("username")!=null){
	        @SuppressWarnings("deprecation")
			String root = ServletActionContext.getRequest().getRealPath("/images");
	        album.setA_id(1);
	        album.setPath("nf.mp3");
	        int tag1 = albSer.updateAlbum(album);
	        boolean tag2 = false;
	        boolean tag3 = false;
	        if(tag1 > 0){
	            File delfile=new File(root,"nf.mp3");
	            if(delfile.exists()){
	            	tag2 = delfile.delete();
	            }
	            //上传文件
	            InputStream is;
	    		try {
	    			is = new FileInputStream(file);
	    		    //得到mp3保存的位置(根据root来得到mp3保存的路径在tomcat下的该工程里)
	    	        File destFile = new File(root,"nf.mp3");
	    	        //把mp3写入到上面设置的路径里
	    	        OutputStream os = new FileOutputStream(destFile);
	    	        byte[] buffer = new byte[400];
	    	        int length  = 0 ;
	    		    while((length = is.read(buffer))>0){
	    		        os.write(buffer, 0, length);
	    		    }
	    		    is.close();
	    		    os.close();
	    		    tag3 = true ;
	    		} catch (FileNotFoundException e1) {
	    			ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('更新失败！')</script>");
	    			return showAlbum();
	    		}
	            catch (IOException e2) {
	            	ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('更新失败！')</script>");
	            	return showAlbum();    		
	            }
	        }
	        if(tag1<=0||tag2==false||tag3==false){
	        	ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('更新失败！')</script>");
	        	return showAlbum();
	        }
	        else{
	        	ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('更新成功！')</script>");
	        	album = albSer.findAlbum(1);
	        	return showAlbum();
	        }
		}
		else{
			return "login";
		}
	}
	
	public String showAlbum(){
		if(getSession().get("username")!=null){
			album = albSer.findAlbum(1);
			return "showAlbum";
		}
		else{
			return "login";
		}
	}
	
	public String listMsg(){
		if(getSession().get("username")!=null){
			pager = new Pager(msgSer.getMsg().size(),15);
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
			ActionContext.getContext().put("mlist", msgSer.getMsgListByPage(pager));
			return "listMsg";
		}
		else{
			return "login";
		}
	}
	
	public String delMsg(){
		if(getSession().get("username")!=null){
			int tag = msgSer.delMsg(id);
			if(tag == 0){
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除失败！')</script>");
				return listMsg();
			}
			else{
				ActionContext.getContext().put("say", "<script type='text/javascript'>window.alert('删除成功！')</script>");
				return listMsg();
			}
		}
		else{
			return "login";
		}
	}
	
	public String delMsgList(){
		if(getSession().get("username")!=null){
			int tag = msgSer.delMsgList(idList);
			if(tag == 0){
				say = "批量删除失败！";
				return "del_json";
			}
			else{
				say = "批量删除成功！";
				return "del_json";
			}
		}
		else{
			return "login";
		}
	}

	public String delBlogList(){
		if(getSession().get("username")!=null){
			int tag = bloSer.delBlogList(idList);
			if(tag == 0){
				say = "批量删除失败！";
				return "del_json";
			}
			else{
				say = "批量删除成功！";
				return "del_json";
			}
		}
		else{
			return "login";
		}
	}
}
