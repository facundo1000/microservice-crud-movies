package com.fmartinez.disney.app.mapper;

import com.fmartinez.disney.app.dto.*;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.model.MovieSerie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    CharacterDto characterToCharacterDto(Character character);

    CharacterDetailDto characterToCharacterDetail(Character character);

    MovieSerieDto movieSerieToMovieSerieDto(MovieSerie movie);

    MovieSerieDetailDto movieSerieDetail(MovieSerie movie);

    GenreDto genreToGenreDto(Genre genre);

}
