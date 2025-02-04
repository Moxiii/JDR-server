package com.moxi.jdrserver.Config.Interceptor;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Config.Utils.JwtUtils;
import com.moxi.jdrserver.Config.Utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
  @Autowired
  private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerInterceptor){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireAuthorization requireAuthorization = handlerMethod.getMethodAnnotation(RequireAuthorization.class);
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/auth")) {
            return true;
        }
        if (requireAuthorization != null) {
           if(!SecurityUtils.isAuthenticated(request , jwtUtils)){
               response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
               return false;
            }
        }}
        return true;
    }
}
