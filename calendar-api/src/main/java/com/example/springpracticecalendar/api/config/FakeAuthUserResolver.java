package com.example.springpracticecalendar.api.config;

import com.example.springpracticecalendar.api.dto.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FakeAuthUserResolver implements HandlerMethodArgumentResolver {
    // query parameter로 userId를 넘겨주면 AuthUserResolver와 똑같이 동작한다. 테스트 시에 편하다.
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        final String userIdStr = webRequest.getParameter("userId");
        if(userIdStr == null){
            return new AuthUserResolver().resolveArgument(
                    parameter,
                    mavContainer,
                    webRequest,
                    binderFactory
            );
        }
        return AuthUser.of(Long.parseLong(userIdStr));
    }
}
