package aoss.assignment.restservice.auth;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {

    public AuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION) != null ?
                httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION) : "";
        token = token.replace("Bearer", "").trim();

        Authentication reqAuthentication = new UsernamePasswordAuthenticationToken(token,token);
        return getAuthenticationManager().authenticate(reqAuthentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request,response);
    }
}
