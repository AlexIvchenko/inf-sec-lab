package com.github.infseclab.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(public * com.github.infseclab.controller..*.*(..))")
    public void controllers() {

    }

    @Pointcut("execution(public * com.github.infseclab.service..*.*(..))")
    public void service() {

    }

    @Pointcut("execution(public * com.github.infseclab.security..*.*(..))")
    public void security() {

    }

    @Pointcut("security() || service() || controllers()")
    public void any() {

    }

    @Before("any()")
    public void logBefore(JoinPoint jp) {
        log.info("invoke {}", lazyFormatMethodSignature(jp));
    }

    @AfterReturning(value = "any()", returning = "ret")
    public void logAfterReturning(JoinPoint jp, Object ret) {
        log.info("method {} returned: {}", lazyFormatMethodSignature(jp), ret);
    }

    @AfterThrowing(value = "any() || controllers()", throwing = "thr")
    public void logAfterThrowing(JoinPoint jp, Throwable thr) {
        log.error("method {} thrown: {}", lazyFormatMethodSignature(jp), thr);
    }

    private Object lazyFormatMethodSignature(JoinPoint jp) {
        return new Object() {
            @Override
            public String toString() {
                return formatMethodSignature(jp);
            }
        };
    }

    private String formatMethodSignature(JoinPoint jp) {
        String method = jp.getSignature().toShortString();
        String args = fetchArgs(jp);
        return method.replace("..", args);
    }

    private String fetchArgs(JoinPoint jp) {
        List<String> args = Stream.of(jp.getArgs())
                .map(String::valueOf)
                .collect(Collectors.toList());
        return String.join(", ", args);
    }
}
