package se.lexicon.samuel.workshop_jpa.data;

import se.lexicon.samuel.workshop_jpa.entity.BookLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDAO{

    private EntityManager em;

    @Autowired
    public BookLoanDAORepository(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(Integer bookLoanId) {
        return em.find(BookLoan.class, bookLoanId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        return em
                .createQuery("SELECT bookLoan FROM BookLoan bookLoan", BookLoan.class)
                .getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BookLoan create(BookLoan bookLoan) {
        em.persist(bookLoan);
        return bookLoan;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BookLoan update(BookLoan bookLoan) {
        return em.merge(bookLoan);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer bookLoanId) {
        BookLoan toDelete = findById(bookLoanId);
            if (toDelete != null) {
                em.remove(toDelete);
                System.out.println("Book loan " + bookLoanId + " has been removed");
            } else {
                throw new IllegalArgumentException("Book loan " + bookLoanId + " cannot be found");
            }
        return false;
    }
    }

