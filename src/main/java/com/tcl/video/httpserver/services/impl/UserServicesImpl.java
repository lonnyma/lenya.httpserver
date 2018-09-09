package com.tcl.video.httpserver.services.impl;

import java.util.List;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.business.UserMB;
import com.tcl.video.httpserver.services.IUserServices;

public class UserServicesImpl implements IUserServices {

private static UserMB userMB;	
	
	public static void setUserMB(UserMB userMB) {
		UserServicesImpl.userMB = userMB;
	}

	public List<VideoBean> getUsersOfUploadVideoListByUserId(Integer userId) {
		return userMB.getUsersOfUploadVideoListByUserId(userId);
	}

	public Boolean delUserVideos(String[] v_ids) {
		return userMB.delUserVideos(v_ids);
	}
	
	
	
	
	
}
