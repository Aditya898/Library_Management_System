package com.lms.Admin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.Admin.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(String bookId);
    boolean existsByTitleAndAuthor(String title, String author);
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String keyword, String author);
}
