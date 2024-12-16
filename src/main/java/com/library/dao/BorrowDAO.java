package com.library.dao;

import com.library.model.Borrow;
import com.library.util.DbConnection;

import java.sql.*;

public class BorrowDAO {

    private final Connection connection;

    public BorrowDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBorrow(Borrow borrow) {
        String query = "INSERT INTO borrows (student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, borrow.getStudent().getId()); // Utilisation de l'ID de l'étudiant
            stmt.setInt(2, borrow.getBook().getId());    // Utilisation de l'ID du livre
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
