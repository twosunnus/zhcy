package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//角色表
@Entity
@Table(name = "jobinfo", catalog = "zhcy")
public class JobInfo {
	private int ji_id;
	private String ji_name;

	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "ji_id", unique = true, nullable = false)
	public int getJi_id() {
		return ji_id;
	}
	public void setJi_id(int ji_id) {
		this.ji_id = ji_id;
	}
	@Column(name = "ji_name",length=50)
	public String getJi_name() {
		return ji_name;
	}
	public void setJi_name(String ji_name) {
		this.ji_name = ji_name;
	}
	
	
}
