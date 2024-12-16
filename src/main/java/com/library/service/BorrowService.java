package com.library.service;

import com.library.dao.BorrowDAO;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.model.Book;

public class BorrowService {

    private BorrowDAO borrowDAO;
    private StudentService studentService;
    private BookService bookService;

    public BorrowService(BorrowDAO borrowDAO, StudentService studentService, BookService bookService) {
        this.borrowDAO = borrowDAO;
        this.studentService = studentService;
        this.bookService = bookService;
    }

    // Méthode pour emprunter un livre
    public void borrowBook(int studentId, int bookId, java.util.Date borrowDate, java.util.Date returnDate) {
        Student student = studentService.findStudentById(studentId);
        Book book = bookService.findBookById(bookId);

        if (student != null && book != null) {
            Borrow borrow = new Borrow(student, book, borrowDate, returnDate);
            borrowDAO.addBorrow(borrow);
            System.out.println("Emprunt enregistré pour l'étudiant : " + student.getName());
        } else {
            System.out.println("Impossible d'enregistrer l'emprunt, étudiant ou livre introuvable.");
        }
    }
}
