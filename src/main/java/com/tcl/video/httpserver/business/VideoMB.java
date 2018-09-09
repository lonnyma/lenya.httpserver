package com.tcl.video.httpserver.business;

import java.util.HashMap;
import java.util.Map;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.dao.IVideoDao;

import cn.lenya.soft.core.rs.ListRs;

public class VideoMB {

	private IVideoDao videoDao;
		
	public void setVideoDao(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}


/**
 * 功能描述：首页初始化数据
 * @return
 */
	public Map<String, VideoBean> init(){		
		
		Map<String, VideoBean> rs = new HashMap<String, VideoBean>();
		try{
			Object [] args= new Object[]{
					
					
			};
			
			String sql = "SELECT * FROM `v_video_info` ORDER BY  uploadTime DESC LIMIT 1";
		
			VideoBean vb =	videoDao.getVideoBySql(sql, args).getObject();
			
			rs.put("homeFRFD", vb);//FirstRowFirsData
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rs;
		
	}
	
	public VideoBean getVideoBeanById(String id)
	{
		if (id == null)
		{
			return null;
		}
		
		Object[] args = new Object[]{
				id
		};
		
		String sql = "SELECT * FROM `v_video_info` WHERE videoId=?";
		VideoBean vb =	videoDao.getVideoBySql(sql, args).getObject();
		
		return vb;
	}
	
	public ListRs<VideoBean> getListVideoBean(int li)
	{
		
		Object[] args = new Object[]{
				li
		};
		String sql = "SELECT * FROM `v_video_info` ORDER BY uploadTime DESC LIMIT ?";
		ListRs<VideoBean> vb = videoDao.getListVideoBean(sql, args);
		
		return vb; 
	}
}
