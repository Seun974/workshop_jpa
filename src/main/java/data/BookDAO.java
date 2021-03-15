package data;

import entity.Book;

import java.util.Collection;

public interface BookDAO {
    Book findById(Integer bookId);
    Collection<Book>findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(Integer bookId);
}
