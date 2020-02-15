package com.example.demo.aop;

import com.example.demo.annotation.Secure;
import com.example.demo.utils.CryptPojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Configuration
public class DesAndEncAspect {

    //切所有mapper
    @Pointcut("execution(public * com.example.demo.dao..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundReturning(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取dao层接口参数注解 start
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                //方法参数包含注解,进行加密
                if (annotation instanceof Secure) {
                    args[i] = CryptPojoUtils.encryptObject(args[i]);
                }
            }
        }
        //获取dao层接口参数注解 end

        //获取参数实体类注解
        CryptPojoUtils.encryptFields(args);
        //执行方法
        Object proceed = joinPoint.proceed(args);
        if (null == proceed) {
            return null;
        }
        //解密
        CryptPojoUtils.decryptFieldOrList(proceed);
        return proceed;
    }
}
