package com.pmis.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pmis.manage.dao.DishesTypeInfoDao;
import com.pmis.manage.dao.JobInfoDao;
import com.pmis.manage.model.DishesTypeInfo;
import com.pmis.manage.model.JobInfo;
@Component
public class DishesTypeInfoService {
	private DishesTypeInfoDao dishesTypeInfoDao;

	public List<DishesTypeInfo> pageQuery(int page,int page_num,String contidion){
		String hql = "from DishesTypeInfo "+contidion;
		return (List<DishesTypeInfo>) dishesTypeInfoDao.pageQuery(hql, page, page_num);
	}
	
	public List<DishesTypeInfo> pageQuery(String cition){
		String hql = "from DishesTypeInfo"+cition;
		return (List<DishesTypeInfo>) dishesTypeInfoDao.pageQuery(hql,null);
	}
	
	public DishesTypeInfo getById(int ji_id){
		return (DishesTypeInfo)dishesTypeInfoDao.getById(DishesTypeInfo.class, ji_id);
	}
	
	public void delete(DishesTypeInfo dtiBean){
		dishesTypeInfoDao.delete(dtiBean);
	}
	
	public void saveOrUpdate(DishesTypeInfo dtiBean){
		if(dtiBean.getDti_id()!=0){
			dishesTypeInfoDao.merge(dtiBean);
		}else{
			dishesTypeInfoDao.save(dtiBean);
		}
	}
	
	public DishesTypeInfoDao getDishesTypeInfoDao() {
		return dishesTypeInfoDao;
	}
	@Resource
	public void setDishesTypeInfoDao(DishesTypeInfoDao dishesTypeInfoDao) {
		this.dishesTypeInfoDao = dishesTypeInfoDao;
	}

	
	
}
