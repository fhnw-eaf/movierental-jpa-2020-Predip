package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<PriceCategory> findById(Long id) {
		return Optional.ofNullable(em.find(PriceCategory.class, id));
	}

	@Override
	public List<PriceCategory> findAll() {
		TypedQuery<PriceCategory> q = em.createQuery("SELECT pc FROM PriceCategory pc", PriceCategory.class);
		return q.getResultList();
	}

	@Override
	public PriceCategory save(PriceCategory t) {
		return em.merge(t);
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(PriceCategory.class, id));
	}

	@Override
	public void delete(PriceCategory entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public boolean existsById(Long id) {
		TypedQuery<Long> q = em.createQuery(
				"SELECT COUNT(pc) FROM PriceCategory pc WHERE pc.id = :id", Long.class);
		q.setParameter("id", id);
		return q.getSingleResult() > 0;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(pc) FROM PriceCategory pc", Long.class)
				.getSingleResult();
	}

}
