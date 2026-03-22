package backend.javabean.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleBusinessException(BusinessException ex) {
    return Map.of("error", ex.getMessage());
  }

}
