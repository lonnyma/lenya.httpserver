package com.tcl.video.httpserver.bean;

import java.io.Serializable;

public class VideoBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String videoId;
	private String videoTitle;
	private String videoFileName;
	private String videoFileType;
	private String resourceUrl; //磁盘目录
	private String domain;//域名
	private String transcodingUrl;//转码后路径
	private String linkUrl;//播放路径
	private String playerType;//播放器类型
	private String playPage;//播放页面
	private String videoSize;//视频大小
	private String duration;//时长
	private String status;
	private String userId;
	private String uploadTime;
	private String uploadIP;
	private String releaseTime;
	private String publishedTime;
	private String keyWord;
	private String categoryId;//分类id
	private String introduce;//简介
	private String copyRight;//版权
	private String perimit;//权限
	private String notes;//备注
	
	private ThumbnailBean thumbnailBean;
	private PlayInfoBean  playInfoBean;
	
	
		
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public PlayInfoBean getPlayInfoBean() {
		return playInfoBean;
	}
	public void setPlayInfoBean(PlayInfoBean playInfoBean) {
		this.playInfoBean = playInfoBean;
	}
	public ThumbnailBean getThumbnailBean() {
		return thumbnailBean;
	}
	public void setThumbnailBean(ThumbnailBean thumbnailBean) {
		this.thumbnailBean = thumbnailBean;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoFileName() {
		return videoFileName;
	}
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	public String getVideoFileType() {
		return videoFileType;
	}
	public void setVideoFileType(String videoFileType) {
		this.videoFileType = videoFileType;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getTranscodingUrl() {
		return transcodingUrl;
	}
	public void setTranscodingUrl(String transcodingUrl) {
		this.transcodingUrl = transcodingUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
		
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	public String getVideoSize() {
		return videoSize;
	}
	public void setVideoSize(String videoSize) {
		this.videoSize = videoSize;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadIP() {
		return uploadIP;
	}
	public void setUploadIP(String uploadIP) {
		this.uploadIP = uploadIP;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getPerimit() {
		return perimit;
	}
	public void setPerimit(String perimit) {
		this.perimit = perimit;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getPlayPage() {
		return playPage;
	}
	public void setPlayPage(String playPage) {
		this.playPage = playPage;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(String publishedTime) {
		this.publishedTime = publishedTime;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String toString()
	{
		// 这里主要是拼接字符串，便于javascript的解析，单条记录的格式为：
		// filename,domain,videolinkURl,timelength,imgdoamin,imgurl;
		return videoFileName + "," + domain + "," + linkUrl + "," + duration + "," 
				+ thumbnailBean.getImgDomain() + "," + thumbnailBean.getImgLinkUrl() + ","
				+ videoId + ";";
	}
	
	
}
