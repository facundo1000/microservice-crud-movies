package com.fmartinez.disney.app.exception;

import com.fmartinez.disney.app.util.ErrorType;
import lombok.Data;


@Data
public class NotFoundException extends RuntimeException {

    public final ErrorType errorDefinition;

    private String additionalInfo;

    public NotFoundException(ErrorType errorDefinition) {
        this.errorDefinition = errorDefinition;
    }

    public NotFoundException(ErrorType errorDefinition, String additionalInfo) {
        this.errorDefinition = errorDefinition;
        this.additionalInfo = additionalInfo;
    }
}
