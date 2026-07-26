package com.msoft.booksapi.application.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(UUID id) {
        super(String.format("Cannot find Author with id [%s]", id));
    }
}
