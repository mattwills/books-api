package com.msoft.booksapi.application.service;

import com.msoft.booksapi.application.domain.Author;
import com.msoft.booksapi.application.exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorService {

    private final Map<UUID, Author> AUTHORS = new HashMap<>();

    public Author createAuthor(String name) {
        UUID id = UUID.randomUUID();
        Author author = new Author(id, name);
        AUTHORS.put(id, author);
        return author;
    }

    public Author getAuthorById(UUID id) {
        Author author = AUTHORS.get(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
        return author;
    }

    public List<Author> getAuthors() {
        return AUTHORS.values().stream().toList();
    }
}
