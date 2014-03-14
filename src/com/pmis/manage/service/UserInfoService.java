package com.pmis.manage.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.apache.james.security.DigestUtil;
import org.springframework.stereotype.Component;

import com.pmis.manage.dao.UserInfoDao;
import com.pmis.manage.model.UserInfo;
import com.pmis.util.LoadCon;

@Component
public class UserInfoService {
	private UserInfoDao userInfoDao;
	
	public List<UserInfo> pageQuery(int page,int page_num,String contidion){
		String hql = "from UserInfo "+contidion;
		return (List<UserInfo>) userInfoDao.pageQuery(hql, page, page_num);
	}
	
	public UserInfo getById(int bi_id){
		return (UserInfo)userInfoDao.getById(UserInfo.class, bi_id);
	}
	
	public void delete(UserInfo uiBean){
		userInfoDao.delete(uiBean);
	}
	
	public void saveOrUpdate(UserInfo uiBean){
		if(uiBean.getUi_id()!=0){
			userInfoDao.save(uiBean);
		}else{
			userInfoDao.merge(uiBean);
		}
	}
	
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	@Resource
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
}