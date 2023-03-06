package com.nbcamp.gamematching.matchingservice.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        if (token != null) {
            if (!jwtUtil.validateToken(token)) {
                Cookie[] rc = request.getCookies();
                String refreshtoken;
                for (Cookie cookie : rc) {
                        if (cookie.getName().equals("refreshtoken")) {
                            refreshtoken = cookie.getValue();
                        }else {
                            return;
                        }
                Claims ATInfo = AuthenticatedUserByToken(token);
                if (jwtUtil.getRefreshTokenIsTrue(ATInfo.getSubject(), refreshtoken)) {
                    var member = jwtUtil.AuthenticatedMember(ATInfo.getSubject());
                    log.info("= AccessToken Response = MEMBEREMAIL {}",member.getEmail());
                    response.addHeader(jwtUtil.AUTHORIZATION_HEADER,
                            jwtUtil.createAccessToken(member.getEmail(),member.getRole()));
                }
            }
        }
            AuthenticatedUserByToken(token);
    }
        filterChain.doFilter(request, response);
}

public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private Claims AuthenticatedUserByToken(String token) {
        Claims accessTokenInfo = jwtUtil.getUserInfoFromToken(token);
        setAuthentication(accessTokenInfo.getSubject());
        return accessTokenInfo;
    }
}
