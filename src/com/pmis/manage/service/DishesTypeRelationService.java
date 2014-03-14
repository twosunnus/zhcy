package com.pmis.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pmis.manage.dao.DishesTypeRelationDao;
import com.pmis.manage.model.DishesInfo;
import com.pmis.manage.model.DishesTypeRelation;
@Component
public class DishesTypeRelationService {
	private DishesTypeRelationDao dishesTypeRelationDao;

	public void deleteAll(List<DishesTypeRelation> list){
		dishesTypeRelationDao.deleteAll(list);
	}
	
	public void saveOrUpdate(DishesInfo diBean,String dti_id){
		this.deleteAll(this.getByDi_id(diBean.getDi_id()));
		String dti_ids [] = dti_id.split(",");
		for(String dtiid : dti_ids){
			DishesTypeRelation dtrBean = new DishesTypeRelation();
			dtrBean.setDi_id(diBean.getDi_id());
			dtrBean.setDti_id(Integer.parseInt(dtiid));
			dishesTypeRelationDao.save(dtrBean);
		}
	}
	
	public List<DishesTypeRelation> getByDi_id(int di_id){
		String hql = "from DishesTypeRelation where di_id = "+di_id+"";
		return (List<DishesTypeRelation>)dishesTypeRelationDao.pageQuery(hql,null);
	}
	
	public DishesTypeRelationDao getDishesTypeRelationDao() {
		return dishesTypeRelationDao;
	}
	@Resource
	public void setDishesTypeRelationDao(DishesTypeRelationDao dishesTypeRelationDao) {
		this.dishesTypeRelationDao = dishesTypeRelationDao;
	}

	
}
