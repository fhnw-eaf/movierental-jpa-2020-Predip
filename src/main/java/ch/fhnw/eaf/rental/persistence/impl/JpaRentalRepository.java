package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaRentalRepository extends JpaRepository<Rental> implements RentalRepository {

	public JpaRentalRepository() {
		super(Rental.class, "Rental");
	}

	@Override
	public List<Rental> findByUser(User user) {
		return user.getRentals();
	}

}
