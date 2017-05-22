package com.faith.pojo;

public class Photo {
private int p_id;
private String name;//图片名称
private String describe;//图片描述
private String path;//图片路径
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public int getP_id() {
	return p_id;
}
public void setP_id(int p_id) {
	this.p_id = p_id;
}
}
