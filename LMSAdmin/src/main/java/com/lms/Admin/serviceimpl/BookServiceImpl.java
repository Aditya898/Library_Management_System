package com.lms.Admin.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Admin.dto.BookDTO;
import com.lms.Admin.exception.BookAlreadyExistsException;
import com.lms.Admin.exception.BookNotFoundException;
import com.lms.Admin.model.Book;
import com.lms.Admin.repository.BookRepository;
import com.lms.Admin.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<Book> addBook(BookDTO bookDTO) throws BookAlreadyExistsException {
        if (bookRepository.existsByTitleAndAuthor(bookDTO.getTitle(), bookDTO.getAuthor())) {
            throw new BookAlreadyExistsException("Book already exists with the provided title and author");
        }

        String generatedBookId = generateBookId();
       
        Book book = new Book();
        book.setBookId(generatedBookId);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setAvailable(true);
        
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Book> updateBook(String bookId, BookDTO bookDTO) throws BookNotFoundException {
        Book existingBook = bookRepository.findByBookId(bookId);
        if (existingBook == null) {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        
        bookRepository.save(existingBook);
        return new ResponseEntity<>(existingBook, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBook(String bookId) throws BookNotFoundException {
        Book existingBook = bookRepository.findByBookId(bookId);
        if (existingBook == null) {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }

        bookRepository.delete(existingBook);
        return new ResponseEntity<>("Book with id " + bookId + " has been successfully deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> getBookById(String bookId) throws BookNotFoundException {
        Book book = bookRepository.findByBookId(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
        return new ResponseEntity<>(book, HttpStatus.FOUND);
    }
    
    private static int bookCounter = 1;

    private String generateBookId() {
        String formattedCounter = String.format("%03d", bookCounter++);
        return "B" + formattedCounter;
    }
    
    
    @Override
    public ResponseEntity<List<Book>> searchBooks(String keyword) {
        List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
        return new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> borrowBook(String bookId) throws BookNotFoundException {
        Book book = bookRepository.findByBookId(bookId);
        if (book != null) {
            if (!book.isAvailable()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Book is not available for borrowing
            }
            book.setAvailable(false);
            bookRepository.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }


    @Override
    public ResponseEntity<Book> returnBook(String bookId) throws BookNotFoundException {
        Book book = bookRepository.findByBookId(bookId);
        if (book != null) {
            book.setAvailable(true);
            bookRepository.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }



}
