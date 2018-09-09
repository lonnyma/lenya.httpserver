package com.tcl.video.httpserver.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tcl.video.httpserver.bean.ThumbnailBean;
import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.dao.IVideoDao;

import cn.lenya.soft.core.rs.ListRs;
import cn.lenya.soft.core.rs.ObjectRs;
import cn.lenya.soft.db.dao.impl.BaseDao;
import cn.lenya.soft.db.dao.impl.BaseDaoHelper;

public class VideoDaoImpl extends BaseDao implements IVideoDao {

	private static final Logger Log = LoggerFactory.getLogger(VideoDaoImpl.class);

	private JdbcTemplate ds;

	public void setDs(DataSource ds) {
		this.ds = new JdbcTemplate(ds);
	}

	public ObjectRs<VideoBean> getVideoBySql(String sql, Object[] args) {

		ObjectRs<VideoBean> rs = null;
		try {
			rs = new ObjectRs<VideoBean>();
			Map<String, Object> orm = new HashMap<String, Object>();
			// orm.put("userName", "user_name");
			// orm.put("password", "user_pass");
			// orm.put("map", orm);
			// VideoBean object = getObject(ds.queryForMap(sql, args),VideoBean.class,orm);

			long a = System.currentTimeMillis();
			List<Map<String, Object>> rslist = ds.queryForList(sql, args);
			System.out.println("jdbc:" + (System.currentTimeMillis() - a));

			// 定义正在运行的线程数
			CountDownLatch runningThreadNum = new CountDownLatch(2);

			BaseDaoHelper<VideoBean> bdh_1 = new BaseDaoHelper<VideoBean>(runningThreadNum, rslist, VideoBean.class,
					null);
			new Thread(bdh_1).start();

			BaseDaoHelper<ThumbnailBean> bdh_2 = new BaseDaoHelper<ThumbnailBean>(runningThreadNum, rslist,
					ThumbnailBean.class, null);
			new Thread(bdh_2).start();

			runningThreadNum.await();

			Log.debug("两现成组装对象完成，所花时间：" + (System.currentTimeMillis() - a));

			List<VideoBean> result = (List<VideoBean>) bdh_1.getResult();
			List<ThumbnailBean> thumbnails = (List<ThumbnailBean>) bdh_2.getResult();

			a = System.currentTimeMillis();
			for (int i = 0; i < result.size(); i++) {
				for (ThumbnailBean tb : thumbnails) {
					if (result.get(i).getVideoId().equals(tb.getVideoId())) {
						result.get(i).setThumbnailBean(tb);
						break;
					}
				}
			}
			System.out.println("拼装：" + (System.currentTimeMillis() - a));

			// 当前记录数
			rs.setRsCount(result.size());
			rs.setObject(result.get(0) == null ? null : result.get(0));
			rs.setRsSql(sql);
			rs.setRsMsg("succ");
		} catch (Exception e) {
			rs.setRsMsg("failed");
			e.printStackTrace();
			return rs;
		}
		return rs;
	}

	public VideoBean getVideoBySql2(String sql, Object[] args) {
		return (VideoBean) ds.queryForMap(sql, args);
	}

	public ListRs<VideoBean> getListVideoBean(String sql, Object[] args) {
		ListRs<VideoBean> rs = null;

		try {
			rs = new ListRs<VideoBean>();
			List<Map<String, Object>> rslist = ds.queryForList(sql, args);

			CountDownLatch runningThreadNum = new CountDownLatch(2);

			BaseDaoHelper<VideoBean> bdh_1 = new BaseDaoHelper<VideoBean>(runningThreadNum, rslist, VideoBean.class,
					null);
			new Thread(bdh_1).start();

			BaseDaoHelper<ThumbnailBean> bdh_2 = new BaseDaoHelper<ThumbnailBean>(runningThreadNum, rslist,
					ThumbnailBean.class, null);
			new Thread(bdh_2).start();

			runningThreadNum.await();

			List<VideoBean> result = (List<VideoBean>) bdh_1.getResult();
			List<ThumbnailBean> thumbnails = (List<ThumbnailBean>) bdh_2.getResult();

			for (int i = 0; i < result.size(); i++) {
				for (ThumbnailBean tb : thumbnails) {
					if (result.get(i).getVideoId().equals(tb.getVideoId())) {
						result.get(i).setThumbnailBean(tb);
						break;
					}
				}
			}
			rs.setRsCount(result.size());
			rs.setResultList(result);
			rs.setRsSql(sql);
			rs.setRsMsg("succ");
		} catch (Exception e) {
			rs.setRsMsg("failed");
			e.printStackTrace();
			return rs;
		}
		return rs;
	}

}
