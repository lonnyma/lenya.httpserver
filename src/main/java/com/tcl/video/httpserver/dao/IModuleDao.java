package com.tcl.video.httpserver.dao;


import com.tcl.video.httpserver.bean.ModuleBean;

import cn.lenya.soft.core.rs.ListRs;

public interface IModuleDao {

	public ListRs<ModuleBean> getModuleList(String sql,Object args[]);
}
