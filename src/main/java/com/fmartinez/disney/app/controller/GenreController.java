package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.swagger.find.genre.ResponseFindGenre;
import com.fmartinez.disney.app.swagger.save.genre.ResponseSaveGenre;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fmartinez.disney.app.constant.ApplicationConstant.GENRE_PATH;

@Tag(name = "Genre Controller", description = "Genre client side operations")
@RequestMapping(GENRE_PATH)
public interface GenreController {

    @GetMapping
    @ResponseFindGenre
    ResponseEntity<List<Genre>> getAllGenres();

    @PostMapping("/create")
    @ResponseSaveGenre
    ResponseEntity<Genre> create(@RequestBody Genre genre);

    @PutMapping("/update/{id}")
    ResponseEntity<Genre> update(@RequestBody Genre genre, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
