package com.pmis.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pmis.manage.dao.JobInfoDao;
import com.pmis.manage.dao.UserInfoDao;
import com.pmis.manage.model.JobInfo;
import com.pmis.manage.model.UserInfo;
import com.pmis.util.StringFormat;
@Component
public class JobInfoService {
	private JobInfoDao jobInfoDao;
	private UserInfoDao userInfoDao;
	
	public List<JobInfo> pageQuery(int page,int page_num,String contidion){
		String hql = "from JobInfo "+contidion;
		return (List<JobInfo>) jobInfoDao.pageQuery(hql, page, page_num);
	}
	
	public List<JobInfoDao> pageQuery(String cition){
		String hql = "from JobInfo"+cition;
		return (List<JobInfoDao>) jobInfoDao.pageQuery(hql,null);
	}
	
	public JobInfo getById(int ji_id){
		return (JobInfo)jobInfoDao.getById(JobInfo.class, ji_id);
	}
	
	public void delete(JobInfo jiBean){
		jobInfoDao.delete(jiBean);
	}
	
	public void saveOrUpdate(JobInfo jiBean){
		if(jiBean.getJi_id()!=0){
			jobInfoDao.merge(jiBean);
		}else{
			jobInfoDao.save(jiBean);
		}
	}
	
	/*
	 * 根据传入的用户ID,获得该用户的职位ID  
	 */
	public String getJiIdWithUiId(int ui_id){
		UserInfo userBean = (UserInfo) userInfoDao.getById(UserInfo.class,ui_id);
		if( StringFormat.notEmpty(userBean) || StringFormat.notEmpty(userBean.getUi_ji_id())){
			String ji_id = userBean.getUi_ji_id();
			return ji_id;
		}
		return "";
	}
	
	public JobInfoDao getJobInfoDao() {
		return jobInfoDao;
	}
	@Resource
	public void setJobInfoDao(JobInfoDao jobInfoDao) {
		this.jobInfoDao = jobInfoDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	@Resource
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	
	
}
