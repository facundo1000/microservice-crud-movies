package com.fmartinez.disney.app.util;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PojoGenerator {
    public static MovieSerie buildMovieModel() {
        return MovieSerie.builder()
                .id(1L)
                .image("Alguna imagen de terror")
                .title("El cuco re loco")
                .createAt(new Date())
                .rate(3)
                .characters(new HashSet<>())
                .gender(new Genre())
                .build();
    }

    public static Character buildCharacterModel() {
        return Character.builder()
                .id(1L)
                .image("Image of a Kilo Ren")
                .name("Kilo Ren")
                .age(30)
                .weight(20.33)
                .story("This is a Story")
                .movies(Set.of(buildMovieModel()))
                .build();
    }

    public static Genre buildGenreModel() {
        return Genre.builder()
                .id(1L)
                .image("Some genre image")
                .name("Horror")
                .movies(Set.of(buildMovieModel()))
                .build();
    }

    public static CharacterDto buildCharacterDtoModel() {
        return CharacterDto.builder()
                .image("Image of a Kilo Ren")
                .name("Kilo Ren")
                .build();
    }

    public static CharacterDetailDto buildCharacterDetail() {
        return CharacterDetailDto.builder()
                .image("Image of a Kilo Ren")
                .name("Kilo Ren")
                .age(30)
                .weight(20.33)
                .story("This is a Story")
                .movies(Set.of(buildMovieModel()))
                .build();
    }

    public static MovieSerieDto buildMovieDtoModel() {
        return MovieSerieDto.builder()
                .image("Image of Star Wars Sith's Vengance")
                .title("Star Wars - Sith's Vengance")
                .createAt(new Date())
                .build();
    }

    public static MovieSerieDetailDto buildMovieDetailModel() {
        return MovieSerieDetailDto
                .builder()
                .image("Some imagen")
                .title("Some title of the movie")
                .rate(4)
                .createAt(new Date())
                .characters(new HashSet<>())
                .gender(new Genre())
                .build();
    }

    public static List<MovieSerie> listOfMovies() {
        return List.of(buildMovieModel(),
                new MovieSerie(2L,
                        "Action Image",
                        "Indiana Johnes",
                        new Date(),
                        4,
                        new HashSet<>(),
                        new Genre()));
    }
}
