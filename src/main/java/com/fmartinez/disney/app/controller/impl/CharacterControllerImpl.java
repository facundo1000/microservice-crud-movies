package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.CharacterController;
import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CharacterControllerImpl implements CharacterController {
    private final CharacterService service;

    public CharacterControllerImpl(final CharacterService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Set<CharacterDto>> getAllCharacters(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted) {
        Set<CharacterDto> dtoList = service.getAllCharactersFiltered(isDeleted);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CharacterDetailDto> getCharacterById(@PathVariable(required = false) Long id) {
        CharacterDetailDto detailDto =   service.getCharacterById(id);
        return new ResponseEntity<>(detailDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CharacterDto> getCharacterByName(@PathVariable String name) {
        CharacterDto nameDto = service.getCharacterByName(name);
        return new ResponseEntity<>(nameDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<CharacterDto>> getCharactersByAge(@PathVariable Integer age) {
        Set<CharacterDto> dtoSet = service.getCharactersByAge(age);
        return new ResponseEntity<>(dtoSet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<CharacterDto>> getCharacterByMovieId(@PathVariable Long id) {
        Set<CharacterDto> movieIdDto = service.getCharacterByMovieId(id);
        return new ResponseEntity<>(movieIdDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CharacterDto> getCharacterByWeight(@PathVariable Double weight) {
        CharacterDto weightDto = service.getCharacterByWeight(weight);
        return new ResponseEntity<>(weightDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CharacterDto> create(@Valid @RequestBody Character character) {
        Character character1 = service.create(character);
        CharacterDto dto = new CharacterDto(character1.getImage(), character1.getName());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CharacterDto> update(@Valid @RequestBody Character character, @PathVariable Long id) {
        Character update = service.update(character, id);
        CharacterDto dto = new CharacterDto(update.getImage(), update.getName());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCharcter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
