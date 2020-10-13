package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.persistence.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class JpaRepository<T> implements Repository<T, Long> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> specificClass;

    public JpaRepository(Class<T> specificClass) {
        this.specificClass = specificClass;
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
        return em.createQuery("SELECT tm FROM " + specificClass.getSimpleName() + " tm", specificClass)
                .getResultList();
    }

    @Override
    public boolean existsById(Long id) {
        return em.createQuery(
                "SELECT COUNT(tm) FROM " + specificClass.getSimpleName() + " tm WHERE tm.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(tm) FROM " + specificClass.getSimpleName() + " tm", Long.class)
                .getSingleResult();
    }
}
