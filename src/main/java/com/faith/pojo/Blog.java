package com.faith.pojo;

import java.util.Date;

public class Blog {
private int b_id;//博客id
private String title;//标题
private String content;//内容
private Date createTime;//创建时间
private Date alterTime;//最近修改时间
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getB_id() {
	return b_id;
}
public void setB_id(int b_id) {
	this.b_id = b_id;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public Date getAlterTime() {
	return alterTime;
}
public void setAlterTime(Date alterTime) {
	this.alterTime = alterTime;
}
}
