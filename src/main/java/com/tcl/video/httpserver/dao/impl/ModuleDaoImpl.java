package com.tcl.video.httpserver.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.lenya.soft.core.rs.ListRs;
import cn.lenya.soft.db.dao.impl.BaseDao;

import com.tcl.video.httpserver.bean.ModuleBean;
import com.tcl.video.httpserver.dao.IModuleDao;

public class ModuleDaoImpl extends BaseDao 
							implements IModuleDao{
	
		private static final Logger Log = LoggerFactory.getLogger(ModuleDaoImpl.class);
		
		private JdbcTemplate ds;
		public void setDs(DataSource ds) {
			this.ds = new JdbcTemplate(ds);
		}
	
	public ListRs<ModuleBean> getModuleList(String sql,Object args[]) {
		ListRs<ModuleBean> rs = null;
		
		try
		{
			rs = new ListRs<ModuleBean>();
			
			List<Map<String,Object>> rslist = ds.queryForList(sql, args);			
			
		    rs.setRsCount(rslist.size());
		    rs.setResultList(getList(rslist, ModuleBean.class, null));
		    rs.setRsSql(sql);
			rs.setRsMsg("succ");
			
		}catch(Exception e)
		{
			Log.info("获取栏目列表失败！"+e.getMessage());
			rs.setRsMsg("failed");
			e.printStackTrace();
			return rs;	
		}
		return rs;
	}

}
