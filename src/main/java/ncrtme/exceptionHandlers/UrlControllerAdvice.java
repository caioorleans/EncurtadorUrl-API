package ncrtme.exceptionHandlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ncrtme.exceptionHandlers.exceptions.UrlExpiredException;
import ncrtme.exceptionHandlers.exceptions.UrlNotFoundException;

@ControllerAdvice(basePackages = "ncrtme.controller")
public class UrlControllerAdvice {
	@ResponseBody
	@ExceptionHandler(UrlExpiredException.class)
	public ResponseEntity<MessageExceptionsHandler> urlExpired(UrlExpiredException urlExpiredException){
		var error = new MessageExceptionsHandler(new Date(), HttpStatus.GONE.value(), "URL expirou");
		return new ResponseEntity<>(error, HttpStatus.GONE);
	}
	
	@ResponseBody
	@ExceptionHandler(UrlNotFoundException.class)
	public ResponseEntity<MessageExceptionsHandler> urlNotFound(UrlNotFoundException urlNotFoundException){
		var error = new MessageExceptionsHandler(new Date(), HttpStatus.NOT_FOUND.value(), "URL não encontrada");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
