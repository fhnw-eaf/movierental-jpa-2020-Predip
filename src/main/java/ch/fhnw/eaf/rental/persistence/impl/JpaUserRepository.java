package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaUserRepository extends JpaRepository<User> implements UserRepository {

	public JpaUserRepository() {
		super(User.class, "User");
	}

	@Override
	public List<User> findByLastName(String lastName) {
		TypedQuery<User> q = em.createQuery(
				"SELECT u FROM User u WHERE u.lastName = :lastName",
				User.class);
		q.setParameter("lastName", lastName);
		return q.getResultList();
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		TypedQuery<User> q = em.createQuery(
				"SELECT u FROM User u WHERE u.firstName = :firstName",
				User.class);
		q.setParameter("firstName", firstName);
		return q.getResultList();
	}

	@Override
	public List<User> findByEmail(String email) {
		TypedQuery<User> q = em.createQuery(
				"SELECT u FROM User u WHERE u.email = :email",
				User.class);
		q.setParameter("email", email);
		return q.getResultList();
	}

}
