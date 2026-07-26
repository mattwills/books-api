package com.msoft.booksapi.application.service;

import com.msoft.booksapi.application.domain.Author;
import com.msoft.booksapi.application.exception.AuthorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class AuthorServiceTest {

    private final AuthorService authorService = new AuthorService();

    @Test
    void shouldCreateAuthor() {
        // Given
        String name = "Harper Lee";

        // When
        Author author = authorService.createAuthor(name);

        // Then
        assertThat(author.name()).isEqualTo(name);
        assertThat(author.id()).isNotNull();
    }

    @Test
    void shouldGetAuthorById() {
        // Given
        Author expected = authorService.createAuthor("Harper Lee");

        // When
        Author actual = authorService.getAuthorById(expected.id());

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFailToGetAuthorByIdGivenUnknownId() {
        // Given
        authorService.createAuthor("Harper Lee");
        UUID unknownId = UUID.randomUUID();
        String expectedMessage = "Cannot find Author with id [" + unknownId + "]";

        // When
        AuthorNotFoundException e = catchThrowableOfType(() -> authorService.getAuthorById(unknownId), AuthorNotFoundException.class);

        // Then
        assertThat(e.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void shouldGetAuthors() {
        // Given
        Author author1 = authorService.createAuthor("Harper Lee");
        Author author2 = authorService.createAuthor("Stephen King");

        // When
        List<Author> actual = authorService.getAuthors();

        // Then
        assertThat(actual).containsExactlyInAnyOrder(author1, author2);
    }
}