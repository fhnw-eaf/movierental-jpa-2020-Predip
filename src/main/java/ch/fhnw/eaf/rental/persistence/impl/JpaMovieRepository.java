package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaMovieRepository extends JpaRepository<Movie> implements MovieRepository {

	public JpaMovieRepository() {
		super(Movie.class, "Movie");
	}

	@Override
	public List<Movie> findByTitle(String title) {
		TypedQuery<Movie> q = em.createQuery(
				"SELECT m FROM Movie m WHERE m.title = :title",
				Movie.class);
		q.setParameter("title", title);
		return q.getResultList();
	}

}
