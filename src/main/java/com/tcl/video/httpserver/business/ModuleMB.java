package com.tcl.video.httpserver.business;

import com.tcl.video.httpserver.bean.ModuleBean;
import com.tcl.video.httpserver.dao.IModuleDao;

import cn.lenya.soft.core.rs.ListRs;

public class ModuleMB {

	private IModuleDao moduleDao;

	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}
	
	/**
	 * Ft: 获取栏目列表
	 * @param sql
	 * @param args
	 * @return
	 */
	public ListRs<ModuleBean> getModuleList(){
		
		String sql ="select * from t_module";
		Object args[] = null;
		return moduleDao.getModuleList(sql, args);
	}
	
	
}
