package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.swagger.delete.ResponseDeleteCharacter;
import com.fmartinez.disney.app.swagger.find.character.*;
import com.fmartinez.disney.app.swagger.save.character.ResponseSaveCharacter;
import com.fmartinez.disney.app.swagger.save.character.ResponseUpdateCharacter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.fmartinez.disney.app.constant.ApplicationConstant.CHARACTER_PATH;

@Tag(name = "Character Controller", description = "Character side operations")
@RequestMapping(CHARACTER_PATH)
public interface CharacterController {

    @GetMapping
    @ResponseFindCharacter
    ResponseEntity<Set<CharacterDto>> getAllCharacters(@RequestParam(defaultValue = "false", required = false) Boolean isDeleted);

    @GetMapping(value = "/details/{id}")
    @ResponseFindCharacterDetails
    ResponseEntity<CharacterDetailDto> getCharacterById(@PathVariable Long id);

    @GetMapping("/name/{name}")
    @ResponseFindCharacterByName
    ResponseEntity<?> getCharacterByName(@PathVariable String name);

    @GetMapping("/age/{age}")
    @ResponseFindCharacterByAge
    ResponseEntity<?> getCharactersByAge(@PathVariable Integer age);

    @GetMapping("/idmovie/{id}")
    @ResponseFindCharacterMovieId
    ResponseEntity<?> getCharacterByMovieId(@PathVariable Long id);

    @GetMapping("/weight/{weight}")
    @ResponseFindCharacterByWeight
    ResponseEntity<?> getCharacterByWeight(@PathVariable Double weight);

    @PostMapping
    @ResponseSaveCharacter
    ResponseEntity<CharacterDto> create(@Valid @RequestBody Character character);

    @PutMapping("/update/{id}")
    @ResponseUpdateCharacter
    ResponseEntity<CharacterDto> update(@Valid @RequestBody Character character, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    @ResponseDeleteCharacter
    ResponseEntity<Void> delete(@PathVariable Long id);
}
