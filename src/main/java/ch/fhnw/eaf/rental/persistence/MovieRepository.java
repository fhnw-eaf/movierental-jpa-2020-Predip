package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByTitle(String title);
}
