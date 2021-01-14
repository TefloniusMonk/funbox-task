package fun.box.test.task.web.aspect;

import fun.box.test.task.dto.ErrorResponse;
import fun.box.test.task.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class MessageHandler {

    @ResponseBody
    @ExceptionHandler(BusinessLogicException.class)
    protected ResponseEntity<ErrorResponse> handleLogicException(HttpServletRequest request, BusinessLogicException exception) {
        log.error("Exception while processing request: {}. Exception is: {}", request.getRequestURL(), exception.getMessage(), exception);
        return new ResponseEntity<>(ErrorResponse.build(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception exception) {
        log.error("Exception while processing request: {}. Exception is : ", request.getRequestURL(), exception);
        if (exception.getMessage() == null){
            return new ResponseEntity<>(ErrorResponse.build("Unknown exception"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ErrorResponse.build(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}