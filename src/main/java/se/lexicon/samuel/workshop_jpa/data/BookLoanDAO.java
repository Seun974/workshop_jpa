package se.lexicon.samuel.workshop_jpa.data;

import se.lexicon.samuel.workshop_jpa.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {
    BookLoan findById(Integer bookLoanId);
    Collection<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    boolean delete(Integer bookLoanId);


}
