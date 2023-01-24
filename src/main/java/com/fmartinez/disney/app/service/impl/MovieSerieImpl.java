package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.MovieSerie;
import com.fmartinez.disney.app.repository.CharacterRepository;
import com.fmartinez.disney.app.repository.MovieSerieRepository;
import com.fmartinez.disney.app.service.MovieSerieService;
import com.fmartinez.disney.app.util.ErrorType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieSerieImpl implements MovieSerieService {

    private final CharacterRepository characterRepository;
    private final MovieSerieRepository repository;
    private final MapStructMapper mapper;

    @Override
    public List<MovieSerieDto> getAllMovies() {
        return repository.findAll().stream()
                .map(mapper::movieSerieToMovieSerieDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieSerieDetailDto getMovieSerieDetail(Long id) {
        return repository
                .findById(id)
                .map(mapper::movieSerieDetail)
                .orElseThrow(() -> new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND));
    }

    @Override
    public MovieSerieDto getByTitle(String name) {
        return repository.findByTitleIgnoreCase(name)
                .map(mapper::movieSerieToMovieSerieDto)
                .orElseThrow(() -> new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND));
    }

    @Override
    public Set<MovieSerieDto> findeMovieSerieByGenderId(Long id) {
        return repository.findByGenderId(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND))
                .stream()
                .map(mapper::movieSerieToMovieSerieDto)
                .collect(Collectors.toSet());
    }

    @Override
    public List<MovieSerieDto> movieSerieOrderByDate(String sort) {
        List<MovieSerieDto> dtoList;

        if (sort != null && !sort.startsWith(" ")) {
            dtoList = switch (sort) {

                case "ASC", "asc" -> repository
                        .findAll(Sort.by("createAt").ascending())
                        .stream()
                        .map(mapper::movieSerieToMovieSerieDto)
                        .toList();

                case "DESC", "desc" -> repository
                        .findAll(Sort.by("createAt").descending())
                        .stream()
                        .map(mapper::movieSerieToMovieSerieDto)
                        .toList();

                default -> repository
                        .findAll(Sort.by("createAt").descending())
                        .stream()
                        .map(mapper::movieSerieToMovieSerieDto)
                        .toList();
            };
            return dtoList;
        }
        throw new NotFoundException(ErrorType.NOT_FOUND_EXCEPTION, "String 'DESC' or 'ASC not found'");
    }

    @Override
    public MovieSerie create(MovieSerie movieSerie) {
        return repository.save(movieSerie);
    }

    //TODO: arreglar
    @Override
    public MovieSerie update(MovieSerie movieSerie, Long id) {

        if (id > 0) {
            Optional<MovieSerie> actual = repository.findById(id);

            if (actual.isPresent()) {

                Set<Character> characterSet = actual.get().getCharacters();

                actual.get().setImage(movieSerie.getImage());
                actual.get().setTitle(movieSerie.getTitle());
                actual.get().setCreateAt(movieSerie.getCreateAt());
                actual.get().setRate(movieSerie.getRate());
                actual.get().setCharacters(characterSet);
                actual.get().setGender(movieSerie.getGender());

                return repository.save(actual.get());
            }
        }
        throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND);
    }

    @Override
    public void deleteMovieSerie(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND))
                .removeCharacters();
        repository.deleteById(id);
    }


    @Override
    public MovieSerie addCharacterToMovie(Long idMovie, Long idCharacter) {

        Optional<MovieSerie> serieOptional = repository.findById(idMovie);
        Optional<Character> characterOptional = characterRepository.findById(idCharacter);

        if (serieOptional.isPresent() && characterOptional.isPresent()) {
            serieOptional.get().addCharacter(characterOptional.get());
            characterOptional.get().addMovieSerie(serieOptional.get());
            return repository.save(serieOptional.get());
        }

        throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND);
    }

    @Override
    public void deleteCharacterFromMovie(Long idMovie, Long idCharacter) {

        Optional<MovieSerie> serieOptional = repository.findById(idMovie);
        Optional<Character> characterOptional = characterRepository.findById(idCharacter);

        if (serieOptional.isPresent() && characterOptional.isPresent()) {

            serieOptional.get().removeCharacter(characterOptional.get());
            characterOptional.get().removeMovie(serieOptional.get());

            repository.save(serieOptional.get());
        } else {
            throw new NotFoundException(ErrorType.MOVIE_SERIE_NOT_FOUND);
        }
    }

}
