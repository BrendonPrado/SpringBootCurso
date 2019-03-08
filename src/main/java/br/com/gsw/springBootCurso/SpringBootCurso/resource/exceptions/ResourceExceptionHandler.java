package br.com.gsw.springBootCurso.SpringBootCurso.resource.exceptions;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.DataIntegrityException;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),Calendar.getInstance());		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e,HttpServletRequest request){
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),Calendar.getInstance());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgument(MethodArgumentNotValidException e,HttpServletRequest request){
	
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação",Calendar.getInstance());		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErrors(new FieldMessage(x.getField(),x.getDefaultMessage()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
}
