package com.example.borrowingservice.repository;

import com.example.borrowingservice.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    @Query("SELECT COUNT(b) FROM Borrowing b WHERE b.userId = :userId AND b.returnDate IS NULL")
    long countByUserId(Long userId);
}
