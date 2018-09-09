package com.tcl.video.httpserver.dao;

public interface IUploadDao {
	boolean saveOrUpdate(String sql,Object[] args);
}
