package data;

import entity.Book;
import entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BookDAORepository implements BookDAO{

    private EntityManager em;

    @Autowired
    public BookDAORepository(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Integer bookId) {
        return em.find(Book.class, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return em
                .createQuery("Select book FROM Book book", Book.class)
                .getResultList();

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer bookId) {
        Book toDelete = findById(bookId);
        if (toDelete != null) {
            em.remove(toDelete);
            System.out.println("Book " + bookId + " has been removed");
        } else {
            throw new IllegalArgumentException("Book was not found");
        }

    }

}
