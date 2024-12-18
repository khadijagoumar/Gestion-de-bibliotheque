package com.library;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BorrowDAO borrowDAO;
    private BookService bookService;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        borrowDAO = new BorrowDAO();
        StudentDAO studentDAO = new StudentDAO();
        BookDAO bookDAO = new BookDAO();
        studentService = new StudentService(studentDAO);
        bookService = new BookService(bookDAO);
        borrowService = new BorrowService(borrowDAO, studentService, bookService);
    }

    @Test
    public void testBorrowBook() {
        Student student = new Student(5, "Alice");
        Book book = new Book(6, "1984", "George Orwell", "123456789", "Secker & Warburg", 1949);
        studentService.addStudent(student);
        bookService.addBook(book);

        Date borrowDate = new Date();
        Date returnDate = new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000); // Retour dans 7 jours
        borrowService.borrowBook(1, 1, borrowDate, returnDate);

        /* Vérification de l'emprunt
        Borrow borrow = borrowDAO.getBorrowByStudentId(1);
        assertNotNull(borrow);
        assertEquals(student.getId(), borrow.getStudent().getId());
        assertEquals(book.getId(), borrow.getBook().getId());*/
    }
}
