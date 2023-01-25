package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.repository.CharacterRepository;
import com.fmartinez.disney.app.repository.MovieSerieRepository;
import com.fmartinez.disney.app.service.impl.MovieSerieImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.Set;

import static com.fmartinez.disney.app.util.PojoGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieSerieImpTest {

    @Mock
    MovieSerieRepository movieRepository;

    @Mock
    CharacterRepository characterRepository;

    @Mock
    MapStructMapper mapper;

    @InjectMocks
    MovieSerieImpl movieService;

    private static MovieSerieDto expectedResponse;

    private static MovieSerieDetailDto expectedDetail;

    private static MovieSerie movie;

    private static Character character;

    @BeforeAll
    static void setup() {
        movie = buildMovieModel();
        expectedResponse = buildMovieDtoModel();
        expectedDetail = buildMovieDetailModel();
        character = buildCharacterModel();
    }


    @Test
    @DisplayName("Test: Get All Movies")
    void getAllMoviesReturnMovieResponse() {
        when(movieRepository.findAll()).thenReturn(listOfMovies());
        when(mapper.movieSerieToMovieSerieDto(any(MovieSerie.class))).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.getAllMovies().iterator().next());

        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Test: get a movie detail by ID")
    void getMovieShouldReturnMovieResponse() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.ofNullable(movie));
        when(mapper.movieSerieDetail(any(MovieSerie.class))).thenReturn(expectedDetail);

        assertionsForMovieDetail(movieService.getMovieSerieDetail(1L));

        verify(movieRepository).findById(anyLong());
        verify(mapper).movieSerieDetail(any(MovieSerie.class));
    }

    @Test
    @DisplayName("Test: get a movie by name")
    void getMovieByTitle() {
        when(movieRepository.findByTitleIgnoreCase(anyString())).thenReturn(Optional.ofNullable(movie));
        when(mapper.movieSerieToMovieSerieDto(any())).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.getByTitle(anyString()));

        verify(movieRepository).findByTitleIgnoreCase(anyString());
    }

    @Test
    @DisplayName("Test: get a movie by gender id")
    void getMovieByGenderId() {
        when(movieRepository.findByGenderId(anyLong())).thenReturn(Optional.of(Set.of(movie)));
        when(mapper.movieSerieToMovieSerieDto(any())).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.findeMovieSerieByGenderId(1L).iterator().next());

        verify(movieRepository).findByGenderId(anyLong());
    }

    @Test
    @DisplayName("Test: get all movies order by date")
    void getAllMoviesOrderByDate() {
        when(movieRepository.findAll(Sort.by("createAt").descending())).thenReturn(listOfMovies());
        when(movieRepository.findAll(Sort.by("createAt").ascending())).thenReturn(listOfMovies());
        when(mapper.movieSerieToMovieSerieDto(any(MovieSerie.class))).thenReturn(expectedResponse);

        assertThat(expectedResponse).isEqualTo(movieService.movieSerieOrderByDate("asc").iterator().next());
        assertThat(expectedResponse).isEqualTo(movieService.movieSerieOrderByDate("desc").iterator().next());

        verify(movieRepository).findAll(Sort.by("createAt").ascending());
        verify(movieRepository).findAll(Sort.by("createAt").descending());
    }

    @Test
    @DisplayName("Test: create a new movie-serie")
    void createNewMovie() {
        when(movieRepository.save(any(MovieSerie.class))).thenReturn(movie);
        assertionsForMovieSerie(movieService.create(movie));
        verify(movieRepository).save(any(MovieSerie.class));
    }

    @Test
    @DisplayName("Test: update an existing movie-serie")
    void updateMovieSerie() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(MovieSerie.class))).thenReturn(movie);

        movie.addCharacter(character);

        assertionsForMovieSerie(movieService.update(movie, 1L));

        verify(movieRepository).findById(anyLong());
        verify(movieRepository).save(any(MovieSerie.class));
    }

    @Test
    @DisplayName("Test: delete an existing movie-serie")
    void deleteMovieSerie() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        movieService.deleteMovieSerie(1L);
        verify(movieRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Test: add an existing character into an existing movie")
    void addCharacterToMovie() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        when(characterRepository.findById(anyLong())).thenReturn(Optional.of(character));
        when(movieRepository.save(any(MovieSerie.class))).thenReturn(movie);

        movie.addCharacter(character);
        character.addMovieSerie(movie);

        assertionsForMovieSerie(movieService.addCharacterToMovie(movie.getId(), character.getId()));

        assertThat(movie.getCharacters()).hasSize(1).containsOnly(character);
        assertThat(character.getMovies()).hasSize(1).containsOnly(movie);

        assertionsForMovieSerie(movie);

        verify(movieRepository).findById(anyLong());
        verify(characterRepository).findById(anyLong());
        verify(movieRepository).save(any(MovieSerie.class));
    }

    @Test
    @DisplayName("Test: remove existing character from an existing movie")
    void removeChararcters() {
        addCharacterToMovie();

        movie.removeCharacters();
        character.removeMovies();

        assertThat(movie.getCharacters()).hasSize(0);
        assertThat(character.getMovies()).hasSize(0);
    }

    private void allAssertionsForTest(MovieSerieDto movieResponse) {
        assertThat(movieResponse).isNotNull();
        assertThat(movieResponse.getImage()).isEqualTo(expectedResponse.getImage());
        assertThat(movieResponse.getTitle()).isEqualTo(expectedResponse.getTitle());
        assertThat(movieResponse.getCreateAt()).isEqualToIgnoringHours(expectedResponse.getCreateAt());
    }

    private void assertionsForMovieDetail(MovieSerieDetailDto movieDetail) {
        assertThat(movieDetail).isNotNull();
        assertThat(movieDetail).isInstanceOf(MovieSerieDetailDto.class);
        assertThat(movieDetail.getImage()).isEqualTo(expectedDetail.getImage());
        assertThat(movieDetail.getTitle()).isEqualTo(expectedDetail.getTitle());
        assertThat(movieDetail.getRate()).isEqualTo(expectedDetail.getRate());
        assertThat(movieDetail.getCreateAt()).isEqualTo(expectedDetail.getCreateAt());
        assertThat(movieDetail.getCharacters()).isEqualTo(expectedDetail.getCharacters());
        assertThat(movieDetail.getGender()).isEqualTo(expectedDetail.getGender());
    }

    private void assertionsForMovieSerie(MovieSerie movieSerie) {
        assertThat(movieSerie).isNotNull();
        assertThat(movieSerie.getId()).isEqualTo(movie.getId());
        assertThat(movieSerie.getImage()).isEqualTo(movie.getImage());
        assertThat(movieSerie.getTitle()).isEqualTo(movie.getTitle());
        assertThat(movieSerie.getRate()).isEqualTo(movie.getRate());
        assertThat(movieSerie.getCreateAt()).isEqualTo(movie.getCreateAt());
        assertThat(movieSerie.getCharacters()).isEqualTo(movie.getCharacters());
        assertThat(movieSerie.getGender()).isEqualTo(movie.getGender());
    }

}
