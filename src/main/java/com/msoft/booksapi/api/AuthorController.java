package com.msoft.booksapi.api;

import com.msoft.booksapi.api.model.AuthorResponse;
import com.msoft.booksapi.api.model.CreateAuthorRequest;
import com.msoft.booksapi.application.domain.Author;
import com.msoft.booksapi.application.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(CREATED)
    public AuthorResponse createAuthor(@RequestBody CreateAuthorRequest request) {
        Author author = authorService.createAuthor(request.name());
        return new AuthorResponse(author.id(), author.name());
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<AuthorResponse> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors.stream()
                .map(author -> new AuthorResponse(author.id(), author.name()))
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public AuthorResponse getAuthorById(@PathVariable UUID id) {
        Author author = authorService.getAuthorById(id);
        return new AuthorResponse(author.id(), author.name());
    }
}
