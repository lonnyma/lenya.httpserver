package com.tcl.video.httpserver.services.impl;

import com.tcl.video.httpserver.bean.ModuleBean;
import com.tcl.video.httpserver.business.ModuleMB;
import com.tcl.video.httpserver.services.IModuleServices;

import cn.lenya.soft.core.rs.ListRs;

public class ModuleServicesImpl implements IModuleServices{

	private static ModuleMB moduleMB;
	
	public void setModuleMB(ModuleMB moduleMB) {
		ModuleServicesImpl.moduleMB = moduleMB;
	}

	public ListRs<ModuleBean> getModuleList() {
		return moduleMB.getModuleList();
	}

}
