package com.laptopshop.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AdminAccessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra nếu người dùng đã xác thực và đang cố gắng truy cập trang admin
        if (authentication != null && authentication.isAuthenticated()) {
            String requestURI = request.getRequestURI();

            // Nếu URL không chứa "admin" và người dùng không có ROLE_ADMIN
            if (requestURI.contains("/admin") && !authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                // Chuyển hướng đến trang thông báo hoặc trang chủ
                response.sendRedirect("/access-denied");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
