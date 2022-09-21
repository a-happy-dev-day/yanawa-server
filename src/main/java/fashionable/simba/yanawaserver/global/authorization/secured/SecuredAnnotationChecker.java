package fashionable.simba.yanawaserver.global.authorization.secured;


import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.context.SecurityContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class SecuredAnnotationChecker {
    @Before("@annotation(fashionable.simba.yanawaserver.global.authorization.secured.Secured)")
    public void checkAuthorities(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Secured secured = method.getAnnotation(Secured.class);
        List<String> values = Arrays.stream(secured.value()).collect(Collectors.toList());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities()
            .stream()
            .noneMatch(values::contains)) {
            throw new RoleAuthenticationException("권한이 없습니다.");
        }
    }
}
