package com.pmis.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.pmis.manage.dao.AllotInfoDao;
import com.pmis.manage.dao.JobInfoDao;
import com.pmis.manage.dao.MenuTreeDao;
import com.pmis.manage.dao.UserInfoDao;
import com.pmis.manage.model.AllotInfo;
import com.pmis.manage.model.MenuInfo;
import com.pmis.util.PageConfig;
import com.pmis.util.SqlCommon;
import com.pmis.util.StringFormat;

@Component
public class MenuTreeService {
	private MenuTreeDao menuTreeDao;
	private UserInfoDao userInfoDao;
	private JobInfoDao jobInfoDao;
	private AllotInfoDao allotInfoDao;
	
	
	/*
	 * 根据传入的职位ID,获得该职位下的菜单
	 */
	public List<MenuInfo> getMenuTree(String ji_id){
		List<MenuInfo> returnList = new ArrayList();
		if( StringFormat.notEmpty(ji_id) ){
			List<AllotInfo> allotInfoList = (List<AllotInfo>) allotInfoDao.pageQuery("from AllotInfo where ji_id = "+ji_id,null );
			String menuConditionSql = SqlCommon.getInSqlWithClass(new AllotInfo(), allotInfoList);
			if(StringFormat.notEmpty(menuConditionSql)){
				 menuConditionSql = " and mi_id in " + menuConditionSql;
			}else{
				menuConditionSql = "and 1 = 2";
			}
			returnList = (List<MenuInfo>) menuTreeDao.pageQuery("from MenuInfo where 1 = 1 " + menuConditionSql + " order by mi_order asc", null);
		}else{
			return returnList;
		}
		return returnList;
	}
	/**
	 * 获得树结构json字符串
	 * @return
	 */
	public String getJsonTreeStr(List<MenuInfo> menuTreeList){
		JSONArray array = new JSONArray();
		
		for(MenuInfo miBean : menuTreeList){
			if(miBean.getMi_parentid()==PageConfig.root_num){
				JSONObject obj = new JSONObject();
				obj.put("id", miBean.getMi_id());
				obj.put("name",miBean.getMi_name());
				obj.put("checked", miBean.getChecked());
				recur(menuTreeList,miBean.getMi_id(),obj);
				array.add(obj);
				
			}
		}
		return array.toString();
	}
	//递归添加下级菜单
	public static JSONObject recur(List<MenuInfo> menuTreeList,int parentid,JSONObject obj){
	JSONArray array = new JSONArray();
	for(int i = 0 ; i < menuTreeList.size(); i++){
		if(menuTreeList.get(i).getMi_parentid()==(parentid)){
			JSONObject sonobj = new JSONObject();
			sonobj.put("id", menuTreeList.get(i).getMi_id());
			sonobj.put("name", menuTreeList.get(i).getMi_name());
			sonobj.put("checked", menuTreeList.get(i).getChecked());
			recur(menuTreeList,menuTreeList.get(i).getMi_id(),sonobj);
			array.add(sonobj);
		}
	}
	if(!array.isEmpty()){
		obj.put("children", array);
	}
	return obj;
	}
	public void save(MenuInfo miBean){
		menuTreeDao.save(miBean);
	}
	public void update(MenuInfo miBean){
		menuTreeDao.merge(miBean);
	}
	public MenuTreeDao getMenuTreeDao() {
		return menuTreeDao;
	}
	@Resource
	public void setMenuTreeDao(MenuTreeDao menuTreeDao) {
		this.menuTreeDao = menuTreeDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	@Resource
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public JobInfoDao getJobInfoDao() {
		return jobInfoDao;
	}
	@Resource
	public void setJobInfoDao(JobInfoDao jobInfoDao) {
		this.jobInfoDao = jobInfoDao;
	}
	
	public AllotInfoDao getAllotInfoDao() {
		return allotInfoDao;
	}
	@Resource
	public void setAllotInfoDao(AllotInfoDao allotInfoDao) {
		this.allotInfoDao = allotInfoDao;
	}
	
	
	
	
	
}
