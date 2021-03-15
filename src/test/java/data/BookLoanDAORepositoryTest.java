package data;

import entity.BookLoan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class BookLoanDAORepositoryTest {

    @Autowired
    private BookLoanDAO testObject;
    @Autowired
    private TestEntityManager em;

    private BookLoan persistedBookLoan;

    @BeforeEach
    void setUp() {
        BookLoan unpersisted = new BookLoan(1, LocalDate.parse("2021-03-01"), LocalDate.parse("2021-03-30"), false);
        persistedBookLoan = em.persistAndFlush(unpersisted);
    }

    @AfterEach
    void tearDown() {
        em.flush();
    }

    @Test
    @DisplayName("Given loanId findById should return entity")
    void findById() {
        Integer loanId = persistedBookLoan.getLoanId();
        BookLoan result = testObject.findById(loanId);

        assertNotNull(result);
        assertEquals(persistedBookLoan, result);
    }

    @Test
    @DisplayName("findAll return collection of size 1")
    void findAll() {
        int expected = 1;

        Collection<BookLoan> result = testObject.findAll();

        assertNotNull(result);
        assertEquals(expected, result.size());
    }

    @Test
    @DisplayName("Given the new bookLoan create should return an entity with id")
    void create() {
        BookLoan bookLoan = new BookLoan(
                2, LocalDate.parse("2021-02-01"), LocalDate.parse("2021-02-22"), false
        );

        BookLoan result = testObject.create(bookLoan);

        assertNotNull(bookLoan);
        assertNotNull(result.getLoanId());

    }

    @Test
    @DisplayName("Given update entity update should modify relevant field and return entity ")
    void update() {
        BookLoan toUpdate = persistedBookLoan;
        toUpdate.setReturned(true);

        BookLoan result = testObject.update(toUpdate);

        assertNotNull(result);
        assertEquals(true, result.getReturned());

    }

    @Test
    @DisplayName("Given persistedBookLoan.loanId should return true")
    void delete() {
        assertTrue(testObject.delete(persistedBookLoan.getLoanId()));
    }
}