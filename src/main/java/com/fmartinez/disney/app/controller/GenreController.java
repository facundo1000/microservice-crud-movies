package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.GenreDto;
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
    ResponseEntity<List<GenreDto>> getAllGeneres(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted);

    @PostMapping("/create")
    @ResponseSaveGenre
    ResponseEntity<GenreDto> create(@RequestBody Genre genre);

    @PutMapping("/update/{id}")
    ResponseEntity<GenreDto> update(@RequestBody Genre genre, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PostMapping("/add/{idGenre}/movie/{idMovie}")
    ResponseEntity<GenreDto> addGenreToMovie(@PathVariable Long idGenre, @PathVariable Long idMovie);

    @DeleteMapping("/remove/{idGenre}/movie/{idMovie}")
    ResponseEntity<Void> removeGenreToMovie(@PathVariable Long idGenre, @PathVariable Long idMovie);

}
