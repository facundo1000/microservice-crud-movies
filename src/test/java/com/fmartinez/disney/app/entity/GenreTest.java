package com.fmartinez.disney.app.entity;

import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;

import static com.fmartinez.disney.app.util.PojoGenerator.buildGenreModel;
import static com.fmartinez.disney.app.util.PojoGenerator.buildMovieModel;
import static org.assertj.core.api.Assertions.assertThat;

public class GenreTest {
    private static Genre genre;
    private static MovieSerie movie;

    @BeforeAll
    static void setUp() {
        genre = buildGenreModel();
        movie = buildMovieModel();
    }

    private final MovieSerie movieSerieOne = new MovieSerie(2L,
            "Source image of the movie",
            "Indiana Jones",
            new Date(),
            2,
            new HashSet<>(),
            new Genre(1L, "Drama", "descriptive image of the genre", new HashSet<>()));

    @Test
    @DisplayName("Test: add a genre to a movie")
    void addMovieSerie() {
        genre.addMovieSerie(movie);
        genre.addMovieSerie(movieSerieOne);

        assertThat(genre.getMovies()).hasSize(2);
        assertThat(movieSerieOne.getGender()).isInstanceOf(Genre.class);
        assertThat(movie.getGender()).isInstanceOf(Genre.class);
    }

    @Test
    @DisplayName("Test: remove a genre form a movie")
    void removeGenreFromMovie() {
        addMovieSerie();
        genre.removesMovies();

        assertThat(movie.getGender()).isEqualTo(null);
        assertThat(movieSerieOne.getGender()).isEqualTo(null);
        assertThat(genre.getMovies()).hasSize(0);
    }

}
