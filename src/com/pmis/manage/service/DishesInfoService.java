package com.pmis.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.pmis.manage.dao.DishesInfoDao;
import com.pmis.manage.model.DishesInfo;
import com.pmis.manage.model.DishesTypeInfo;
@Component
public class DishesInfoService {
	private DishesInfoDao dishesInfoDao;

	public List<DishesInfo> pageQuery(int ui_id,int page,int page_num){
		Session session = dishesInfoDao.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery("SELECT t1.di_id,t1.ui_id,t1.di_name,t1.di_num,t1.di_flag,t1.di_lastprice,t1.di_nowprice, GROUP_CONCAT(t2.dti_name,'','') dti_name FROM dishesinfo t1,dishestypeinfo t2,dishestyperelation t3 WHERE t1.di_id = t3.di_id AND t2.dti_id = t3.dti_id and t1.ui_id = "+ui_id+" GROUP BY t1.di_id");
		query.addScalar("di_id", Hibernate.INTEGER);
		query.addScalar("ui_id", Hibernate.INTEGER);
		query.addScalar("di_name", Hibernate.STRING);
		query.addScalar("di_num", Hibernate.INTEGER);
		query.addScalar("di_flag", Hibernate.INTEGER);
		query.addScalar("di_lastprice", Hibernate.DOUBLE);
		query.addScalar("di_nowprice", Hibernate.DOUBLE);
		query.addScalar("dti_name", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(DishesInfo.class)); 
		query.setFirstResult(page);
		query.setMaxResults(page+page_num);
		List<DishesInfo> l =  query.list();
		return l;
	}
	public List<DishesInfo> pageQuery(int ui_id){
		Session session = dishesInfoDao.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery("SELECT t1.di_id,t1.ui_id,t1.di_name,t1.di_num,t1.di_flag,t1.di_lastprice,t1.di_nowprice, GROUP_CONCAT(t2.dti_name,'','') dti_name FROM dishesinfo t1,dishestypeinfo t2,dishestyperelation t3 WHERE t1.di_id = t3.di_id AND t2.dti_id = t3.dti_id and t1.ui_id = "+ui_id+" GROUP BY t1.di_id");
		query.addScalar("di_id", Hibernate.INTEGER);
		query.addScalar("ui_id", Hibernate.INTEGER);
		query.addScalar("di_name", Hibernate.STRING);
		query.addScalar("di_num", Hibernate.INTEGER);
		query.addScalar("di_flag", Hibernate.INTEGER);
		query.addScalar("di_lastprice", Hibernate.DOUBLE);
		query.addScalar("di_nowprice", Hibernate.DOUBLE);
		query.addScalar("dti_name", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(DishesInfo.class)); 
		List<DishesInfo> l =  query.list();
		return l;
	}
	public void saveOrUpdate(DishesInfo diBean){
		if(diBean.getDi_id()!=0){
			dishesInfoDao.merge(diBean);
		}else{
			dishesInfoDao.save(diBean);
		}
	}
	public DishesInfoDao getDishesInfoDao() {
		return dishesInfoDao;
	}
	@Resource
	public void setDishesInfoDao(DishesInfoDao dishesInfoDao) {
		this.dishesInfoDao = dishesInfoDao;
	}

	
	
}
