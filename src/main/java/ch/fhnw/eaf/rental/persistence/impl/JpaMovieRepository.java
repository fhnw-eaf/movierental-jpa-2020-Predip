package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JpaMovieRepository extends JpaRepository<Movie> implements MovieRepository {

    public JpaMovieRepository() {
        super(Movie.class);
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return em.createNamedQuery(Movie.FIND_BY_TITLE, Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

}
