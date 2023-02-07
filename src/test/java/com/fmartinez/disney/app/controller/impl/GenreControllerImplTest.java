package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.dto.GenreDto;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.service.GenreService;
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

import static com.fmartinez.disney.app.util.PojoGenerator.buildGenreDto;
import static com.fmartinez.disney.app.util.PojoGenerator.buildGenreModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreControllerImplTest {

    @Mock
    GenreService service;
    @InjectMocks
    GenreControllerImpl controller;
    private static Genre genre;
    private static GenreDto genreDto;

    @BeforeAll
    static void setUp() {
        genreDto = buildGenreDto();
        genre = buildGenreModel();
    }


    @Test
    @DisplayName("Test: get all characters filtered [200]")
    void getAllGeneres() {
        when(service.findAllGenres(anyBoolean())).thenReturn(List.of(genreDto));
        ResponseEntity<List<GenreDto>> response = controller.getAllGeneres(Boolean.FALSE);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty().isNotNull();

    }

    @Test
    @DisplayName("Test: new genre return CharacterDto [201]")
    void create() {
        when(service.create(any(Genre.class))).thenReturn(genre);
        ResponseEntity<GenreDto> response = controller.create(genre);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Test: update genre return GenreDto [201]")
    void update() {
        when(service.update(any(Genre.class), anyLong())).thenReturn(genre);
        ResponseEntity<GenreDto> response = controller.update(genre, 1L);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isExactlyInstanceOf(GenreDto.class);
    }

    @Test
    @DisplayName("Test: delete genre return void [204]")
    void delete() {
        service.delete(anyLong());
        ResponseEntity<Void> response = controller.delete(1L);
        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("Test: add genre to movie return [201]")
    void addGenreToMovie() {
        when(service.addGenreToMovie(anyLong(),anyLong())).thenReturn(genre);
        ResponseEntity<GenreDto> response = controller.addGenreToMovie(1L,1L);
        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull().isInstanceOf(GenreDto.class);
    }

    @Test
    @DisplayName("Test: remove genre from movie return [204]")
    void removeGenreToMovie() {
        service.removeGenreToMovie(anyLong(),anyLong());
        ResponseEntity<Void> response = controller.removeGenreToMovie(1L,1L);
        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }
}