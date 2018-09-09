package com.tcl.video.httpserver.services;

import com.tcl.video.httpserver.bean.VideoBean;

public interface IUploadServices {

	
	Boolean saveVideoToDB(VideoBean vBean);
	Object saveVideo2DB(VideoBean vBean);
}
