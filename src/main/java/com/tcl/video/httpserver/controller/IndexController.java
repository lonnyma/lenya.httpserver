package com.tcl.video.httpserver.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.services.IModuleServices;
import com.tcl.video.httpserver.services.IVideoServices;
import com.tcl.video.httpserver.util.JsonUtil;

@Controller
public class IndexController {

	@Resource(name = "videoServices")
	private IVideoServices videoServices;

	@Resource(name = "moduleServices")
	private IModuleServices moduleServices;

	@RequestMapping("/index")
	@ResponseBody
	public String index(HttpServletRequest request, HttpSession session) {

		System.out.println("   **** httpserver  ****");

		// 获取栏目列表
		// ListRs<ModuleBean> moduleList= moduleServices.getModuleList();

		ServletContext application = request.getServletContext();

		VideoBean vb = videoServices.getVideoBeanById("1a072fd1f0bc4057a56ad741cbfd16e7");

		application.setAttribute("vb", vb);

		// JsonUtil jsonUtil=
		String jsonStr = JsonUtil.toJson(vb);

		return jsonStr;
	}

}
