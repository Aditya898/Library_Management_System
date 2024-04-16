
package com.lms.Librarian.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Librarian.dto.LibrarianDTO;
import com.lms.Librarian.exceptions.LibrarianAlreadyExistsException;
import com.lms.Librarian.exceptions.LibrarianNotFoundException;
import com.lms.Librarian.model.Librarian;

public interface LibrarianService {
    ResponseEntity<Librarian> addLibrarian(LibrarianDTO librarianDTO) throws LibrarianAlreadyExistsException;
    ResponseEntity<List<Librarian>> getAllLibrarians();
    ResponseEntity<Librarian> getLibrarianById(String id)throws LibrarianNotFoundException;
    ResponseEntity<Void> deleteLibrarian(String id) throws LibrarianNotFoundException;
    
}
