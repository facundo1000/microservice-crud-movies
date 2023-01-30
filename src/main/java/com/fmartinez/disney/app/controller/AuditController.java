package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.GenreDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fmartinez.disney.app.constant.ApplicationConstant.AUDIT_PATH;

@RequestMapping(AUDIT_PATH)
public interface AuditController {
    @GetMapping("/genres")
    ResponseEntity<List<Genre>> getAllGenres();

    @GetMapping("/characters")
    ResponseEntity<List<Character>> getAllCharacters();

    @GetMapping("/movies")
    ResponseEntity<List<MovieSerie>> getAllMovies();


}
