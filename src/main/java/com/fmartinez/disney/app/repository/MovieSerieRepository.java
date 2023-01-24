package com.fmartinez.disney.app.repository;

import com.fmartinez.disney.app.model.MovieSerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MovieSerieRepository extends JpaRepository<MovieSerie, Long> {
    Optional<MovieSerie> findByTitleIgnoreCase(String name);

    Optional<Set<MovieSerie>> findByGenderId(Long id);

}
