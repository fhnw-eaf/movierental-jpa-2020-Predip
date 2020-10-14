package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public abstract class JpaMovieRepository implements MovieRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Movie> findByTitle(String title) {
        return em.createNamedQuery(Movie.FIND_BY_TITLE, Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

}
