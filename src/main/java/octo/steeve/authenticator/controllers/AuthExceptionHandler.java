package octo.steeve.authenticator.controllers;

import octo.steeve.authenticator.usecases.NameMissingException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(NameMissingException.class)
    public ResponseEntity<ErrorResponse> handleYourCustomException(NameMissingException ex) {
        ErrorResponse errorResponse = new ErrorResponse("YOUR_ERROR_CODE", "name is missing");

        return ResponseEntity. status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    class ErrorResponse {
        private final String code;
        private final String message;

        ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
