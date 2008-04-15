package leland.dao;

import java.io.Serializable;
import java.util.List;

import leland.domain.base.BaseEntity;

import org.hibernate.criterion.DetachedCriteria;

public interface LelandDao
{

	public <T extends BaseEntity> T get(final Class<T> clazz, final Serializable id);

	public <T extends BaseEntity> List<T> getAll(final Class<T> clazz);

	public <T extends BaseEntity> List<T> getAllActive(final Class<T> clazz);

	public <T extends BaseEntity> List<T> getByField(final Class<T> clazz, final String fieldName, final Object value);

	public <T extends BaseEntity> List<T> getByCriteria(final DetachedCriteria dc);

	public void save(final BaseEntity entity);

	public void delete(final BaseEntity entity);

	public void deleteAll(final Class<? extends BaseEntity> clazz);

	public void reassociate(final BaseEntity persistent);

	public void evict(final BaseEntity entity);

	public void flush();

}
