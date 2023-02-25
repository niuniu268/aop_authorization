package com.example.aop;

import com.example.annotation.Access;
import com.example.utils.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import static com.example.utils.CacheManager.userset;

@Component
@Aspect
public class AopConfig {
    @Pointcut("@annotation(com.example.annotation.Access)")
    public static void cut(){};
    @Before( "cut()" )
    public static void before(JoinPoint joinPoint){

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes( );

        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;

        HttpServletRequest request = attributes.getRequest( );

        String username = request.getParameter( "username" );

        Set <String> stringSet = userset.get( username );

        MethodSignature signature = (MethodSignature) joinPoint.getSignature( );

        Method method = signature.getMethod( );

        Access declaredAnnotation = method.getDeclaredAnnotation( Access.class );

        if(declaredAnnotation != null && (stringSet == null || !stringSet.contains( declaredAnnotation.value() ))){

            throw new RuntimeException( "no authorization" );


        }


    }
}
