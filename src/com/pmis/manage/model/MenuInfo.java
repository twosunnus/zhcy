package com.pmis.manage.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//菜单表
@Entity
@Table(name = "menuinfo", catalog = "zhcy")
public class MenuInfo {
	private int mi_id;
	private String mi_name;
	private int mi_parentid;
	private String mi_url;
	private int mi_order;
	
	private String checked;
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "mi_id", unique = true, nullable = false)
	public int getMi_id() {
		return mi_id;
	}
	public void setMi_id(int mi_id) {
		this.mi_id = mi_id; 
	}
	@Column(name = "mi_name",length=50)
	public String getMi_name() {
		return mi_name;
	}
	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}
	@Column(name = "mi_parentid",length=200)
	public int getMi_parentid() {
		return mi_parentid;
	}
	public void setMi_parentid(int mi_parentid) {
		this.mi_parentid = mi_parentid;
	}
	@Column(name = "mi_url",length=200)
	public String getMi_url() {
		return mi_url;
	}
	public void setMi_url(String mi_url) {
		this.mi_url = mi_url;
	}
	@Column(name = "mi_order",length=20)
	public int getMi_order() {
		return mi_order;
	}
	public void setMi_order(int mi_order) {
		this.mi_order = mi_order;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
	
}
