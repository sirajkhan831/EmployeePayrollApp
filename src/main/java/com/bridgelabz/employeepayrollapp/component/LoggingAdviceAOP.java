package com.bridgelabz.employeepayrollapp.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAdviceAOP {

    Logger log = LoggerFactory.getLogger(LoggingAdviceAOP.class);

    @Around("pointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        Object object = new Object();
        log.info("Method invoked " + className + " : " + methodName + "()" + "arguments : " + Arrays.toString(array));
        object = pjp.proceed();
        if (object != null) {
            log.info(className + " : " + methodName + "()" + "Response : " + object.toString());
        }
        return object;
    }

    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..))")
    public void pointCut() {
    }
}
