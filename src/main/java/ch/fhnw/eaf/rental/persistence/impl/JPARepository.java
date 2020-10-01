package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.persistence.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public abstract class JPARepository<T> implements Repository<T, Long> {

    @PersistenceContext
    private EntityManager em;

    private Class<T> specificClass;

    public JPARepository(Class<T> specificClass) {
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
}
