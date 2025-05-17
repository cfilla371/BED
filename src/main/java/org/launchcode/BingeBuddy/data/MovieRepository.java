package org.launchcode.BingeBuddy.data;

import org.launchcode.BingeBuddy.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


    Optional<Movie> findByImdbId(String imdbID);
}
