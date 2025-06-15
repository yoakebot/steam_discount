package com.steam.discount.exception;

import com.steam.discount.model.ResultVO;
import com.steam.discount.util.ResultVoUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Object> handleException(HttpServletRequest request, Exception ex) {
        log.error("请求URL: {} 出现异常: ", request.getRequestURI(), ex);
        return ResultVoUtil.fail("系统错误，请稍后重试");
    }

    // 处理自定义业务异常，比如 ServiceException
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Object> handleServiceException(BizException ex) {
        // 业务异常返回前端对应错误信息
        return ResultVoUtil.fail(ex.getMessage());
    }

    // 处理参数绑定异常，如请求参数格式错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Object> handleValidException(MethodArgumentNotValidException ex) {
        String errorMsg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResultVoUtil.fail("参数校验失败：" + errorMsg);
    }

    // 你可以继续添加其他异常处理方法，比如认证异常、访问权限异常等
}
