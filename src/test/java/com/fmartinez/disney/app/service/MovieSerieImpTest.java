package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.repository.MovieSerieRepository;
import com.fmartinez.disney.app.service.impl.MovieSerieImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    MapStructMapper mapper;

    @InjectMocks
    MovieSerieImpl movieService;

    private static MovieSerieDto expectedResponse;

    private static MovieSerieDetailDto expectedDetail;

    private static MovieSerie movie;

    @BeforeAll
    static void setup() {
        movie = buildMovieModel();
        expectedResponse = buildMovieDtoModel();
        expectedDetail = buildMovieDetailModel();
    }


    @Test
    @DisplayName("Test: Get All Movies")
    void getAllMoviesReturnMovieResponse() {
        lenient().when(movieRepository.findAll()).thenReturn(listOfMovies());
        lenient().when(mapper.movieSerieToMovieSerieDto(any())).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.getAllMovies().iterator().next());
    }

    @Test
    @DisplayName("Test: get a movie detail by ID")
    void getMovieShouldReturnMovieResponse() {
        lenient().when(movieRepository.findById(anyLong())).thenReturn(Optional.ofNullable(movie));
        lenient().when(mapper.movieSerieDetail(any())).thenReturn(expectedDetail);

        assertionsForMovieDetail(movieService.getMovieSerieDetail(1L));
    }

    @Test
    @DisplayName("Test: get a movie by name")
    void getMovieByTitle() {
        lenient().when(movieRepository.findByTitleIgnoreCase(anyString())).thenReturn(Optional.ofNullable(movie));
        lenient().when(mapper.movieSerieToMovieSerieDto(any())).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.getByTitle(anyString()));
    }

    @Test
    @DisplayName("Test: get a movie by gender id")
    void getMovieByGenderId() {
        lenient().when(movieRepository.findByGenderId(anyLong())).thenReturn(Optional.of(Set.of(buildMovieModel())));
        lenient().when(mapper.movieSerieToMovieSerieDto(any())).thenReturn(expectedResponse);

        allAssertionsForTest(movieService.findeMovieSerieByGenderId(1L).iterator().next());
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

}
