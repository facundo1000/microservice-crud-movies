package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.swagger.delete.ResponseDeleteMovieSerie;
import com.fmartinez.disney.app.swagger.delete.ResponseDeleteMovieSerieCharacter;
import com.fmartinez.disney.app.swagger.find.movie.*;
import com.fmartinez.disney.app.swagger.save.movie.ResponseSaveMovieSerie;
import com.fmartinez.disney.app.swagger.save.movie.ResponseSaveMovieSerieCharcter;
import com.fmartinez.disney.app.swagger.save.movie.ResponseUpdateMovieSerie;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.fmartinez.disney.app.constant.ApplicationConstant.MOVIE_SERIE_PATH;

@Tag(name = "Movie-Serie Controller", description = "movie-serie client side operations")
@RequestMapping(MOVIE_SERIE_PATH)
public interface MovieController {

    @GetMapping
    @ResponseFindMovieSerie
    ResponseEntity<Set<MovieSerieDto>> getAllMovies(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted);

    @GetMapping("/details/{id}")
    @ResponseFindMovieSerieDetails
    ResponseEntity<MovieSerieDetailDto> getMovieDetail(@PathVariable Long id);

    @GetMapping("/name/{title}")
    @ResponseFindMovieSerieByName
    ResponseEntity<MovieSerieDto> getMovieByName(@PathVariable String title);

    @GetMapping("/genre/{idGenero}")
    @ResponseFindMovieSerieByGenre
    ResponseEntity<Set<MovieSerieDto>> getMovieByGenderId(@PathVariable Long idGenero);

    @GetMapping("/sort/{sort}")
    @ResponseFindMovieSerieSort
    ResponseEntity<List<MovieSerieDto>> getMovieOrderByCreateAt(@PathVariable String sort);

    @PostMapping("/create")
    @ResponseSaveMovieSerie
    ResponseEntity<MovieSerieDto> createMovie(@RequestBody MovieSerie movieSerie);

    @PutMapping("/update/{id}")
    @ResponseUpdateMovieSerie
    ResponseEntity<MovieSerieDto> updateMovie(@RequestBody MovieSerie movieSerie, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    @ResponseDeleteMovieSerie
    ResponseEntity<Void> deleteMovie(@PathVariable Long id);

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    @ResponseSaveMovieSerieCharcter
    ResponseEntity<MovieSerieDetailDto> addCharacterToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter);

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    @ResponseDeleteMovieSerieCharacter
    ResponseEntity<Void> deleteCharacterFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter);


}
