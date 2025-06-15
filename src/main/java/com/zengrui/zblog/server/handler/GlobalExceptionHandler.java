package com.zengrui.zblog.server.handler;

import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return Result.error("发生错误!发生错误!!!");
    }

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.warn("️业务异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
