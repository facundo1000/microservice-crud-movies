package com.fmartinez.disney.app.entity;

import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.MovieSerie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static com.fmartinez.disney.app.util.PojoGenerator.buildCharacterModel;
import static com.fmartinez.disney.app.util.PojoGenerator.buildMovieModel;
import static org.assertj.core.api.Assertions.assertThat;

public class MovieSeriesTest {

    private static Character character;

    private static MovieSerie movieSerie;

    @BeforeAll
    static void setUp() {
        character = buildCharacterModel();
        movieSerie = buildMovieModel();
    }

    private final Character characterTwo = new Character(2L,
            "source image of the character",
            "Indiana Jones",
            20,
            45.65,
            "the story of Indiana Jones",
            new HashSet<>(),
            Boolean.FALSE);

    @Test
    @DisplayName("Test: add a characters to the set")
    void addCharacters() {
        movieSerie.addCharacter(character);
        movieSerie.addCharacter(characterTwo);

        assertThat(movieSerie.getCharacters()).hasSize(2);
        assertThat(character.getMovies()).containsOnly(movieSerie);
        assertThat(characterTwo.getMovies()).containsOnly(movieSerie);

        allAssertionsForMovieSerie(character);
    }

    @Test
    @DisplayName("Test: remove a character from the set")
    void removeCharacter() {
        addCharacters();
        movieSerie.removeCharacters();
        assertThat(movieSerie.getCharacters()).hasSize(0);
    }

    private void allAssertionsForMovieSerie(Character characterIn) {
        assertThat(characterIn.getId()).isEqualTo(character.getId());
        assertThat(characterIn.getName()).isEqualTo(character.getName());
        assertThat(characterIn.getImage()).isEqualTo(character.getImage());
        assertThat(characterIn.getAge()).isEqualTo(character.getAge());
        assertThat(characterIn.getWeight()).isEqualTo(character.getWeight());
        assertThat(characterIn.getStory()).isEqualTo(character.getStory());
        assertThat(characterIn.getMovies()).isEqualTo(character.getMovies());
    }


}
