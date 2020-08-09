package com.xenby.demo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xenby.demo.exception.ForbiddenException;
import com.xenby.demo.exception.UnauthorizedException;
import com.xenby.demo.model.TokenUserDetails;
import com.xenby.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 提供的 token 無效
        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null) {
                TokenUserDetails tokenUserDetails = jwtService.loadUserDetailByToken(requestTokenHeader.substring(7));
                // 紀錄登入資料
                ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + tokenUserDetails.getRole()));
                PreAuthenticatedAuthenticationToken authentication =
                        new PreAuthenticatedAuthenticationToken(tokenUserDetails, null, grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
