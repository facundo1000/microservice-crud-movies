package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.model.Genre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.fmartinez.disney.app.constant.ApplicationConstant.GENRE_PATH;

@RequestMapping(GENRE_PATH)
public interface GenreController {

    @GetMapping
    ResponseEntity<?> getAllGenres();

    @PostMapping
    ResponseEntity<?> create(@RequestBody Genre genre);
}
