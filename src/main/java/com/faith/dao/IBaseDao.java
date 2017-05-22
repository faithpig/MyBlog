package com.faith.dao;

import java.util.List;

public interface IBaseDao<T>{
	public List<T> runSelect(String sql);
	public int runUpdate(String sql);
	public int runAdd(String sql);
}
