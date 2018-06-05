package com.ay.testlab.atom.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public void insertAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author storedAuthor = getAuthor(id).get();
        storedAuthor.setName(author.getName());
        storedAuthor.setSurname(author.getSurname());
        storedAuthor.setCountry(author.getCountry());
        authorRepository.save(storedAuthor);
        return storedAuthor;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean isAuthorExist(Author author) {
        Author storedAuthor = authorRepository.findFirstByNameAndSurname(author.getName(), author.getSurname());
        return storedAuthor!=null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }
}