package com.wbh.emall.common.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.*;
import java.io.IOException;

/**
 * 设置数据返回格式为json
 *
 * @author WBH
 * @since 2022/2/23
 */
// @RestControllerAdvice("com.wbh.emall")
public class GlobalRestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
