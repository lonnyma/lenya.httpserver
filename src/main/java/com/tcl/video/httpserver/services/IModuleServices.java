package com.tcl.video.httpserver.services;

import com.tcl.video.httpserver.bean.ModuleBean;

import cn.lenya.soft.core.rs.ListRs;

public interface IModuleServices {

	
	ListRs<ModuleBean> getModuleList();	
}
