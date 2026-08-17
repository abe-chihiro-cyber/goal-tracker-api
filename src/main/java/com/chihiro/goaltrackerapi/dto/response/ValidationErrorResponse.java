package com.chihiro.goaltrackerapi.dto.response;

import java.util.Map;

public record ValidationErrorResponse(String message, Map<String, String> errors) {
}