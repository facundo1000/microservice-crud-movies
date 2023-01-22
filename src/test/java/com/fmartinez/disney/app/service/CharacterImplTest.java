package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.mapper.MapStructMapper;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.repository.CharacterRepository;
import com.fmartinez.disney.app.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.fmartinez.disney.app.util.PojoGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterImplTest {

    @Mock
    CharacterRepository repository;

    @Mock
    MapStructMapper mapper;

    @InjectMocks
    CharacterServiceImpl service;

    private static CharacterDto characterDto;

    private static CharacterDetailDto characterDetail;

    private static Character character;


    @BeforeAll
    static void setUp() {
        characterDto = buildCharacterDtoModel();
        characterDetail = buildCharacterDetail();
        character = buildCharacterModel();
    }

    @Test
    @DisplayName("Test: get all Characters")
    void getAllCharactersReturnResponse() {
        lenient().when(repository.findAll()).thenReturn(List.of(character));
        lenient().when(mapper.characterToCharacterDto(character)).thenReturn(characterDto);

        allAssertionsForTest(service.getAllCharacters().iterator().next());
    }

    @Test
    @DisplayName("Test: get character by id")
    void getCharacterByIdReturnResponse() {
        lenient().when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(character));
        lenient().when(mapper.characterToCharacterDetail(any())).thenReturn(characterDetail);

        allAssertionsForTestsDetail(service.getCharacterById(1L));
    }

    @Test
    @DisplayName("Test: get character by age")
    void getCharacterByAgeReturnResponse() {
        when(repository.findByAge(anyInt())).thenReturn(Optional.of(List.of(character)));
        when(mapper.characterToCharacterDto(any())).thenReturn(characterDto);

        allAssertionsForTest(service.getCharactersByAge(23).iterator().next());
    }


    private void allAssertionsForTest(CharacterDto characterDto) {
        assertThat(characterDto).isNotNull();
        assertThat(characterDto.getImage()).isEqualTo(character.getImage());
        assertThat(characterDto.getName()).isEqualTo(character.getName());
    }

    private void allAssertionsForTestsDetail(CharacterDetailDto detailDto) {
        assertThat(detailDto).isNotNull();
        assertThat(detailDto.getImage()).isEqualTo(character.getImage());
        assertThat(detailDto.getName()).isEqualTo(character.getName());
        assertThat(detailDto.getAge()).isEqualTo(character.getAge());
        assertThat(detailDto.getWeight()).isEqualTo(character.getWeight());
        assertThat(detailDto.getStory()).isEqualTo(character.getStory());
        assertThat(detailDto.getMovies()).isEqualTo(character.getMovies());
    }

}
