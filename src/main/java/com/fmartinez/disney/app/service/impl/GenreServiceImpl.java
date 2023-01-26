package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.repository.GenreRepository;
import com.fmartinez.disney.app.repository.MovieSerieRepository;
import com.fmartinez.disney.app.service.GenreService;
import com.fmartinez.disney.app.util.ErrorType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    private final MovieSerieRepository movieRepo;

    @Override
    public List<Genre> getAllGenre() {
        return (List<Genre>) repository.findAll();
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
            throw new NotFoundException(ErrorType.GENRE_NOT_FOUND, "genre not found");
        }
        throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND, "movie not present");

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
}
