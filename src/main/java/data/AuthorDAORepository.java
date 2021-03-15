package data;

import entity.Author;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDAO {

    private EntityManager em;

    @Autowired
    public AuthorDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Integer authorId) {
        return em.find(Author.class, authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return em
                .createQuery("SELECT author FROM Author author", Author.class)
                .getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author create(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author update(Author author) {
        return em.merge(author);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer authorId) {
        Author toDelete = findById(authorId);
        if (toDelete != null) {
            em.remove(toDelete);
            System.out.println("Author " + authorId + " has been removed");
        } else {
            throw new IllegalArgumentException("Author cannot be found");
        }
    }

}


