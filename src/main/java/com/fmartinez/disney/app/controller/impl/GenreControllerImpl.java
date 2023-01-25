package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.GenreController;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreControllerImpl implements GenreController {

    private final GenreService service;

    public GenreControllerImpl(final GenreService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genreSet = service.getAllGenre();
        return new ResponseEntity<>(genreSet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Genre> create(@RequestBody Genre genre) {
        Genre genreNuevo = service.create(genre);
        return new ResponseEntity<>(genreNuevo, HttpStatus.CREATED);
    }
}
