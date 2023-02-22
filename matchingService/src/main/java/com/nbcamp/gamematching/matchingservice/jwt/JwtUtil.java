package com.nbcamp.gamematching.matchingservice.jwt;

import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserDetailsServiceImpl userDetailsService;
    private final StringRedisTemplate stringRedisTemplate;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final long ACCESS_TOKEN_TIME = 60 * 60 * 1000L;
    private static final long REFRESH_TOKEN_TIME = 24 * 60 * 60 * 1000L;
    private static final int COOKIE_TIME = 24 * 60 * 60;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String createAccessToken(String email, MemberRoleEnum role) {
        Date date = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }
    public String createdRefreshToken(String email) {
        Date date = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email)
                        .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public Cookie createCookie(String email) throws UnsupportedEncodingException {
        String cookieName = "refreshtoken";
        String cookieValue = createdRefreshToken(email);
        var RTcookie = URLEncoder.encode(cookieValue, "utf-8");
        Cookie cookie = new Cookie(cookieName, RTcookie);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_TIME);
        return cookie;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            log.info("TokenException");
        } catch (ExpiredJwtException e) {
            log.info("Token Expired");
        }
        return false;
    }

    public Member AuthenticatedMember(String email) {
        return userDetailsService.loadMemberByEmail(email);
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }

    public boolean getRefreshTokenIsTrue(String email,String refreshToken) {
        return getRefreshTokenByRedis(email).equals(refreshToken);
    }
    public String getRefreshTokenByRedis(String email) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println("Redis key : " + email);
        System.out.println("Redis value : " + stringStringValueOperations.get(email));
        return stringStringValueOperations.get(email);
    }
}