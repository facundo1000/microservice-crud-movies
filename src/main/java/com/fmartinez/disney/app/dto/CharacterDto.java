package com.fmartinez.disney.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterDto {
    @JsonPropertyDescription("Character image")
    @JsonProperty(required = true)
    private String image;

    @JsonPropertyDescription("Character name")
    @JsonProperty(required = true)
    private String name;

}
