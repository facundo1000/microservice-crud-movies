package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.model.MovieSerie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fmartinez.disney.app.constant.ApplicationConstant.MOVIE_SERIE_PATH;

@RequestMapping(MOVIE_SERIE_PATH)
public interface MovieController {

    @GetMapping
    ResponseEntity<?> getAllMovies();

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(params = "id")
    MovieSerieDetailDto getMovieDetail(@RequestParam Long id);

    @GetMapping(params = "name")
    ResponseEntity<?> getMovieByName(@RequestParam String name);

    @GetMapping(params = "genre")
    ResponseEntity<?> getMovieByGenderId(@RequestParam("genre") Long idGenero);

    @GetMapping(params = "order")
    ResponseEntity<?> getMovieOrderByCreateAt(@RequestParam("order") String sort);

    @PostMapping
    ResponseEntity<?> createMovie(@RequestBody MovieSerie movieSerie);

    @PutMapping(params = "id")
    ResponseEntity<?> updateMovie(@RequestBody MovieSerie movieSerie, @RequestParam Long id);

    @DeleteMapping(params = "id")
    ResponseEntity<Void> deleteMovie(@RequestParam Long id);

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    ResponseEntity<?> addCharacterToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter);

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    ResponseEntity<Void> deleteCharacterFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter);


}
