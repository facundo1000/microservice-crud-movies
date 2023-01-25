package com.fmartinez.disney.app.swagger.find.movie;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.dto.MovieSerieDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ANNOTATION_TYPE, METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)

@Operation(summary = "Get movies details")
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Found a movie-serie by id and get all it's details",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = MovieSerieDetailDto.class))
                }),
        @ApiResponse(responseCode = "404", description = "Character Not Found")

})
public @interface ResponseFindMovieSerieDetails {

}
