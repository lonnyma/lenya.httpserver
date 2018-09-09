package com.tcl.video.httpserver.services;

import java.util.List;

import com.tcl.video.httpserver.bean.VideoBean;

public interface IUserServices {

	List<VideoBean> getUsersOfUploadVideoListByUserId(Integer userId);
	Boolean delUserVideos(String[] v_ids);
}
