package com.example.borrowingservice.controller;

import com.example.borrowingservice.entity.Borrowing;
import com.example.borrowingservice.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public List<Borrowing> getAllBorrowings() {
        return borrowingService.getAllBorrowings();
    }

    @GetMapping("/{id}")
    public Borrowing getBorrowingById(@PathVariable Long id) {
        return borrowingService.findById(id);
    }

    @PostMapping
    public Borrowing saveBorrowing(@RequestBody Borrowing borrowing) {
        return borrowingService.save(borrowing);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowingById(@PathVariable Long id) {
        borrowingService.deleteById(id);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return ResponseEntity.ok().build();
    }

}
