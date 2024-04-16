package com.lms.Librarian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.Librarian.dto.LibrarianDTO;
import com.lms.Librarian.model.Librarian;
import com.lms.Librarian.service.LibrarianService;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping
    public ResponseEntity<Librarian> addLibrarian(@RequestBody LibrarianDTO librarianDTO) {
        return librarianService.addLibrarian(librarianDTO);
    }

    @GetMapping
    public ResponseEntity<List<Librarian>> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable String id) {
        return librarianService.getLibrarianById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable String id) {
        return librarianService.deleteLibrarian(id);
    }
}
