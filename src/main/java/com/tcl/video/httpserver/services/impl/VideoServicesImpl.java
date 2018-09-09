package com.tcl.video.httpserver.services.impl;


import java.util.Map;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.business.VideoMB;
import com.tcl.video.httpserver.services.IVideoServices;

import cn.lenya.soft.core.rs.ListRs;

public class VideoServicesImpl implements IVideoServices {

	private static VideoMB videoMB;	
	
	public static void setVideoMB(VideoMB videoMB) {
		VideoServicesImpl.videoMB = videoMB;
	}

	public Map<String, VideoBean> init() {
		
		
		return videoMB.init();
	}

	public VideoBean getVideoBeanById(String v_id)
	{
		
		return videoMB.getVideoBeanById(v_id);
	}
	
	public ListRs<VideoBean> getListVideoBean(int li)
	{
		return videoMB.getListVideoBean(li);
	}
}
