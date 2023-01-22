package com.fmartinez.disney.app.mapper;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.MovieSerie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    CharacterDto characterToCharacterDto(Character character);

    CharacterDetailDto characterToCharacterDetail(Character character);

    MovieSerieDto movieSerieToMovieSerieDto(MovieSerie movie);

    MovieSerieDetailDto movieSerieDetail(MovieSerie movie);
}
