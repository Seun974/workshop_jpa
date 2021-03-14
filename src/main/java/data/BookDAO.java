package data;

import entity.Book;

import java.util.Collection;

public interface BookDAO {
    Book findById(Integer bookId);
    Collection<Book>findAll();
    Book create(Book book);
    Book update(Book book);
    boolean delete(Integer bookId); //take not that this is void and it returns nothing
}
