package data;

import entity.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {
    BookLoan findById(Integer bookLoanId);
    Collection<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    boolean delete(Integer bookLoanId);


}
