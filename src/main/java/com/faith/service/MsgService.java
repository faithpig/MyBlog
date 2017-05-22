package com.faith.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.faith.dao.MsgDao;
import com.faith.pojo.Message;
import com.faith.tools.Pager;

public class MsgService {
	
	private MsgDao dao = new MsgDao();
	
	public int addMsg(Message m){
		String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql = "insert into message(name,detail,createtime) values('"+m.getName()+"','"+m.getDetail()+"','"+cTime+"');";
		return dao.runUpdate(sql);
	}
	
	public List<Message> getMsg(){
		String sql ="select * from message order by m_id desc;";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Message>();
		}
	}
	
	//分页查询留言
	public List<Message> getMsgListByPage(Pager pager){
		String sql =" select * from (select * from message order by m_id desc) as message limit "+pager.getM()+","+pager.getN()+";";
		if(!dao.runSelect(sql).isEmpty()){
			return dao.runSelect(sql);
		}
		else{
			return new ArrayList<Message>();
		} 
	}
	
	public int delMsg(int id){
		String sql = "delete from message where m_id = "+id+";";
		return dao.runUpdate(sql);
	}
	
	public int delMsgList(ArrayList<Integer> idList){
		int flag = 1;
		for(int i=0;i<idList.size();i++){
			if(delMsg(idList.get(i))<=0) flag=0;
		}
		return flag;
	}
}
