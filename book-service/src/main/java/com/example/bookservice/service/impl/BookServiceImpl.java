package com.example.bookservice.service.impl;

import com.example.bookservice.entity.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book saveBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBookAvailability(Long id, boolean isAvailable) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
        book.setAvailable(isAvailable);
        bookRepository.save(book);
    }
}
