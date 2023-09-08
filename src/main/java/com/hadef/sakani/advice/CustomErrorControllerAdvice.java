package com.hadef.sakani.advice;

import com.hadef.sakani.domain.value.ErrorCodeEnum;
import com.hadef.sakani.exceptions.CustomException;
import com.hadef.sakani.exceptions.ErrorDTO;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

import static com.hadef.sakani.util.LoggingUtil.logError;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomErrorControllerAdvice extends ResponseEntityExceptionHandler {

    private final CustomException customException =
            new CustomException(ErrorCodeEnum.DEFAULT.getCode()+" - " +ErrorCodeEnum.DEFAULT.getDescription());
    private final String serviceName;

    public CustomErrorControllerAdvice() {
        this.serviceName = this.getClass().getName();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMostSpecificCause().getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause());
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMostSpecificCause().getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("HttpMessageNotReadableException");
        logError(ex.getMessage(), ex.getCause());
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .code(ex.getMessage().split("\n")[0])
                .message(parseMessage(ex.getMessage()))
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex.getMessage(), ex.getCause(),headers, status, request);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .code(ex.getMessage().split("\n")[0])
                        .message(parseMessage(ex.getMessage()))
                .build());
    }

    private String parseMessage(String message) {
        String[] split = message.split(",");
        StringBuilder builder = new StringBuilder();
        Arrays.stream(split).iterator().forEachRemaining(
                c -> {
                    builder.append(c.split("-"));
                    builder.append(", ");
                }
        );
        return builder.toString();
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        logError(ex.getMessage(), ex.getCause(),headers, status, webRequest);
        return ResponseEntity.status(BAD_REQUEST).body(ErrorDTO.builder()
                .message(ex.getMessage().split("\n")[0])
                .build());
    }

}
