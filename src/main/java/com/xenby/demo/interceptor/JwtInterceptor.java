package com.xenby.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xenby.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 未提供 token
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("not login");
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }

        // 提供的 token 無效
        try {
            jwtService.validateToken(requestTokenHeader.substring(7));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token not validated");
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        // do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        // do nothing
    }
}
