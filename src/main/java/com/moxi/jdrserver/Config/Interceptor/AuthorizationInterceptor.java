package com.moxi.jdrserver.Config.Interceptor;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireAuthorization requireAuthorization = handlerMethod.getMethodAnnotation(RequireAuthorization.class);
        //TODO implement JWT for real auth :)
        //PS postman request now require Authorization header with True value
        if (requireAuthorization != null) {
            if (authorization != null && authorization.startsWith("True ")) {
                return true;
            }
        }
        return false;
    }
}
