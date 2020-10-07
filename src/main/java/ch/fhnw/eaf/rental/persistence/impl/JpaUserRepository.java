package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaUserRepository extends JpaRepository<User> implements UserRepository {

    public JpaUserRepository() {
        super(User.class, "User");
    }

    @Override
    public List<User> findByLastName(String lastName) {
        //return em.createQuery("SELECT u FROM User u WHERE u.lastName = :lastName", User.class)
        return em.createNamedQuery(User.FIND_BY_LASTNAME, User.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return em.createQuery(User.FIND_BY_FIRSTNAME, User.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    @Override
    public List<User> findByEmail(String email) {
        return em.createQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();
    }
}
