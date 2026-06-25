package com.emapaez.storepay.common.exception;


import com.emapaez.storepay.common.exception.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.auth.login.AccountExpiredException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    private static final Logger log = LoggerFactory.getLogger(GlobalHandlerException.class);

    /// ---------------------- MSJ ERROR CONSTRUCTOR ----------------------------------- ///
    private ResponseEntity<ErrorMessage> buildResponse(HttpStatus status, String message){
        ErrorMessage error = new ErrorMessage(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(error, status);
    }

    /// --------------------------- HANDLERS --------------------------------------------- ///

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleUnexpected(Exception ex){
        log.error("Unexpected error", ex);  /// msj para saber que esta pasando
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred on the server.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex){
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponse(HttpStatus.BAD_REQUEST, errors);
    }

//    @ExceptionHandler(LockedException.class)
//    public ResponseEntity<ErrorMessage> handlerLockedAccount(LockedException ex) {
//        return buildResponse(HttpStatus.FORBIDDEN, "Your account is Locked");
//    }

//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ErrorMessage> handlerDisabledAccount(DisabledException ex) {
//        return buildResponse(HttpStatus.FORBIDDEN, "Your account is disabled");
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorMessage> handlerBadCredentials(BadCredentialsException ex){
//        return buildResponse(HttpStatus.UNAUTHORIZED, "Authentication invalid, try again");
//    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<ErrorMessage> handlerAccountExpired(AccountExpiredException ex){
        return buildResponse(HttpStatus.FORBIDDEN, "Your account has expired. Please contact support.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, "You do not have permission to perform this action.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("The parameter '%s' has an invalid value. Expected type: %s",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handlerHttpMessageException(HttpMessageNotReadableException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handlerHttpRequestMethod(HttpRequestMethodNotSupportedException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EmailInvalidException.class)
    public ResponseEntity<ErrorMessage> handlerEmailInvalid(EmailInvalidException ex){
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EntityExistsWithAtributeException.class)
    public ResponseEntity<ErrorMessage> handlerEntityExistsWithAtribute(EntityExistsWithAtributeException ex){
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundCustomException.class)
    public ResponseEntity<ErrorMessage> handlerEntityNotFound(EntityNotFoundCustomException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
