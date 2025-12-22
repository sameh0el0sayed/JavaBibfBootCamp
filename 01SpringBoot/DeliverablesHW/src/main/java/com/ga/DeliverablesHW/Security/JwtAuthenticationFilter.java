package com.ga.DeliverablesHW.Security;

import com.ga.DeliverablesHW.Entity.User;
import com.ga.DeliverablesHW.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                if (!jwtTokenProvider.validateToken(token)) {
                     sendUnauthorized(response, "Invalid or expired token");
                    return;
                }

                String username = jwtTokenProvider.getUsernameFromToken(token);
                User user = userRepository.findByUsername(username).orElse(null);

                if (user == null) {
                     sendUnauthorized(response, "User not found for this token");
                    return;
                }


            } catch (Exception ex) {
                 sendUnauthorized(response, "Invalid or expired token: " + ex.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
        response.getWriter().flush();
    }
}
