package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.MovieController;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.MovieSerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<MovieSerieDto>> getAllMovies() {
        List<MovieSerieDto> movieSeries = service.getAllMovies();
        return new ResponseEntity<>(movieSeries, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieSerieDetailDto> getMovieDetail(@PathVariable Long id) {
        MovieSerieDetailDto movieSerie = service.getMovieSerieDetail(id);
        return new ResponseEntity<>(movieSerie, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieSerieDto> getMovieByName(@PathVariable String name) {
        MovieSerieDto movieSerie = service.getByTitle(name);
        return new ResponseEntity<>(movieSerie, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Set<MovieSerieDto>> getMovieByGenderId(@PathVariable Long idGenero) {
        Set<MovieSerieDto> movieDtoSet = service.findeMovieSerieByGenderId(idGenero);
        return new ResponseEntity<>(movieDtoSet, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<MovieSerieDto>> getMovieOrderByCreateAt(@PathVariable String sort) {
        List<MovieSerieDto> dtoList = service.movieSerieOrderByDate(sort);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieSerie> createMovie(@RequestBody MovieSerie movieSerie) {
        MovieSerie movie = service.create(movieSerie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MovieSerie> updateMovie(@RequestBody MovieSerie movieSerie, @PathVariable Long id) {
        MovieSerie update = service.update(movieSerie, id);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        service.deleteMovieSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<MovieSerie> addCharacterToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {

        MovieSerie ms = service.addCharacterToMovie(idMovie, idCharacter);

        return new ResponseEntity<>(ms, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCharacterFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {

        if (idMovie > 0 && idCharacter > 0) {
            service.deleteCharacterFromMovie(idMovie, idCharacter);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
