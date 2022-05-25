package com.wbh.emall.common.handler;

import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.entity.ResultCode;
import com.wbh.emall.common.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * @author WBH
 * @since 2022/2/14
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.wbh.emall"})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * 通用异常拦截器
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result<Object> commomExceptionHandler(HttpServletRequest request, Throwable ex) {
        log.error("异常信息: ", ex);
        return Result.failed(ResultCode.FAILED, ex.getMessage());
    }
    
    /**
     * 认证异常拦截器
     */
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public Result<Object> handleAuthenticationException(HttpServletRequest request, Throwable ex) {
        log.error("异常信息: ", ex);
        return Result.failed(ResultCode.UNAUTHORIZED, ex.getMessage());
    }
    
    /**
     * 仅输出用户堆栈
     */
    private void logUserStack(Throwable ex) {
        StringBuffer userStackTrace = Stream.of(ex.getStackTrace())
                .filter(e -> e.getClassName().startsWith("com.wbh.emall"))
                .collect(() -> new StringBuffer(ex + "\n\t"),
                        (stringBuffer, stackTraceElement) ->
                                stringBuffer.append(stackTraceElement)
                                        .append("\n\t"),
                        StringBuffer::append);
        log.error("用户堆栈: \n{}", userStackTrace);
    }
    
}
