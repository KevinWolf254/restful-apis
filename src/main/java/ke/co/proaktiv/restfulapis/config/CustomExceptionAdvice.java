package ke.co.proaktiv.restfulapis.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ke.co.proaktiv.restfulapis.model.NotFoundException;

@ControllerAdvice
public class CustomExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String notFoundHandler(NotFoundException ex) {
		return ex.getMessage();
	}
}
