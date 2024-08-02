package com.beratyesbek.oneglobal.controller;

import com.beratyesbek.oneglobal.exception.OneGlobalException;
import com.beratyesbek.oneglobal.modal.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(OneGlobalException.class)
    public ResponseEntity<ErrorDTO> handle(OneGlobalException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .message(List.of(exception.getMessage()))
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException exception) {
        List<String> messages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(error.getDefaultMessage());
            log.error("An validation error occurred {}", error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDTO.builder()
                        .message(messages).build());
    }



}
