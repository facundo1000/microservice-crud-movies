package com.fmartinez.disney.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreDto {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String image;

}
