package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.repository.CharacterRepository;
import com.fmartinez.disney.app.service.CharacterService;
import com.fmartinez.disney.app.util.ErrorType;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {


    private final CharacterRepository repository;
    private final MapStructMapper mapper;

    private final EntityManager manager;

    @Override
    public List<Character> getAllCharacters() {
        return repository.findAll();
    }


    @Override
    public CharacterDetailDto getCharacterById(Long id) {
        return repository.findById(id)
                .map(mapper::characterToCharacterDetail)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND));
    }

    @Override
    public CharacterDto getCharacterByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .map(mapper::characterToCharacterDto)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND));
    }

    @Override
    public Set<CharacterDto> getCharactersByAge(Integer age) {
        return repository.findByAge(age)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND))
                .stream().map(mapper::characterToCharacterDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CharacterDto> getCharacterByMovieId(Long id) {
        return repository.findCharacterByMoviesId(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND))
                .stream()
                .map(mapper::characterToCharacterDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CharacterDto getCharacterByWeight(Double weigth) {
        return repository
                .findByWeight(weigth)
                .map(mapper::characterToCharacterDto)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND));
    }

    @Override
    public Character create(Character character) {
        return repository.save(character);
    }

    @Override
    public Character update(Character character, Long id) {
        if (id > 0) {

            Optional<Character> characterNew = repository.findById(id);

            if (characterNew.isPresent()) {

                characterNew.get().setImage(character.getImage());
                characterNew.get().setName(character.getName());
                characterNew.get().setAge(character.getAge());
                characterNew.get().setWeight(character.getWeight());
                characterNew.get().setStory(character.getStory());
                characterNew.get().setMovies(character.getMovies());

                return repository.save(characterNew.get());
            }
        }
        throw new NotFoundException(ErrorType.CHARACTER_NOT_FOUND);
    }

    @Override
    public void deleteCharcter(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND))
                .removeMovies();
        repository.deleteById(id);
    }

    @Override
    public Set<CharacterDto> getAllCharactersFiltered(boolean isDeleted) {
        Session session = manager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCharacterFilter");
        filter.setParameter("isDeleted", isDeleted);
        Set<CharacterDto> characterDtoSet = repository.findAll()
                .stream()
                .map(mapper::characterToCharacterDto)
                .collect(Collectors.toSet());
        session.disableFilter("deletedCharacterFilter");
        return characterDtoSet;

    }
}
