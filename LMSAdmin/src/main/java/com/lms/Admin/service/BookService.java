package com.lms.Admin.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Admin.dto.BookDTO;
import com.lms.Admin.exception.BookNotFoundException;
import com.lms.Admin.model.Book;

public interface BookService {
    ResponseEntity<Book> addBook(BookDTO bookDTO);
    ResponseEntity<Book> updateBook(String bookId, BookDTO bookDTO);
    ResponseEntity<String> deleteBook(String bookId);
    ResponseEntity<List<Book>> getAllBooks();
    ResponseEntity<Book> getBookById(String bookId);

    ResponseEntity<List<Book>> searchBooks(String keyword);
    ResponseEntity<Book> borrowBook(String bookId) throws BookNotFoundException;
    ResponseEntity<Book> returnBook(String bookId) throws BookNotFoundException;

}
