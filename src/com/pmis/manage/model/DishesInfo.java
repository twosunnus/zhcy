package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//菜品表
@Entity
@Table(name = "dishesinfo", catalog = "zhcy")
public class DishesInfo {
	private int di_id;
	//id
	private int ui_id;
	//菜品名称
	private String di_name;
	//数量
	private int di_num;
	//状态0，下架。1，正常
	private int di_flag;
	//原价格
	private double di_lastprice;
	//现价格
	private double di_nowprice;
	
	//菜品类别（不映射到数据库）
	private String dti_name;
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "di_id", unique = true, nullable = false)
	public int getDi_id() {
		return di_id;
	}
	public void setDi_id(int di_id) {
		this.di_id = di_id;
	}
	@Column(name = "ui_id",length=11)
	public int getUi_id() {
		return ui_id;
	}
	public void setUi_id(int ui_id) {
		this.ui_id = ui_id;
	}
	@Column(name = "di_name",length=40)
	public String getDi_name() {
		return di_name;
	}
	public void setDi_name(String di_name) {
		this.di_name = di_name;
	}
	@Column(name = "di_num",length=11)
	public int getDi_num() {
		return di_num;
	}
	public void setDi_num(int di_num) {
		this.di_num = di_num;
	}
	@Column(name = "di_flag",length=11)
	public int getDi_flag() {
		return di_flag;
	}
	public void setDi_flag(int di_flag) {
		this.di_flag = di_flag;
	}
	@Column(name = "di_lastprice",length=11)
	public double getDi_lastprice() {
		return di_lastprice;
	}
	public void setDi_lastprice(double di_lastprice) {
		this.di_lastprice = di_lastprice;
	}
	@Column(name = "di_nowprice",length=11)
	public double getDi_nowprice() {
		return di_nowprice;
	}
	public void setDi_nowprice(double di_nowprice) {
		this.di_nowprice = di_nowprice;
	}
	@Transient
	public String getDti_name() {
		return dti_name;
	}
	public void setDti_name(String dti_name) {
		this.dti_name = dti_name;
	}
	
	
	
}
