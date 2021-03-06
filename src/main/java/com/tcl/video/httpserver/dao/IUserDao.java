package com.tcl.video.httpserver.dao;

import com.tcl.video.httpserver.bean.VideoBean;

import cn.lenya.soft.core.rs.ListRs;

public interface IUserDao {

	/**
	 * 功能描述：更具用户id得到用户下所有上传视频
	 * @param sql
	 * @param args
	 * @return
	 */
	ListRs<VideoBean> queryUploadOfUsersVideoByUserId(String sql, Object[] args);
	
	Boolean delUserVideos(String sql,Object agrs[]);
}
