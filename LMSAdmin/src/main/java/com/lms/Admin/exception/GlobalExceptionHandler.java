package com.lms.Admin.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleNoElectionDetailsFoundException(WebRequest webRequest,Exception exception)
	{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"NOT FOUND");
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleCandidateAlreadyPresentException(WebRequest webRequest,Exception exception)
	{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"ALREADY REPORTED");
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.ALREADY_REPORTED);
	}

}
