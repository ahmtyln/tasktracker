package com.tasktracker.tasktracker.config;

import com.tasktracker.tasktracker.security.CustomUserDetails;
import com.tasktracker.tasktracker.services.CustomUserDetailsService;
import com.tasktracker.tasktracker.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = header.substring(7);
        String username = jwtService.getUserNameFromToken(token);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token,userDetails)){
               Authentication authentication =
                       new UsernamePasswordAuthenticationToken
                               (userDetails,null,userDetails.getAuthorities());

               SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request,response);

    }
}
