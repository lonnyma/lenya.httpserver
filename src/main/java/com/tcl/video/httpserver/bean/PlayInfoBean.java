package com.tcl.video.httpserver.bean;

public class PlayInfoBean {

	private String videoId;
	private Integer playSum;//播放总数
	private Integer commentSum;
	private Integer praiseSum;//赞
	private Integer subscribeSum;//关注人数
	private Integer collectionSum;//收藏
	private Integer downloadSun;
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public Integer getPlaySum() {
		return playSum;
	}
	public void setPlaySum(Integer playSum) {
		this.playSum = playSum;
	}
	public Integer getCommentSum() {
		return commentSum;
	}
	public void setCommentSum(Integer commentSum) {
		this.commentSum = commentSum;
	}
	public Integer getPraiseSum() {
		return praiseSum;
	}
	public void setPraiseSum(Integer praiseSum) {
		this.praiseSum = praiseSum;
	}
	public Integer getSubscribeSum() {
		return subscribeSum;
	}
	public void setSubscribeSum(Integer subscribeSum) {
		this.subscribeSum = subscribeSum;
	}
	public Integer getCollectionSum() {
		return collectionSum;
	}
	public void setCollectionSum(Integer collectionSum) {
		this.collectionSum = collectionSum;
	}
	public Integer getDownloadSun() {
		return downloadSun;
	}
	public void setDownloadSun(Integer downloadSun) {
		this.downloadSun = downloadSun;
	}
	
	
	
	
}
