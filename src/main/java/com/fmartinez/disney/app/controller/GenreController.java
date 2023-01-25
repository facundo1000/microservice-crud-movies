package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.swagger.find.genre.ResponseFindGenre;
import com.fmartinez.disney.app.swagger.save.genre.ResponseSaveGenre;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.fmartinez.disney.app.constant.ApplicationConstant.GENRE_PATH;

@Tag(name = "Genre Controller", description = "Genre client side operations")
@RequestMapping(GENRE_PATH)
public interface GenreController {

    @GetMapping
    @ResponseFindGenre
    ResponseEntity<?> getAllGenres();

    @PostMapping("/create")
    @ResponseSaveGenre
    ResponseEntity<?> create(@RequestBody Genre genre);
}
