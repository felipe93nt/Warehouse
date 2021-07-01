package br.com.warehouse.exceptions;

import java.net.ConnectException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//Handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest request) {	
		ErrorDetails ed = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ed);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<?> handleNumberFormatException(
			NumberFormatException exception, WebRequest request) {	
		ErrorDetails ed = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ed);
	}
	
	@ExceptionHandler( ConnectException.class)
	public ResponseEntity<?> handleConnectException(
			ConnectException exception, WebRequest request) {
		ErrorDetails ed = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(ed);
	}
	
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception, WebRequest request) {
		ErrorDetails ed = new ErrorDetails(new Date(), "Not Valid Argument provided", request.getDescription(false));
		for(ObjectError oe:exception.getBindingResult().getAllErrors()) {
			ed.getErros().put(oe.getObjectName(), oe.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(ed);
	}
	
}
