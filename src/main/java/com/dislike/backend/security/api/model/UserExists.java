package com.dislike.backend.security.api.model;

import org.springframework.http.HttpStatus;

public record UserExists (String message, HttpStatus status) {
}
