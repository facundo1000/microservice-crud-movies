package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.swagger.find.ResponseFindCharacter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fmartinez.disney.app.constant.ApplicationConstant.CHARACTER_PATH;

@RequestMapping(CHARACTER_PATH)
public interface CharacterController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ResponseFindCharacter
    List<CharacterDto> getAllCharacters();

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(params = "id")
    CharacterDetailDto getCharacterById(@RequestParam Long id);

    @GetMapping(params = "name")
    ResponseEntity<?> getCharacterByName(@RequestParam String name);

    @GetMapping(params = "age")
    ResponseEntity<?> getCharactersByAge(@RequestParam Integer age);

    @GetMapping(params = "movies")
    ResponseEntity<?> getCharacterByMovieId(@RequestParam Long movies);

    @GetMapping(params = "weight")
    ResponseEntity<?> getCharacterByWeight(@RequestParam Double weight);

    @PostMapping
    ResponseEntity<?> create(@RequestBody Character character);

    @PutMapping(params = "id")
    ResponseEntity<Character> update(@RequestBody Character character, @RequestParam Long id);

    @DeleteMapping(params = "id")
    ResponseEntity<Void> delete(@RequestParam Long id);
}
