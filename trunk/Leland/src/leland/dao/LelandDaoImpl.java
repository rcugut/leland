package leland.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import leland.domain.base.BaseEntity;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LelandDaoImpl
		extends HibernateDaoSupport implements LelandDao
{
	
	public <T extends BaseEntity> T get(final Class<T> clazz, final Serializable id)
	{
		return clazz.cast(this.getHibernateTemplate().get(clazz, id));
	}

	
	public <T extends BaseEntity> List<T> getAll(final Class<T> clazz)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(clazz).addOrder(Order.asc("id"));
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	public <T extends BaseEntity> List<T> getAllActive(final Class<T> clazz)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(clazz).addOrder(Order.asc("id"));
		dc.add(Restrictions.eq("active", true));
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	
	public <T extends BaseEntity> List<T> getByField(final Class<T> clazz, final String fieldName, final Object value)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(clazz).addOrder(Order.asc("id"));
		dc.add(Restrictions.eq(fieldName, value));
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	
	public <T extends BaseEntity> List<T> getByCriteria(final DetachedCriteria dc)
	{
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	
	
	public void save(final BaseEntity entity)
	{
		entity.setDateUpdated(Calendar.getInstance().getTime());
		this.getHibernateTemplate().saveOrUpdate(entity);
	}


	public void delete(final BaseEntity entity)
	{
		this.getHibernateTemplate().delete(entity);
	}
	
	
	public void deleteAll(final Class<? extends BaseEntity> clazz)
	{
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createQuery("DELETE FROM " + clazz.getName()).executeUpdate();
				return null;
			}
		});

	}

	
	
	
	
	public void reassociate(final BaseEntity persistent)
	{
		this.getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				session.lock(persistent, LockMode.NONE);
				return null;
			}
		});
	}

	
	public void evict(final BaseEntity entity)
	{
		this.getHibernateTemplate().evict(entity);
	}
	
	
	public void flush()
	{
		this.getHibernateTemplate().flush();
	}	
}
