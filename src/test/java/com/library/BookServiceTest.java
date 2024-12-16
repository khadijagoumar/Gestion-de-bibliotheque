package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        bookDAO = new BookDAO();
        bookService = new BookService();
    }

    @Test
    void testAddBook() {
        // Ajout d'un livre
        Book book = new Book("Java Programming", "John Doe", "Tech Publisher", 2024, "1234567890");
        bookService.addBook(book);

        // Vérifier que le livre est ajouté
        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(book.getId()).getTitle());
    }

    @Test
    void testUpdateBook() {
        // Ajout du livre initial
        Book book = new Book("Java Programming", "John Doe", "Tech Publisher", 2024, "1234567890");
        bookService.addBook(book);

        // Mise à jour du livre
       // bookService.updateBook(new Book(book.getId(), "Advanced Java", "Jane Doe", "Advanced Publisher", 2025, "0987654321"));

        // Vérifier que les informations ont été mises à jour
        Book updatedBook = bookDAO.getBookById(book.getId());
        assertEquals("Advanced Java", updatedBook.getTitle());
        assertEquals("Jane Doe", updatedBook.getAuthor());
        assertEquals("Advanced Publisher", updatedBook.getPublisher());
        assertEquals(2025, updatedBook.getYear());
        assertEquals("0987654321", updatedBook.getIsbn());
    }

    @Test
    void testDeleteBook() {
        // Ajout d'un livre
        Book book = new Book("Java Programming", "John Doe", "Tech Publisher", 2024, "1234567890");
        bookService.addBook(book);

        // Suppression du livre
        bookService.deleteBook(book.getId());

        // Vérifier que le livre est supprimé
        assertFalse(bookDAO.getBookById(book.getId()).isPresent());
    }
}
