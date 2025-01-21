package com.example.borrowingservice.service;

import com.example.borrowingservice.entity.Borrowing;

import java.util.List;

public interface BorrowingService {
    List<Borrowing> getAllBorrowings();

    Borrowing findById(Long id);

    Borrowing save(Borrowing borrowing);

    void deleteById(Long id);
}
