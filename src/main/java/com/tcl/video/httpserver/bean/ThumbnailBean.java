package com.tcl.video.httpserver.bean;

import java.io.Serializable;

public class ThumbnailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String videoId;
	private Integer height;
	private Integer width;
	private String imgResourceUrl;//服务器保存路径
	private String imgDomain;//域名
	private String imgLinkUrl;//httpUrl
	private String resloutionType;//分辨率类型
	private String size;
	private String notes;//备注
	
	
	public ThumbnailBean(){}
	
	public ThumbnailBean( Integer height,Integer width, String resourceUrl,String linkUrl, 
			String size, String notes,String domain
			){
		this.height=height;
		this.width=width;
		this.imgResourceUrl=resourceUrl;
		this.imgLinkUrl=linkUrl;
		this.size=size;
		this.notes=notes;	
		this.imgDomain=domain;
	}
	
	

	public String getResloutionType() {
		return resloutionType;
	}

	public void setResloutionType(String resloutionType) {
		this.resloutionType = resloutionType;
	}

	public String getImgDomain() {
		return imgDomain;
	}

	public void setImgDomain(String imgDomain) {
		this.imgDomain = imgDomain;
	}

	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	
	public String getImgResourceUrl() {
		return imgResourceUrl;
	}

	public void setImgResourceUrl(String imgResourceUrl) {
		this.imgResourceUrl = imgResourceUrl;
	}

	public String getImgLinkUrl() {
		return imgLinkUrl;
	}

	public void setImgLinkUrl(String imgLinkUrl) {
		this.imgLinkUrl = imgLinkUrl;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
