package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.AuditController;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditControllerImpl implements AuditController {

    private final GenreService genreService;


    public AuditControllerImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genreList = genreService.getAllGenre();
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Character>> getAllCharacters() {
        return null;
    }

    @Override
    public ResponseEntity<List<MovieSerie>> getAllMovies() {
        return null;
    }
}
