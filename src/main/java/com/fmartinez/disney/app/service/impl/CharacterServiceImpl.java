package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.exception.NotFoundException;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.repository.CharacterRepository;
import com.fmartinez.disney.app.service.CharacterService;
import com.fmartinez.disney.app.util.ErrorType;
import lombok.AllArgsConstructor;
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

    @Override
    public List<CharacterDto> getAllCharacters() {
        return repository.findAll()
                .stream()
                .map(mapper::characterToCharacterDto)
                .collect(Collectors.toList());
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
    public List<CharacterDto> getCharactersByAge(Integer age) {
        return repository.findByAge(age)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND))
                .stream().map(mapper::characterToCharacterDto)
                .collect(Collectors.toList());
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
                characterNew.get().addMovieSerie(character.getMovies().iterator().next());
                return repository.save(characterNew.get());
            }
        } else {
            throw new NotFoundException(ErrorType.CHARACTER_NOT_FOUND);
        }
        return repository.save(character);
    }

    @Override
    public void deleteCharcter(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorType.CHARACTER_NOT_FOUND))
                .removeMovies();
        repository.deleteById(id);
    }

    /**
     * Java.lang.NullPointerException: Cannot invoke "java.util.Set.forEach(java.util.function.Consumer)"
     * because the return value of "com.fmartinez.disney.app.dto.CharacterDetailDto.getMovies()" is null
     */
//    private void mapDtotoEntity(CharacterDetailDto dto, Character characte) {
//        characte.setName(dto.getName());
//        if (characte.getMovies() == null) {
//            characte.setMovies(new HashSet<>());
//        }
//        dto.getMovies().forEach(m -> {
//            Optional<MovieSerie> movie = movieRepo.findByTitleIgnoreCase(m.getTitle());
//            if (movie.isEmpty()) {
//                movie = Optional.of(new MovieSerie());
//                movie.get().setCharacters(new HashSet<>());
//            }
//            movie.get().setTitle(m.getTitle());
//            characte.addMovieSerie(movie.get());
//        });
//    }
    private CharacterDetailDto mapEntityToDto(Character character) {
        CharacterDetailDto characterDto = new CharacterDetailDto();
        characterDto.setImage(character.getImage());
        characterDto.setName(character.getName());
        characterDto.setAge(character.getAge());
        characterDto.setWeight(character.getWeight());
        characterDto.setStory(character.getStory());
        characterDto.setMovies(character.getMovies());
        return characterDto;
    }
}
