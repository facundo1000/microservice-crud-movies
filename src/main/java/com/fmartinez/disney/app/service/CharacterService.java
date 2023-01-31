package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    List<Character> getAllCharacters();

    CharacterDetailDto getCharacterById(Long id);

    CharacterDto getCharacterByName(String name);

    Set<CharacterDto> getCharactersByAge(Integer age);

    Set<CharacterDto> getCharacterByMovieId(Long id);

    CharacterDto getCharacterByWeight(Double weigth);

    Character create(Character character);

    Character update(Character character, Long id);

    void deleteCharcter(Long id);

    Set<CharacterDto> getAllCharactersFiltered(boolean isDeleted);
}
