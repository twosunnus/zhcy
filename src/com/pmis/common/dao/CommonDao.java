package com.pmis.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/*
 * 公共抽象类
 */

@Component
public abstract class CommonDao {
	
	public static Logger log = Logger.getLogger(CommonDao.class);
	private HibernateTemplate hibernateTemplate;

	/**
	 * 将实体保存进数据库
	 */
	public void save(Object o){
		hibernateTemplate.save(o);
	}
	/**
	 * 调用update方法
	 */
	public void update(Object o){
		hibernateTemplate.update(o);
	}
	/**
	 * 调用merge方法
	 */
	public void merge(Object o){
		hibernateTemplate.merge(o);
	}
	/**
	 * 根据ID获取对象
	 */
	public Object getById(Class<?> c, Serializable id) {
		return hibernateTemplate.get(c, id);
	}
	/**
	 * 删除对象
	 */
	public void delete(Object o) {
		hibernateTemplate.delete(o);
	}

	/**
	 * 删除全部对象
	 */
	public void deleteAll(Collection entities) {
		hibernateTemplate.deleteAll(entities);
	}
	/**
	 * 根据ID，及 实体类名删除
	 */
	public void deleteById(Class<?> c, Serializable id) {
		delete(getById(c, id));
	}
	
	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects) {
		return hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objects != null)
					for (int i = 0; i < objects.length; i++){
						query.setParameter(i, objects[i]);
					}
				if (page != null && size != null)
					query.setFirstResult((page - 1) * size).setMaxResults(size);
				return query.list();
			}
		});
	}

	/**
	 * 不分页查询
	 */
	public List<?> pageQuery(String hql, Object... objects) {
		return pageQuery(hql, null, null, objects);
	}
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
}
