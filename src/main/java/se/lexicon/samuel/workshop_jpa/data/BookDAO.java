package se.lexicon.samuel.workshop_jpa.data;

import se.lexicon.samuel.workshop_jpa.entity.Book;

import java.util.Collection;

public interface BookDAO {
    Book findById(Integer bookId);
    Collection<Book>findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(Integer bookId);
}
