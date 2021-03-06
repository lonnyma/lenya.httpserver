package com.tcl.video.httpserver.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import cn.lenya.soft.db.dao.impl.BaseDao;

import com.tcl.video.httpserver.dao.IUploadDao;


public class UploadDaoImpl extends BaseDao implements IUploadDao {

	
	
	private JdbcTemplate ds;
	public void setDs(DataSource ds) {
		this.ds = new JdbcTemplate(ds);
	}
	
	@Transactional(rollbackFor=RuntimeException.class) 
	public boolean saveOrUpdate(String sql, Object[] args) {
		//super.setDs(ds);
		return super.saveOrUpdate(sql, args);
	
	}

}
