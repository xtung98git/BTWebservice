package webservice.BHXH.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class BaseDaoImpl<T, K> {

	private Class<T> type;

	@PersistenceContext
	protected EntityManager entityManager;
	
	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
	}

	public void add(T t) {
		entityManager.persist(t);
	}

	public void update(T t) {
		entityManager.merge(t);
	}

	public void delete(T t) {
		entityManager.remove(t);
	}

	public T getById(K k) {
		return entityManager.find(type, k);
	}

	public List<T> getAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
		Root<T> root = criteriaQuery.from(type);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
    public boolean exists(K k) {
        T t = entityManager.find(type, k);
        return t != null;
    }

}