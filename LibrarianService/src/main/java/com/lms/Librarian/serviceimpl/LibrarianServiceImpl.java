package com.lms.Librarian.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Librarian.dto.LibrarianDTO;
import com.lms.Librarian.exceptions.LibrarianAlreadyExistsException;
import com.lms.Librarian.exceptions.LibrarianNotFoundException;
import com.lms.Librarian.model.Librarian;
import com.lms.Librarian.repository.LibrarianRepository;
import com.lms.Librarian.service.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public ResponseEntity<Librarian> addLibrarian(LibrarianDTO librarianDTO) {
        if (librarianRepository.existsByEmail(librarianDTO.getEmail())) {
            throw new LibrarianAlreadyExistsException("Librarian with email " + librarianDTO.getEmail() + " already exists.");
        }
        
        Librarian librarian = new Librarian();
        librarian.setId(generateLibrarianId());
        librarian.setName(librarianDTO.getName());
        librarian.setEmail(librarianDTO.getEmail());
        
        Librarian savedLibrarian = librarianRepository.save(librarian);
        return new ResponseEntity<>(savedLibrarian, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Librarian>> getAllLibrarians() {
        List<Librarian> librarians = librarianRepository.findAll();
        return new ResponseEntity<>(librarians, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Librarian> getLibrarianById(String id) {
        Optional<Librarian> optionalLibrarian = librarianRepository.findById(id);
        if (optionalLibrarian.isPresent()) {
            return new ResponseEntity<>(optionalLibrarian.get(), HttpStatus.OK);
        } else {
            throw new LibrarianNotFoundException("Librarian with ID " + id + " not found.");
        }
    }

    @Override
    public ResponseEntity<Void> deleteLibrarian(String id) {
        if (librarianRepository.existsById(id)) {
            librarianRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new LibrarianNotFoundException("Librarian with ID " + id + " not found.");
        }
    }
    
    
    private String generateLibrarianId() {
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100; 
        return "LB" + randomNumber;
    }
}
