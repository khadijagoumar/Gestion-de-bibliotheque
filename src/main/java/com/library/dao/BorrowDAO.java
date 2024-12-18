package com.library.dao;

import com.library.model.Borrow;
import com.library.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowDAO {

    // Enregistrer un emprunt
    public void borrowBook(Borrow borrow) {
        String query = "INSERT INTO borrows (id, student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, borrow.getId());
            statement.setInt(2, borrow.getStudent().getId());
            statement.setInt(3, borrow.getBook().getId());
            statement.setDate(4, new Date(borrow.getBorrowDate().getTime()));
            statement.setDate(5, new Date(borrow.getReturnDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error borrowing book: " + e.getMessage());
        }
    }
}
