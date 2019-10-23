package br.com.itau.postservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Pointcut("execution(* br.com.itau.postservice.controller.*.*(..))")
    public void controllers() {}

    @Before("controllers()")
    public void logBeforeRequest(JoinPoint joinPoint) {
        LOGGER.info("msg=\"started request\" controller=\"" + joinPoint.getSignature().getDeclaringType().getSimpleName() + "\" method=\"" + joinPoint.getSignature().getName() + "\"");
    }

    @After("controllers()")
    public void logAfterRequest(JoinPoint joinPoint) {
        LOGGER.info("msg=\"finished request\" controller=\"" + joinPoint.getSignature().getDeclaringType().getSimpleName() + "\" method=\"" + joinPoint.getSignature().getName() + "\"");
    }

}
