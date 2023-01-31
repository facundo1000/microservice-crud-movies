package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.MovieController;
import com.fmartinez.disney.app.dto.GenreDto;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.MovieSerieService;
import com.fmartinez.disney.app.util.ErrorType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MovieSerieControllerImpl implements MovieController {

    private final MovieSerieService service;
    private final MapStructMapper mapper;

    @Override
    public ResponseEntity<Set<MovieSerieDto>> getAllMovies(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted) {
        Set<MovieSerieDto> movieSeries = service.getAllMoviesFilter(isDeleted);
        return new ResponseEntity<>(movieSeries, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieSerieDetailDto> getMovieDetail(@PathVariable Long id) {
        Optional<MovieSerieDetailDto> movieSerie = service.getAllMovies()
                .stream()
                .filter(m -> m.getDeleted().equals(Boolean.FALSE) && Objects.equals(m.getId(), id))
                .map(mapper::movieSerieDetail)
                .findAny();

        return movieSerie.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_EXCEPTION));
    }

    @Override
    public ResponseEntity<MovieSerieDto> getMovieByName(@PathVariable String title) {
        Optional<MovieSerieDto> serieDto = service.getAllMovies()
                .stream()
                .filter(m -> m.getDeleted() == Boolean.FALSE && m.getTitle().equalsIgnoreCase(title))
                .map(mapper::movieSerieToMovieSerieDto)
                .findAny();

        return serieDto.map(movie ->
                        new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_EXCEPTION));
    }

    @Override
    public ResponseEntity<Set<MovieSerieDto>> getMovieByGenderId(@PathVariable Long idGenero) {

        Set<MovieSerieDto> serieDto = service.getAllMovies()
                .stream()
                .filter(m -> m.getGender() != null)
                .filter(m -> m.getDeleted() == Boolean.FALSE && Objects.equals(m.getGender().getId(), idGenero))
                .map(mapper::movieSerieToMovieSerieDto)
                .collect(Collectors.toSet());

        if (serieDto.isEmpty()) {
            throw new NotFoundException(ErrorType.NOT_FOUND_EXCEPTION);
        }
        return new ResponseEntity<>(serieDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieSerieDto>> getMovieOrderByCreateAt(@PathVariable String sort) {
        List<MovieSerieDto> dtoList = service.movieSerieOrderByDate(sort);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieSerieDto> createMovie(@RequestBody MovieSerie movieSerie) {
        MovieSerie movie = service.create(movieSerie);
        MovieSerieDto dto = new MovieSerieDto(movie.getImage(), movie.getTitle(), movie.getCreateAt());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MovieSerieDto> updateMovie(@RequestBody MovieSerie movieSerie, @PathVariable Long id) {
        MovieSerie update = service.update(movieSerie, id);
        MovieSerieDto dto = new MovieSerieDto(update.getImage(), update.getTitle(), update.getCreateAt());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        service.deleteMovieSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<MovieSerieDetailDto> addCharacterToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        MovieSerie ms = service.addCharacterToMovie(idMovie, idCharacter);

        MovieSerieDetailDto dto = new MovieSerieDetailDto(ms.getImage(),
                ms.getTitle(),
                ms.getCreateAt(),
                ms.getRate(),
                ms.getCharacters().stream().map(mapper::characterToCharacterDto).collect(Collectors.toSet()),
                new GenreDto(ms.getGender().getName(), ms.getGender().getImage()));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCharacterFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {

        if (idMovie > 0 && idCharacter > 0) {
            service.deleteCharacterFromMovie(idMovie, idCharacter);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
