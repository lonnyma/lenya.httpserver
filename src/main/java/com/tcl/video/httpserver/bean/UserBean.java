package com.tcl.video.httpserver.bean;

import cn.lenya.soft.core.bean.BaseUserBean;

public class UserBean extends BaseUserBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rowId; //����
	private String sex;  //�Ա�
	private String birthDate; //��������
	private String identifier;//���
	
	private UserContactsBean userContactsBean;
	
		
	public UserContactsBean getUserContactsBean() {
		return userContactsBean;
	}
	public void setUserContactsBean(UserContactsBean userContactsBean) {
		this.userContactsBean = userContactsBean;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	
}
