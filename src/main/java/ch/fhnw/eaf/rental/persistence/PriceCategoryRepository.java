package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.PriceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Long> {
}
