package com.niit.Users.Filter;

import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        ServletOutputStream pw =httpServletResponse.getOutputStream();
        //expects the token  to come from header
        String authHeader=httpServletRequest.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer")){
            //If token is not coming than set the response status as UNAUTHORIZED
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            pw.println("Missing or invalid Token ");
            pw.close();

        }else {//extract token from the header
            String jwtToken = authHeader.substring(7);//Bearer => 6+1 since token begins with Bearer
            //token validation
            String username = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("username", username);
            // pass the claims in the request
            filterChain.doFilter(servletRequest, servletResponse);

        }
    }
    }

