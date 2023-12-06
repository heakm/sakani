package com.hadef.sakani.filters;

import com.hadef.sakani.domain.entity.UserPrincipleSecondary;
import com.hadef.sakani.domain.service.impl.ApplicationUserDetailsService;
import com.hadef.sakani.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final ApplicationUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    public JwtRequestFilter(ApplicationUserDetailsService userDetailsService, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (
                authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
        ) {
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        if (
                username != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null
        ) {
            UserDetails userDetails =
                    this.userDetailsService.loadUserByUsername(username);
            UserPrincipleSecondary userPrincipleSecondary = modelMapper.map(userDetails, UserPrincipleSecondary.class);
            if (jwtUtil.validateToken(token, userPrincipleSecondary)) {
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
