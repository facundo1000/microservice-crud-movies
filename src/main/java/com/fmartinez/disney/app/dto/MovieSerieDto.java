package com.fmartinez.disney.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//TODO: implementar DTO MoviesSeries
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieSerieDto {

    @JsonProperty(required = true)
    private String image;
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createAt;
}
