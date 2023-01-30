package com.fmartinez.disney.app.service;


import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.repository.GenreRepository;
import com.fmartinez.disney.app.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.fmartinez.disney.app.util.PojoGenerator.buildGenreModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreTest {

    @Mock
    GenreRepository repository;

    @InjectMocks
    GenreServiceImpl service;

    private static Genre genre;

    @BeforeAll
    static void setUp() {
        genre = buildGenreModel();
    }

    @Test
    @DisplayName("Test: display all genres")
    void getAllTheGenres() {
        when(repository.findAll()).thenReturn(List.of(genre));
        allAssertionGenre(service.getAllGenre().iterator().next());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Test: create a new genre")
    void createNewGenre() {
        when(repository.save(any(Genre.class))).thenReturn(genre);
        allAssertionGenre(service.create(genre));
        verify(repository).save(any(Genre.class));
    }
    @Test
    @DisplayName("Test: update an existing genre")
    void updateGenre(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(genre));
        when(repository.save(any(Genre.class))).thenReturn(genre);

        allAssertionGenre(service.update(genre,1L));

        verify(repository).findById(anyLong());
        verify(repository).save(any(Genre.class));
    }

    @Test
    @DisplayName("Test: delete an existing genre by id")
    void deleteGenre() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(genre));
        service.delete(1L);

        verify(repository).findById(anyLong());
        verify(repository).deleteById(anyLong());
    }


    private void allAssertionGenre(Genre genreIn) {
        assertThat(genreIn).isNotNull();
        assertThat(genreIn.getId()).isEqualTo(genre.getId());
        assertThat(genreIn.getImage()).isEqualTo(genre.getImage());
        assertThat(genreIn.getName()).isEqualTo(genre.getName());
        assertThat(genreIn.getMovies()).isEqualTo(genre.getMovies());
    }

}
