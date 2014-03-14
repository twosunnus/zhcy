package com.pmis.manage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pmis.manage.dao.AllotInfoDao;
import com.pmis.manage.model.MenuInfo;
import com.pmis.util.LoadCon;

@Component
public class AllotInfoService {
	private AllotInfoDao allotInfoDao;

	//在全部树节点中标识处选中节点
	public void getCheckedList(List<MenuInfo> allMenuList,List<MenuInfo> jobMenuList){
		for(MenuInfo miBean : allMenuList){
			int flag = 0;
			for(MenuInfo jbBean : jobMenuList){
				if(miBean.getMi_id()==(jbBean.getMi_id())){
					flag ++;
				}
				if(flag == 1){
					miBean.setChecked("true");
				}else{
					miBean.setChecked("false");
				}
			}
		}
	}
	
	//根据职位ID删除当前职位所有可见菜单
	public void deleteAllMenu(String ji_id) throws SQLException{
		Connection conn = new LoadCon().getCon();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("delete from allotinfo where ji_id ='"+ji_id+"'");
		conn.close();
	}
	public AllotInfoDao getAllotInfoDao() {
		return allotInfoDao;
	}
	@Resource
	public void setAllotInfoDao(AllotInfoDao allotInfoDao) {
		this.allotInfoDao = allotInfoDao;
	}
	
}
