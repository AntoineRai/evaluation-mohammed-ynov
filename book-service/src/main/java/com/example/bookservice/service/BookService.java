package com.example.bookservice.service;

import com.example.bookservice.entity.Book;
import java.util.List;

public interface BookService {
     List<Book> getAllBooks();

     Book getBookById(Long id);

     Book saveBook(Book book);

     void deleteBookById(Long id);
}
