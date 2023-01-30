package com.dislike.backend.security.excpetion;

public class UserExistsException extends IllegalArgumentException {
    public UserExistsException(String s) {
        super(s);
    }
}
