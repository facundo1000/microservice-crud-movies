package com.fmartinez.disney.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmartinez.disney.app.model.Character;
import com.fmartinez.disney.app.model.Genre;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieSerieDetailDto {
    @JsonProperty(required = true)
    private String image;
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createAt;
    @JsonProperty(required = true)
    private Integer rate;
    @JsonProperty(required = true)
    private Set<Character> characters;
    @JsonProperty(required = true)
    private Genre gender;
}
