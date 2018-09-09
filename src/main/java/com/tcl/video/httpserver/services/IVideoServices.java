package com.tcl.video.httpserver.services;

import java.util.Map;

import com.tcl.video.httpserver.bean.VideoBean;

import cn.lenya.soft.core.rs.ListRs;

public interface IVideoServices {

	/**
	 * 功能描述：初始化加载数据
	 * @return
	 */
	Map<String, VideoBean> init();
	VideoBean getVideoBeanById(String v_id);
	ListRs<VideoBean> getListVideoBean(int li);
}
