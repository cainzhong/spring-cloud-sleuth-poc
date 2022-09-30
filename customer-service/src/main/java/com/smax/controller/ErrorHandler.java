package com.smax.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/30/2022
 */
@ControllerAdvice
@Slf4j
public class ErrorHandler {
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public String handleInternalError(Exception e) {
    log.error("internal server error", e);
    return String.format("Internal Server Error (traceId: %s)", MDC.get("X-B3-TraceId"));
  }
}
