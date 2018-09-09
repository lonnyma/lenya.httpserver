package com.tcl.video.httpserver.business;

import java.util.List;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.dao.IUserDao;

public class UserMB {

	
	private IUserDao userDao;
		
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}



	/**
	 * 功能描述：根据用户id 查询用户下的所有视频
	 * @param userId
	 * @return
	 */
	
	public List<VideoBean> getUsersOfUploadVideoListByUserId(Integer userId)
	{		
		Object[] args = new Object[]{
				userId==null?"-1":userId
		};
		String sql = "SELECT * FROM `v_video_info`  where userId = ? ORDER BY uploadTime DESC";		
		return userDao.queryUploadOfUsersVideoByUserId(sql, args).getResultList(); 
	}
	
	/**
	 * 功能描述：删除用户选择的视频
	 * @return
	 */
	public boolean delUserVideos(String[] v_ids){
		String videosId = tansVidsToString(v_ids);
		String sql ="delete from `t_thumbnail` where `videoId` in ("+videosId+")";
		boolean rs = userDao.delUserVideos(sql, null);
		sql = null;
	if(rs){
		sql = "delete from `t_videos`  where `videoId` in ("+videosId+")";
		rs = userDao.delUserVideos(sql, null);
	}
	
	return rs;
	
	}
	
	
	private String tansVidsToString(String[] v_ids){
		
		if (null == v_ids ){
			return null;
		}else if(1==v_ids.length){
			return "'"+v_ids[0]+"'";			
		}else{
		
		StringBuffer temp =new StringBuffer("");
		for(int i =0;i<v_ids.length;i++){
			temp.append("'"+v_ids[i]+"',");
		}
		temp.deleteCharAt(temp.length()-1);
		return temp.toString();
		}
				
	}
}
