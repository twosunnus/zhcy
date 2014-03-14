package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//菜品类别表
@Entity
@Table(name = "dishestypeinfo", catalog = "zhcy")
public class DishesTypeInfo {
	//id
	private int dti_id;
	//用户id
	private int ui_id;
	//菜品类别名称
	private String dti_name;
	
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "dti_id", unique = true, nullable = false)
	public int getDti_id() {
		return dti_id;
	}
	public void setDti_id(int dti_id) {
		this.dti_id = dti_id;
	}
	@Column(name = "ui_id",length=11)
	public int getUi_id() {
		return ui_id;
	}
	public void setUi_id(int ui_id) {
		this.ui_id = ui_id;
	}
	@Column(name = "dti_name",length=40)
	public String getDti_name() {
		return dti_name;
	}
	public void setDti_name(String dti_name) {
		this.dti_name = dti_name;
	}
	
	
}
