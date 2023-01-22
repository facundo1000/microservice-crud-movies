package com.fmartinez.disney.app.exception;

import lombok.Builder;

@Builder
public record Error(String message, String code) {
}
