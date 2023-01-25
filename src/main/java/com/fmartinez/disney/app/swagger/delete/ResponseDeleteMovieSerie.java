package com.fmartinez.disney.app.swagger.delete;

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

@Operation(summary = "Delete movie-serie")
@ApiResponses({
        @ApiResponse(responseCode = "204",
                description = "Delete an existing movie-serie by id",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Void.class))
                }),
        @ApiResponse(responseCode = "404", description = "Movie-serie Not Found")

})
public @interface ResponseDeleteMovieSerie {

}
