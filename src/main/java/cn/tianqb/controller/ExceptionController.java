package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/5 14:29
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public WebResult appException(AppException ex) {
        return WebResult.build(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public WebResult exception(Exception ex) {
        log.error("Server exception", ex);
        return WebResult.build(HttpStatus.SERVICE_UNAVAILABLE.value(), "服务异常，稍后再试");
    }

}
