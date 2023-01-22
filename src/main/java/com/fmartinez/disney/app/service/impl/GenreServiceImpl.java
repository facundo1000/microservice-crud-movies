package com.fmartinez.disney.app.service.impl;

import com.fmartinez.disney.app.model.Genre;
import com.fmartinez.disney.app.repository.GenreRepository;
import com.fmartinez.disney.app.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(final GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> getAllGenre() {
        return (List<Genre>) repository.findAll();
    }

    @Override
    public Genre create(Genre genre) {
        return repository.save(genre);
    }
}
