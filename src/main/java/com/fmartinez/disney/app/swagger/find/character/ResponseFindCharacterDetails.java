package com.fmartinez.disney.app.swagger.find.character;

import com.fmartinez.disney.app.dto.CharacterDetailDto;
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

@Operation(summary = "Get character details")
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Found characters by id",
                content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = CharacterDetailDto.class))
                }),
        @ApiResponse(responseCode = "404", description = "Character Not Found")

})
public @interface ResponseFindCharacterDetails {
}
