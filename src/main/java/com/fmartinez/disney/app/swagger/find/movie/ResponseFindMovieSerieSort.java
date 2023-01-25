package com.fmartinez.disney.app.swagger.find.movie;

import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.dto.MovieSerieDto;
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

@Operation(summary = "Sort movie-series by date")
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Gets all movie-series sorted by date",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = MovieSerieDto.class))
                }),
        @ApiResponse(responseCode = "404", description = "Character Not Found")

})
public @interface ResponseFindMovieSerieSort {

}
