package fashionable.simba.yanawaserver.auth.authorization;

import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.context.SecurityContextHolder;
import fashionable.simba.yanawaserver.auth.userdetails.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new User(authentication.getPrincipal().toString(), authentication.getAuthorities());
    }
}
