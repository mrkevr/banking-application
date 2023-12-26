package dev.mrkevr.bankingapplication.controller.advice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.mrkevr.bankingapplication.exception.EmailAlreadyExistsException;
import dev.mrkevr.bankingapplication.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> errors = ex.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setProperty("errors", errors);
		problemDetail.setProperty("timeStamp", LocalDateTime.now());
		return ResponseEntity.of(problemDetail).build();
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserNotFound(EmailAlreadyExistsException ex) {
		ProblemDetail problemDetail = createProblemDetail(ex, HttpStatus.BAD_REQUEST);
		return ResponseEntity.of(problemDetail).build();
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		ProblemDetail problemDetail = createProblemDetail(ex, HttpStatus.NOT_FOUND);
		return ResponseEntity.of(problemDetail).build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		ProblemDetail problemDetail = createProblemDetail(ex, HttpStatus.BAD_REQUEST);
		return ResponseEntity.of(problemDetail).build();
	}
	
	/*
	 * Helper method in creating ProblemDetail
	 */
	private ProblemDetail createProblemDetail(Exception ex, HttpStatus status) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(status);
		problemDetail.setProperty("errors", ex.getMessage());
		problemDetail.setProperty("timeStamp", LocalDateTime.now());
		return problemDetail;
	}
}
