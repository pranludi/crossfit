package io.pranludi.crossfit.member.rest;

import io.pranludi.crossfit.member.exception.ServerError;
import io.pranludi.crossfit.member.rest.dto.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        System.out.println("아래 printStackTrace 는 개발용");
        ex.printStackTrace(); // todo 개발용
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerError.class)
    public ResponseEntity<ErrorResponse> handleStatusRuntimeException(ServerError e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                new ErrorResponse(e.getCode(), e.getMessage())
            );
    }
}
