package com.lms.Librarian.exceptions;

public class LibrarianNotFoundException extends RuntimeException {
    public LibrarianNotFoundException(String message) {
        super(message);
    }
}

