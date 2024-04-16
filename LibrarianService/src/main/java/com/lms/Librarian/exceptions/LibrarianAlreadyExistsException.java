package com.lms.Librarian.exceptions;

public class LibrarianAlreadyExistsException extends RuntimeException {
    public LibrarianAlreadyExistsException(String message) {
        super(message);
    }
}
