package com.shivmkp.student_management_system.filter;


import com.shivmkp.student_management_system.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@AllArgsConstructor
@Component

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader=request.getHeader("Authorization");
        String username=null;
        String jwt=null;

        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
            jwt=authorizationHeader.substring(7);
            username= jwtUtil.extractStudentId(jwt);
        }
        if (username!=null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt)){
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }
        filterChain.doFilter(request,response);
    }
}
