package com.hey.request.system.exception.handler;

import com.hey.request.system.exception.state.StateCode;
import com.hey.request.system.utils.Q;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice(basePackages = "com.hey.request.system.controller")
public class RequestExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Q handleValidException(MethodArgumentNotValidException e) {

    BindingResult result = e.getBindingResult();

    HashMap<String, String> errorMap = new HashMap<>();

    result.getFieldErrors().forEach(fieldError -> {
      errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
    });

    return Q.error(StateCode.ADMINISTRATION_VALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_VALID_EXCEPTION.getMsg()).put("data", errorMap);
  }

  @ExceptionHandler(value = UserExistException.class)
  public Q handlerUserExistException() {
    return Q.error(StateCode.ADMINISTRATION_USER_EXIST_EXCEPTION.getCode(), StateCode.ADMINISTRATION_USER_EXIST_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = UserNotFoundException.class)
  public Q handlerUserNotFoundException() {
    return Q.error(StateCode.ADMINISTRATION_USER_NOT_FOUND_EXCEPTION.getCode(), StateCode.ADMINISTRATION_USER_NOT_FOUND_EXCEPTION.getMsg());
  }

//  @ExceptionHandler(value = PermissionDeniedException.class)
//  public Q handlerPermissionDeniedException() {
//    return Q.error(StateCode.ADMINISTRATION_PERMISSION_DENIED_EXCEPTION.getCode(), StateCode.ADMINISTRATION_PERMISSION_DENIED_EXCEPTION.getMsg());
//  }
}
