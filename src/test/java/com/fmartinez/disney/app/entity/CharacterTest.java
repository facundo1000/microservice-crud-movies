package com.fmartinez.disney.app.entity;

import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.fmartinez.disney.app.util.PojoGenerator.buildCharacterModel;
import static com.fmartinez.disney.app.util.PojoGenerator.buildMovieModel;
import static org.assertj.core.api.Assertions.assertThat;

class CharacterTest {

    private static Character character;
    private static MovieSerie movieSerie;

    @BeforeAll
    static void setUp() {
        character = buildCharacterModel();
        movieSerie = buildMovieModel();
    }

    private final MovieSerie movieSerieOne = new MovieSerie(2L,
            "Source image of the movie",
            "Indiana Jones",
            new Date(),
            2,
            new HashSet<>(),
            new Genre(1L, "Drama", "descriptive image of the genre", Set.of(movieSerie),Boolean.FALSE),
            Boolean.FALSE);

    @Test
    @DisplayName("Test: add movie to movieSet")
    void addMovieSerie() {

        character.addMovieSerie(movieSerie);
        character.addMovieSerie(movieSerieOne);

        assertThat(character.getMovies()).hasSize(2);
        assertThat(movieSerie.getCharacters()).containsOnly(character);
        assertThat(movieSerieOne.getCharacters()).containsOnly(character);
    }

    @Test
    @DisplayName("Test: remove movie from movieSet")
    void removeMovie() {
        addMovieSerie();

        character.removeMovie(movieSerie);
        character.removeMovie(movieSerieOne);
        assertThat(character.getMovies()).hasSize(0);

    }

}