package data;

import entity.Author;

import java.util.Collection;

public interface AuthorDAO {
        Author findById(Integer authorId);
        Collection<Author> findAll();
        Author create(Author author);
        Author update(Author author);
        void delete(Integer authorId);




}
