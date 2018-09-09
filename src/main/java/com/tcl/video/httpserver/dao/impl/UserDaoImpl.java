package com.tcl.video.httpserver.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.lenya.soft.core.rs.ListRs;
import cn.lenya.soft.db.dao.impl.BaseDao;
import cn.lenya.soft.db.dao.impl.BaseDaoHelper;

import com.tcl.video.httpserver.bean.ThumbnailBean;
import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.dao.IUserDao;

public class UserDaoImpl extends BaseDao implements IUserDao {
	private static final Logger Log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private JdbcTemplate ds;
	public void setDs(DataSource ds) {
		this.ds = new JdbcTemplate(ds);
	}
	
	
	public ListRs<VideoBean> queryUploadOfUsersVideoByUserId(String sql, Object[] args){
		
		ListRs<VideoBean> rs = null;
		
		try
		{
			rs = new ListRs<VideoBean>();
			
			List<Map<String,Object>> rslist = ds.queryForList(sql, args);
			
			CountDownLatch runningThreadNum = new CountDownLatch(2);
	        
	        BaseDaoHelper<VideoBean> bdh_1 = new BaseDaoHelper<VideoBean>(runningThreadNum,rslist, VideoBean.class, null);
	        new Thread(bdh_1).start();
	  
	        BaseDaoHelper<ThumbnailBean> bdh_2 = new BaseDaoHelper<ThumbnailBean>(runningThreadNum,rslist, ThumbnailBean.class, null);
	        new Thread(bdh_2).start();
	        
	        runningThreadNum.await();
	        
	        List<VideoBean> result= (List<VideoBean>) bdh_1.getResult();
		    List<ThumbnailBean> thumbnails = (List<ThumbnailBean>) bdh_2.getResult();
		    
		    for(int i =0 ;i<result.size();i++){
				for(ThumbnailBean tb : thumbnails){
					if(result.get(i).getVideoId().equals(tb.getVideoId())){
						result.get(i).setThumbnailBean(tb);
						break;
					}
				}
			}
		    rs.setRsCount(result.size());
		    rs.setResultList(result);
		    rs.setRsSql(sql);
			rs.setRsMsg("succ");
		}catch(Exception e)
		{
			Log.info("查询用户下的视频失败！"+e.getMessage());
			rs.setRsMsg("failed");
			e.printStackTrace();
			return rs;	
		}
		return rs;
	}


	public Boolean delUserVideos(String sql, Object[] args) {		
		//super.setDs(ds);
		return super.saveOrUpdate(sql, args);
	}
	
	
	

}


