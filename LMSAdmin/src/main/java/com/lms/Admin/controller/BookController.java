package com.lms.Admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.Admin.dto.BookDTO;
import com.lms.Admin.model.Book;
import com.lms.Admin.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(bookId, bookDTO);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }
}
