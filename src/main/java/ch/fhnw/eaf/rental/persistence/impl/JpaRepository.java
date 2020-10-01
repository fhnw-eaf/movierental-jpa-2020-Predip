package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.persistence.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class JpaRepository<T> implements Repository<T, Long> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> specificClass;
    private String tableName;

    public JpaRepository(Class<T> specificClass, String tableName) {
        this.specificClass = specificClass;
        this.tableName = tableName;
    }


    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(em.find(specificClass, id));
    }

    @Override
    public T save(T t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.getReference(specificClass, id));
    }

    @Override
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> q = em.createQuery("SELECT tm FROM "+tableName+" tm", specificClass);
        return q.getResultList();
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT COUNT(tm) FROM "+tableName+" tm WHERE tm.id = :id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(tm) FROM "+tableName+" tm", Long.class)
                .getSingleResult();
    }
}
