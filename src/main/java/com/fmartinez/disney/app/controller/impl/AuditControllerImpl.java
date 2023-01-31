package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.AuditController;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.CharacterService;
import com.fmartinez.disney.app.service.GenreService;
import com.fmartinez.disney.app.service.MovieSerieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuditControllerImpl implements AuditController {

    private final GenreService genreService;
    private final CharacterService characterService;

    private final MovieSerieService movieSerieService;

    @Override
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genreList = genreService.getAllGenre();
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> listAll = characterService.getAllCharacters();
        return new ResponseEntity<>(listAll, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieSerie>> getAllMovies() {
        List<MovieSerie> serieList = movieSerieService.getAllMovies();
        return new ResponseEntity<>(serieList,HttpStatus.OK);
    }
}
