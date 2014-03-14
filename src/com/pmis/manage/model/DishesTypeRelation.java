package com.pmis.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//菜品-类别关联表
@Entity
@Table(name = "dishestyperelation", catalog = "zhcy")
public class DishesTypeRelation {
	//id
	private int dtr_id;
	//菜品id
	private int di_id;
	//菜品类别id
	private int dti_id;
	
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "dtr_id", unique = true, nullable = false)
	public int getDtr_id() {
		return dtr_id;
	}
	public void setDtr_id(int dtr_id) {
		this.dtr_id = dtr_id;
	}
	@Column(name = "di_id",length=11)
	public int getDi_id() {
		return di_id;
	}
	public void setDi_id(int di_id) {
		this.di_id = di_id;
	}
	@Column(name = "dti_id",length=11)
	public int getDti_id() {
		return dti_id;
	}
	public void setDti_id(int dti_id) {
		this.dti_id = dti_id;
	}
	
	
}
