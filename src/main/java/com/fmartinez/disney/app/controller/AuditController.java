package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.swagger.find.character.ResponseFindCharacterAudit;
import com.fmartinez.disney.app.swagger.find.genre.ResponseFindGenre;
import com.fmartinez.disney.app.swagger.find.movie.ResponseFindMovieSerieAudit;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.fmartinez.disney.app.constant.ApplicationConstant.AUDIT_PATH;

@Tag(name = "Audit Controller", description = "Audit client side operations")
@RequestMapping(AUDIT_PATH)
public interface AuditController {
    @GetMapping("/genres")
    @ResponseFindGenre
    ResponseEntity<List<Genre>> getAllGenres();

    @GetMapping("/characters")
    @ResponseFindCharacterAudit
    ResponseEntity<List<Character>> getAllCharacters();

    @GetMapping("/movies")
    @ResponseFindMovieSerieAudit
    ResponseEntity<List<MovieSerie>> getAllMovies();


}
