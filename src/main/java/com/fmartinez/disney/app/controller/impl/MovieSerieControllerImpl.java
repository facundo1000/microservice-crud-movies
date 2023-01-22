package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.MovieController;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.MovieSerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class MovieSerieControllerImpl implements MovieController {

    private final MovieSerieService service;

    public MovieSerieControllerImpl(MovieSerieService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getAllMovies() {
        List<MovieSerieDto> movieSeries = service.getAllMovies();
        return new ResponseEntity<>(movieSeries, HttpStatus.OK);
    }

    @Override
    public MovieSerieDetailDto getMovieDetail(Long id) {
        return service.getMovieSerieDetail(id);
    }

    @Override
    public ResponseEntity<?> getMovieByName(String name) {
        MovieSerieDto movieSerie = service.getByTitle(name);
        return new ResponseEntity<>(movieSerie, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> getMovieByGenderId(Long idGenero) {
        Set<MovieSerieDto> movieDtoSet = service.findeMovieSerieByGenderId(idGenero);
        return new ResponseEntity<>(movieDtoSet, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> createMovie(MovieSerie movieSerie) {
        MovieSerie movie = service.create(movieSerie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateMovie(MovieSerie movieSerie, Long id) {
        MovieSerie update = service.update(movieSerie, id);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long id) {
        service.deleteMovieSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TODO: implementar el metodo addCharacterToMovie
    @Override
    public ResponseEntity<?> addCharacterToMovie(Long idMovie, Long idCharacter) {

        MovieSerie ms = service.addCharacterToMovie(idMovie, idCharacter);

        return new ResponseEntity<>(ms, HttpStatus.CREATED);
    }

    //TODO: implementar el metodo deleteCharacterFromMovie
    @Override
    public ResponseEntity<Void> deleteCharacterFromMovie(Long idMovie, Long idCharacter) {

        if (idMovie > 0 && idCharacter > 0) {
            service.deleteCharacterFromMovie(idMovie, idCharacter);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
