package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPriceCategoryRepository extends JpaRepository<PriceCategory> implements PriceCategoryRepository {

	public JpaPriceCategoryRepository() {
		super(PriceCategory.class, "PriceCategory");
	}
}
