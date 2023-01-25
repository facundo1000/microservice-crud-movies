package com.fmartinez.disney.app.swagger.find.genre;

import com.fmartinez.disney.app.dto.CharacterDto;
import com.fmartinez.disney.app.model.Genre;
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

@Operation(summary = "Get Genre")
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Found all genres",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Genre.class))
                }),
        @ApiResponse(responseCode = "404", description = "Genre Not Found")

})
public @interface ResponseFindGenre {

}
