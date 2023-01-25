package com.fmartinez.disney.app.swagger.save.movie;

import com.fmartinez.disney.app.model.MovieSerie;
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

@Operation(summary = "Save Movie-Serie")
@ApiResponses({
        @ApiResponse(responseCode = "201",
                description = "Creates a new movie-serie",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = MovieSerie.class))
                }),
        @ApiResponse(responseCode = "500", description = "Movie-serie can not be created")

})
public @interface ResponseSaveMovieSerie {

}
