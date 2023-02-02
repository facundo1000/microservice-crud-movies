package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.service.CharacterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static com.fmartinez.disney.app.util.PojoGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterControllerImplTest {

    @Mock
    CharacterService service;

    @InjectMocks
    CharacterControllerImpl characterController;

    private static CharacterDto dto;
    private static CharacterDetailDto detailDto;

    private static Character character;

    @BeforeAll
    static void setUp() {
        dto = buildCharacterDtoModel();
        detailDto = buildCharacterDetail();
        character = buildCharacterModel();
    }

    @Test
    @DisplayName("Test: get all characters (not deleted) [200]")
    void getAllCharacters() {
        when(service.getAllCharactersFiltered(anyBoolean())).thenReturn(Set.of(dto));
        ResponseEntity<Set<CharacterDto>> response = characterController.getAllCharacters(Boolean.FALSE);

        assertCharacter(response);

        verify(service).getAllCharactersFiltered(anyBoolean());
    }

    @Test
    @DisplayName("Test: get character returns CharacterDetailDto [200]")
    void getCharacterById() {
        when(service.getCharacterById(anyLong())).thenReturn(detailDto);
        ResponseEntity<CharacterDetailDto> response = characterController.getCharacterById(1L);

        assertCharacter(response);

        verify(service).getCharacterById(anyLong());
    }

    @Test
    @DisplayName("Test: get character returns CharacterDto [200]")
    void getCharacterByName() {
        when(service.getCharacterByName(anyString())).thenReturn(dto);
        ResponseEntity<CharacterDto> response = characterController.getCharacterByName("Kilo Ren");

        assertCharacter(response);

        verify(service).getCharacterByName(anyString());
    }

    @Test
    @DisplayName("Test: get characters returns Set<CharacterDto> [200]")
    void getCharactersByAge() {
        when(service.getCharactersByAge(anyInt())).thenReturn(Set.of(dto));
        ResponseEntity<Set<CharacterDto>> response = characterController.getCharactersByAge(46);

        assertCharacter(response);

        verify(service).getCharactersByAge(anyInt());
    }

    @Test
    @DisplayName("Test: get character returns Set<CharacterDto> [200]")
    void getCharacterByMovieId() {
        when(service.getCharacterByMovieId(anyLong())).thenReturn(Set.of(dto));
        ResponseEntity<Set<CharacterDto>> response = characterController.getCharacterByMovieId(1L);
        assertCharacter(response);
        verify(service).getCharacterByMovieId(anyLong());
    }

    @Test
    @DisplayName("Test: get character returns CharacterDto [200]")
    void getCharacterByWeight() {
        when(service.getCharacterByWeight(anyDouble())).thenReturn(dto);
        ResponseEntity<CharacterDto> response = characterController.getCharacterByWeight(20.23);
        verify(service).getCharacterByWeight(anyDouble());
    }

    @Test
    @DisplayName("Test: new character return CharacterDto [201]")
    void create() {
        when(service.create(any(Character.class))).thenReturn(character);
        ResponseEntity<CharacterDto> response = characterController.create(character);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        verify(service).create(any(Character.class));
    }

    @Test
    @DisplayName("Test: update character return CharacterDto [200]")
    void update() {
        when(service.update(any(Character.class), anyLong())).thenReturn(character);
        ResponseEntity<CharacterDto> response = characterController.update(character, 1L);
        assertCharacter(response);
        verify(service).update(any(Character.class), anyLong());
    }

    @Test
    @DisplayName("Test: delete character return void response [204]")
    void delete() {
        ResponseEntity<Void> response = characterController.delete(1L);
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    private static void assertCharacter(ResponseEntity<?> response) {
        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}