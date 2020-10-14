package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByLastName(String lastName);
	List<User> findByFirstName(String firstName);
	List<User> findByEmail(String email);
}
