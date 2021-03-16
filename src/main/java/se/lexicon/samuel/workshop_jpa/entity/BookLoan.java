package se.lexicon.samuel.workshop_jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    //defining the relationships here for the entities connected, i.e AppUser and Book
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "borrower_id", table = "book_loan")
    private AppUser borrower;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "book_id", table = "book")
    private Book book;

    public BookLoan(int loanId, LocalDate loanDate, LocalDate dueDate, boolean returned) {
        this.loanId = loanId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public BookLoan(AppUser borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
    }

    public BookLoan() {
    }

    public int getLoanId() {
        return loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getReturned() {
        return returned;
    }

    public void setReturned(boolean regDate) {
        this.returned = regDate;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return Objects.equals(loanDate, bookLoan.loanDate) && Objects.equals(dueDate, bookLoan.dueDate) && Objects.equals(returned, bookLoan.returned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, dueDate, returned);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", regDate=" + returned +
                '}';
    }
}
