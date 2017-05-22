package com.faith.pojo;

import java.util.Date;

public class Message {
private int m_id;
private String name;
private String detail;
private Date createTime;
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public int getM_id() {
	return m_id;
}
public void setM_id(int m_id) {
	this.m_id = m_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
}
