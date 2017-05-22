package com.faith.tools;


/**
 * faith_pager  分页类
 * @version 1.0	
 * @author faithfish  2016.04.15
 *
 */
public class Pager {

private int m;//当前光标位置
private int n;//返回记录数
private int currentPage;//当前页数
private int total;//总页数

public int getCurrentPage() {
	return currentPage;
}

public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}

public int getTotal() {
	return total;
}

public void setTotal(int total) {
	this.total = total;
}

public int getM() {
	return m;
}

public void setM(int m){
	this.m = m;
}

public int getN() {
	return n;
}

public void setN(int n){
	this.n = n;
}

public Pager(int recordsNum,int _n){ 
	this.m = 0;
	this.n = _n;
	if(recordsNum > 0) this.currentPage = 1;
	else this.currentPage = 0;
	this.total = (int) Math.ceil((double)recordsNum/(double)n);
}

//上一页
public void before(){
	if(currentPage <= 1||currentPage > total){}
	else {
		currentPage = currentPage-1;
		m = m-n;
	}
}

//下一页
public void next(){
	if(currentPage+1 >total||currentPage < 1){}
	else {
		currentPage = currentPage+1;
		m = m+n;
	}
}

//跳页
public void jump(int k){
	if(k<1||k>total){}
	else {
		currentPage = k;
		m = (k-1)*n;
	}
}
	
}
