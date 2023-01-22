package com.fmartinez.disney.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmartinez.disney.app.model.MovieSerie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterDetailDto {
    @JsonProperty(required = true)
    private String image;
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private Integer age;
    @JsonProperty(required = true)
    private Double weight;
    @JsonProperty(required = true)
    private String story;
    private Set<MovieSerie> movies;
}
