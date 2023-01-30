package com.fmartinez.disney.app.repository;

import com.fmartinez.disney.app.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
