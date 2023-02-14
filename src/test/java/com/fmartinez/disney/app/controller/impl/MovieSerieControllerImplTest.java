package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.service.MovieSerieService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static com.fmartinez.disney.app.util.PojoGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieSerieControllerImplTest {

    //TODO: terminaar los tests
    @Mock
    MovieSerieService service;

    @InjectMocks
    MovieSerieControllerImpl controller;

    private static MovieSerie movie;

    private static MovieSerieDto movieDto;

    private static MovieSerieDetailDto movieDetailDto;

    private static Character character;

    @BeforeAll
    static void setUp() {
        movie = buildMovieModel();
        movieDto = buildMovieDtoModel();
        movieDetailDto = buildMovieDetailModel();
        character = buildCharacterModel();
    }


    @Test
    @DisplayName("Test: get all movies return filtered moviesDto [200]")
    void getAllMovies() {
        when(service.getAllMoviesFilter(anyBoolean())).thenReturn(Set.of(movieDto));
        ResponseEntity<Set<MovieSerieDto>> response = controller.getAllMovies(Boolean.FALSE);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(Set.class);

        verify(service).getAllMoviesFilter(anyBoolean());
    }

    @Test
    @DisplayName("Test: get movie detail return movieDetailDto [200]")
    void getMovieDetail() {
        when(service.getMovieSerieDetail(anyLong())).thenReturn(movieDetailDto);
        ResponseEntity<MovieSerieDetailDto> response = controller.getMovieDetail(1L);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(MovieSerieDetailDto.class);

        verify(service).getMovieSerieDetail(anyLong());
    }

    @Test
    @DisplayName("Test: get movie return movieDto [200]")
    void getMovieByName() {
        when(service.getByTitle(anyString())).thenReturn(movieDto);
        ResponseEntity<MovieSerieDto> response = controller.getMovieByName("Star Wars - Sith's Vengance");
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(MovieSerieDto.class);

        verify(service).getByTitle(anyString());
    }

    @Test
    @DisplayName("Test: get movie return movieDto [200]")
    void getMovieByGenderId() {
        when(service.findeMovieSerieByGenderId(anyLong())).thenReturn(Set.of(movieDto));
        ResponseEntity<Set<MovieSerieDto>> response = controller.getMovieByGenderId(1L);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(Set.class);

        verify(service).findeMovieSerieByGenderId(anyLong());
    }

    @Test
    @DisplayName("Test: get movies return list of movieDto [200]")
    void getMovieOrderByCreateAt() {
        when(service.movieSerieOrderByDate(anyString())).thenReturn(List.of(movieDto));
        ResponseEntity<List<MovieSerieDto>> response = controller.getMovieOrderByCreateAt("");
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(List.class);

        verify(service).movieSerieOrderByDate(anyString());
    }

    @Test
    @DisplayName("Test: new movie return movieDto [201]")
    void createMovie() {
        when(service.create(any(MovieSerie.class))).thenReturn(movie);
        ResponseEntity<MovieSerieDto> response = controller.createMovie(movie);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull().isInstanceOf(MovieSerieDto.class);

        verify(service).create(any(MovieSerie.class));
    }

    @Test
    @DisplayName("Test: update movie return movieDto [201]")
    void updateMovie() {
        when(service.update(any(MovieSerie.class), anyLong())).thenReturn(movie);
        ResponseEntity<MovieSerieDto> response = controller.updateMovie(movie, 1L);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull().isInstanceOf(MovieSerieDto.class);

        verify(service).update(any(MovieSerie.class), anyLong());
    }

    @Test
    @DisplayName("Test: delete movie return void [204]")
    void deleteMovie() {
        service.deleteMovieSerie(anyLong());
        ResponseEntity<Void> response = controller.deleteMovie(1L);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("Test: add charcter return movieDetail [201]")
    void addCharacterToMovie() {
//        when(service.addCharacterToMovie(anyLong(), anyLong())).thenReturn(movie);
////        ResponseEntity<MovieSerieDetailDto> response = controller.addCharacterToMovie(movie.getId(), character.getId());
//
//        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
//        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull().isInstanceOf(MovieSerieDetailDto.class);
//
//        verify(service).addCharacterToMovie(anyLong(), anyLong());
    }

    @Test
    @DisplayName("Test: remove character return void [204]")
    void deleteCharacterFromMovie() {
        service.deleteCharacterFromMovie(1L, 1L);
        ResponseEntity<Void> response = controller.deleteCharacterFromMovie(1L, 1L);
        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }
}