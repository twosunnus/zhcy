package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//角色用户关联表
@Entity
@Table(name = "allotinfo", catalog = "zhcy")
public class AllotInfo {
	private int ai_id;
	private int ji_id;
	private int mi_id;
	
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "ai_id", unique = true, nullable = false)
	public int getAi_id() {
		return ai_id;
	}
	public void setAi_id(int ai_id) {
		this.ai_id = ai_id;
	}
	@Column(name = "ji_id",length=50)
	public int getJi_id() {
		return ji_id;
	}
	public void setJi_id(int ji_id) {
		this.ji_id = ji_id;
	}
	@Column(name = "mi_id",length=50)
	public int getMi_id() {
		return mi_id;
	}
	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}
	
}
