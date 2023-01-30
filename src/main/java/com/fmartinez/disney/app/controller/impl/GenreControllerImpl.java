package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.GenreController;
import com.fmartinez.disney.app.dto.GenreDto;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreControllerImpl implements GenreController {

    private final GenreService service;

    public GenreControllerImpl(final GenreService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<GenreDto>> getAllGeneres(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted) {
        List<GenreDto> genreList = service.findAllGenres(isDeleted);
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GenreDto> create(@RequestBody Genre genre) {
        service.create(genre);
        GenreDto genreDto = new GenreDto(genre.getImage(), genre.getName());
        return new ResponseEntity<>(genreDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GenreDto> update(@RequestBody Genre genre, @PathVariable Long id) {
        service.update(genre, id);
        GenreDto dto = new GenreDto(genre.getName(), genre.getImage());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<GenreDto> addGenreToMovie(@PathVariable Long idGenre, @PathVariable Long idMovie) {
        Genre genre = service.addGenreToMovie(idGenre, idMovie);
        GenreDto dto = new GenreDto(genre.getName(), genre.getImage());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> removeGenreToMovie(Long idGenre, Long idMovie) {
        service.removeGenreToMovie(idGenre, idMovie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
