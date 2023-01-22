package com.fmartinez.disney.app.repository;

import com.fmartinez.disney.app.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByNameIgnoreCase(String name);

    Optional<List<Character>> findByAge(Integer age);

    Optional<Character> findByWeight(Double weight);

    Optional<Set<Character>> findCharacterByMoviesId(Long id);
}
