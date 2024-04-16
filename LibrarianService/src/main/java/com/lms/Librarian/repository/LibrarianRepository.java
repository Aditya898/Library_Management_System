package com.lms.Librarian.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.Librarian.model.Librarian;

@Repository
public interface LibrarianRepository extends MongoRepository<Librarian, String> {
	boolean existsByEmail(String email);
}
