package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 用户表
 */
@Entity
@Table(name = "userinfo", catalog = "zhcy")
public class UserInfo {
	private int ui_id;
	//用户名
	private String ui_username;
	//md5加密
	private String ui_password;
	//base64加密
	private String ui_basepassword;
	//餐厅服务号
	private String ui_restaurantno;
	//角色id
	private String ui_ji_id;
	
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "ui_id", unique = true, nullable = false)
	public int getUi_id() {
		return ui_id;
	}

	public void setUi_id(int ui_id) {
		this.ui_id = ui_id;
	}
	
	@Column(name = "ui_username",length=50)
	public String getUi_username() {
		return ui_username;
	}

	public void setUi_username(String uiUsername) {
		ui_username = uiUsername;
	}
	@Column(name = "ui_password",length=50)
	public String getUi_password() {
		return ui_password;
	}

	public void setUi_password(String uiPassword) {
		ui_password = uiPassword;
	}
	
	
	@Column(name = "ui_basepassword",length=50)
	public String getUi_basepassword() {
		return ui_basepassword;
	}

	public void setUi_basepassword(String ui_basepassword) {
		this.ui_basepassword = ui_basepassword;
	}

	@Column(name = "ui_ji_id",length=50)
	public String getUi_ji_id() {
		return ui_ji_id;
	}

	public void setUi_ji_id(String ui_ji_id) {
		this.ui_ji_id = ui_ji_id;
	}
	@Column(name = "ui_restaurantno",length=50)
	public String getUi_restaurantno() {
		return ui_restaurantno;
	}
	
	public void setUi_restaurantno(String ui_restaurantno) {
		this.ui_restaurantno = ui_restaurantno;
	}

	
	
}
