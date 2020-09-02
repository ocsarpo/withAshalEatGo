package kr.co.fastcampus.eatgo.filters;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.eatgo.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);

        this.jwtUtil = jwtUtil;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain
    ) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);

        if (authentication != null) {
            // 쓰고 있는 컨텍스트에 인가 해줌
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        // authentication여부와 상관없이 doFilter는 항상 실행되어야함
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        // JWT 분석
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }

//        Authorization: Bearer TOKEN.asdasd 의 형태이기 때문에 그 부분 빼고 토큰 전달
        Claims claims = jwtUtil.getClaims(token.substring(("Bearer ".length())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);
        return authentication;
    }
}
