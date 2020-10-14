package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class JpaUserRepository implements UserRepository {

    @PersistenceContext
    protected EntityManager em;

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
