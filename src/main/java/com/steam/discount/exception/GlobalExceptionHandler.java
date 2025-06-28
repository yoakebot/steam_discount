package com.steam.discount.exception;

import com.steam.discount.model.ResultVO;
import com.steam.discount.util.ResultVoUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    public ResultVO<Object> handleException(HttpServletRequest request, Exception ex) {
        log.error("请求URL: {} 出现异常: ", request.getRequestURI(), ex);
        return ResultVoUtil.fail("系统错误，请稍后重试");
    }

    // 处理自定义业务异常，比如 ServiceException
    @ExceptionHandler(BizException.class)
    public ResultVO<Object> handleServiceException(BizException ex) {
        // 业务异常返回前端对应错误信息
        return ResultVoUtil.fail(ex.getMessage());
    }

    // 处理参数绑定异常，如请求参数格式错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<Object> handleValidException(MethodArgumentNotValidException ex) {
        String errorMsg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResultVoUtil.fail("参数校验失败：" + errorMsg);
    }
}
