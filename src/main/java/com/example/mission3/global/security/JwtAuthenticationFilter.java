package com.example.mission3.global.security;

import com.example.mission3.domain.admin.dto.AdminRequestDto.SigninAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.SigninAdminResponseDto;
import com.example.mission3.domain.admin.entity.type.AuthorityType;
import com.example.mission3.global.jwt.JwtUtil;
import com.example.mission3.global.util.CustomResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/v1/admins/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SigninAdminRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SigninAdminRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        AuthorityType role = ((UserDetailsImpl) authResult.getPrincipal()).getAdmin().getAuthority();

        String token = jwtUtil.createToken(username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        SigninAdminResponseDto responseDto = new SigninAdminResponseDto(userDetails.getAdmin());
        CustomResponseUtil.success(response, responseDto);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        CustomResponseUtil.fail(response, "로그인 실패", HttpStatus.UNAUTHORIZED);
    }
}
