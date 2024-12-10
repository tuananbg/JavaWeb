package com.laptopshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            this.redirectStrategy.sendRedirect(request, response, "/admin");
        } else {
            // Kiểm tra các vai trò khác và chuyển hướng tương ứng
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_MEMBER")) {
                    this.redirectStrategy.sendRedirect(request, response, "/");
                    return;
                } else if (authority.getAuthority().equals("ROLE_SHIPPER")) {
                    this.redirectStrategy.sendRedirect(request, response, "/shipper");
                    return;
                }
            }
            // Nếu không có vai trò phù hợp, có thể chuyển hướng về trang lỗi hoặc trang chủ
            this.redirectStrategy.sendRedirect(request, response, "/access-denied");
        }
    }
}
