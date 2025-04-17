package com.example.board.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String, Object>> handleUserException(UserException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("detailStatusCode", ex.getDetailStatusCode());
        errorBody.put("message", ex.getErrorMessage());

        // 일반적으로 에러 코드에 따라 상태코드 변경도 가능하지만, 지금은 모두 409로 가정
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
    }
}
