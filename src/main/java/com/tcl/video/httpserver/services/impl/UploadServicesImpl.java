package com.tcl.video.httpserver.services.impl;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.business.UploadMB;
import com.tcl.video.httpserver.services.IUploadServices;

public class UploadServicesImpl implements IUploadServices {

	private static UploadMB uploadMB;
	
	public Boolean saveVideoToDB(VideoBean vBean) {
		return uploadMB.saveAndUpdate(vBean).getResult();
//		try {
//			return uploadMB.saveAndUpdate(vBean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	
	
	
	public Object saveVideo2DB(VideoBean vBean) {		
		return uploadMB.saveAndUpdate(vBean);
	}








	public static void setUploadMB(UploadMB uploadMB) {
		UploadServicesImpl.uploadMB = uploadMB;
	}


	
}

