package com.fmartinez.disney.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.fmartinez.disney.app.constant.ApplicationConstant.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handleNotFoundException(NotFoundException exception) {

        return switch (exception.getErrorDefinition()) {
            case CHARACTER_NOT_FOUND -> Error.builder()
                    .code("CHARACTER_NOT_FOUND")
                    .message(CHARACTER_NOT_FOUND + getAdditionalInfo(exception))
                    .build();
            case GENRE_NOT_FOUND -> Error.builder()
                    .code("GENRE_NOT_FOUND")
                    .message(GENRE_NOT_FOUND + getAdditionalInfo(exception))
                    .build();
            case MOVIE_SERIE_NOT_FOUND -> Error.builder()
                    .code("MOVIE_SERIE_NOT_FOUND")
                    .message(MOVIE_SERIE_NOT_FOUND + getAdditionalInfo(exception))
                    .build();
        };
    }

    private String getAdditionalInfo(NotFoundException exception) {
        return Objects.nonNull(exception.getAdditionalInfo()) ? "Info: " + exception.getAdditionalInfo() : "";
    }
}
