package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.dto.GenreDto;
import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.repository.GenreRepository;
import com.fmartinez.disney.app.repository.MovieSerieRepository;
import com.fmartinez.disney.app.service.GenreService;
import com.fmartinez.disney.app.util.ErrorType;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    private final MovieSerieRepository movieRepo;
    private final MapStructMapper mapper;
    private final EntityManager manager;

    @Override
    public List<Genre> getAllGenre() {
        return repository.findAll();
    }

    @Override
    public Genre create(Genre genre) {
        return repository.save(genre);
    }


    @Override
    public Genre update(Genre genre, Long id) {

        if (id > 0) {
            Optional<Genre> actual = repository.findById(id);

            if (actual.isPresent()) {
                actual.get().setImage(genre.getImage());
                actual.get().setName(genre.getName());
                actual.get().setMovies(genre.getMovies());

                return repository.save(actual.get());
            }
        }
        throw new NotFoundException(ErrorType.GENRE_NOT_FOUND, "Cannot be updated");
    }

    @Override
    public void delete(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.GENRE_NOT_FOUND, "Cannot be found"))
                .removesMovies();
        repository.deleteById(id);

    }

    @Override
    public Genre addGenreToMovie(Long idGenre, Long idMovie) {

        Optional<MovieSerie> movie = movieRepo.findById(idMovie);
        Optional<Genre> genre = repository.findById(idGenre);
        if (genre.isPresent()) {
            if (movie.isPresent()) {
                movie.get().setGender(genre.get());
                genre.get().addMovieSerie(movie.get());
                return repository.save(genre.get());
            }
            throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND, "movie not found");
        }
        throw new NotFoundException(ErrorType.GENRE_NOT_FOUND, "genre not found");

    }

    @Override
    public void removeGenreToMovie(Long idGenre, Long idMovie) {
        Optional<MovieSerie> movie = movieRepo.findById(idMovie);
        Optional<Genre> genre = repository.findById(idGenre);

        if (genre.isPresent()) {
            if (movie.isPresent()) {
                movie.get().setGender(null);
                genre.get().removeMovie(movie.get());
                repository.save(genre.get());
                return;
            }
            throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND, "movie not present");
        }
        throw new NotFoundException(ErrorType.GENRE_NOT_FOUND, "genre not found");
    }

    @Override
    public List<GenreDto> findAllGenres(boolean isDeleted) {
        Session session = manager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedGenreFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<GenreDto> genreList = repository.findAll()
                .stream()
                .map(mapper::genreToGenreDto)
                .collect(Collectors.toList());
        session.disableFilter("deletedGenreFilter");
        return genreList;
    }
}
