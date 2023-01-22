package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.CharacterController;
import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService service;


    public CharacterControllerImpl(final CharacterService service) {
        this.service = service;
    }

    @Override
    public List<CharacterDto> getAllCharacters() {
        return service.getAllCharacters();
    }

    @Override
    public CharacterDetailDto getCharacterById(@RequestParam Long id) {
        return service.getCharacterById(id);
    }

    @Override
    public ResponseEntity<?> getCharacterByName(@RequestParam String name) {
        CharacterDto nameDto = service.getCharacterByName(name);
        return new ResponseEntity<>(nameDto, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> getCharactersByAge(@RequestParam Integer age) {
        List<CharacterDto> dtoSet = service.getCharactersByAge(age);
        return new ResponseEntity<>(dtoSet, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> getCharacterByMovieId(@RequestParam("movies") Long idMovie) {
        Set<CharacterDto> movieIdDto = service.getCharacterByMovieId(idMovie);
        return new ResponseEntity<>(movieIdDto, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> getCharacterByWeight(@RequestParam Double weight) {
        CharacterDto weightDto = service.getCharacterByWeight(weight);
        return new ResponseEntity<>(weightDto, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Character character) {
        Character character1 = service.create(character);
        return new ResponseEntity<>(character1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Character> update(Character character, Long id) {
        Character updateCharacter = service.update(character, id);
        return new ResponseEntity<>(updateCharacter, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deleteCharcter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
