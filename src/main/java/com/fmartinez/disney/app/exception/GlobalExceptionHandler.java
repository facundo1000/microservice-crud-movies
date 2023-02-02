package com.fmartinez.disney.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.fmartinez.disney.app.constant.ApplicationConstant.*;
import static com.fmartinez.disney.app.util.ErrorType.NOT_FOUND_EXCEPTION;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handleNotFoundException(NotFoundException exception) {

        return switch (exception.getErrorDefinition()) {
            case CHARACTER_NOT_FOUND -> Error.builder()
                    .message(CHARACTER_NOT_FOUND + getAdditionalInfo(exception))
                    .code("CHARACTER_NOT_FOUND")
                    .build();
            case GENRE_NOT_FOUND -> Error.builder()
                    .message(GENRE_NOT_FOUND + getAdditionalInfo(exception))
                    .code("GENRE_NOT_FOUND")
                    .build();
            case MOVIE_SERIE_NOT_FOUND -> Error.builder()
                    .message(MOVIE_SERIE_NOT_FOUND + getAdditionalInfo(exception))
                    .code("MOVIE_SERIE_NOT_FOUND")
                    .build();
            default -> Error.builder()
                    .message(NOT_FOUND_EXCEPTION + getAdditionalInfo(exception))
                    .code("NOT_FOUND_EXCEPTION")
                    .build();
        };
    }

    private String getAdditionalInfo(NotFoundException exception) {
        return Objects.nonNull(exception.getAdditionalInfo()) ? "Info: " + exception.getAdditionalInfo() : "";
    }
}
