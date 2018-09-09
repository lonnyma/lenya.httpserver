package com.tcl.video.httpserver.dao;

import com.tcl.video.httpserver.bean.VideoBean;

import cn.lenya.soft.core.rs.ListRs;
import cn.lenya.soft.core.rs.ObjectRs;

public interface IVideoDao {

	ObjectRs<VideoBean> getVideoBySql(String sql,Object[] args);
	
	VideoBean getVideoBySql2(String sql,Object[] args);


	ListRs<VideoBean> getListVideoBean(String sql, Object[] args);

}
