package se.lexicon.samuel.workshop_jpa;

import org.junit.jupiter.api.*;
import se.lexicon.samuel.workshop_jpa.data.BookLoanDAO;
import se.lexicon.samuel.workshop_jpa.entity.BookLoan;
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
    @DisplayName("Given loanId findById should return se.lexicon.samuel.workshop_jpa.entity")
    void findById() {
        Integer loanId = persistedBookLoan.getLoanId();
        BookLoan result = testObject.findById(loanId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(persistedBookLoan, result);
    }

    @Test
    @DisplayName("findAll return collection of size 1")
    void findAll() {
        int expected = 1;

        Collection<BookLoan> result = testObject.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result.size());
    }

    @Test
    @DisplayName("Given the new bookLoan create should return an se.lexicon.samuel.workshop_jpa.entity with id")
    void create() {
        BookLoan bookLoan = new BookLoan(
                2, LocalDate.parse("2021-02-01"), LocalDate.parse("2021-02-22"), false
        );

        BookLoan result = testObject.create(bookLoan);

        Assertions.assertNotNull(bookLoan);
        Assertions.assertNotNull(result.getLoanId());

    }

    @Test
    @DisplayName("Given update se.lexicon.samuel.workshop_jpa.entity update should modify relevant field and return se.lexicon.samuel.workshop_jpa.entity ")
    void update() {
        BookLoan toUpdate = persistedBookLoan;
        toUpdate.setReturned(true);

        BookLoan result = testObject.update(toUpdate);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(true, result.getReturned());

    }

    @Test
    @DisplayName("Given persistedBookLoan.loanId should return true")
    void delete() {
        Assertions.assertTrue(testObject.delete(persistedBookLoan.getLoanId()));
    }
}