package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.MovieSerie;

import java.util.List;
import java.util.Set;

public interface MovieSerieService {

    List<MovieSerieDto> getAllMovies();

    MovieSerieDetailDto getMovieSerieDetail(Long id);

    MovieSerieDto getByTitle(String title);


    Set<MovieSerieDto> findeMovieSerieByGenderId(Long id);

    //List<MovieSerieDto> movieSerieByCreateAtOrderBy(Date date, Sort sort);

    MovieSerie create(MovieSerie movieSerie);

    MovieSerie update(MovieSerie movieSerie, Long id);

    void deleteMovieSerie(Long id);

    MovieSerie addCharacterToMovie(Long idMovie, Long idCharacter);

    void deleteCharacterFromMovie(Long idMovie, Long idCharacter);


}
