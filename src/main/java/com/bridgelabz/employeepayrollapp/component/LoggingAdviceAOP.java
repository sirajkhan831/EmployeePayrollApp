package com.bridgelabz.employeepayrollapp.component;

import com.bridgelabz.employeepayrollapp.exception.ResourceException;
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
    public Object applicationLogger(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        Object object;
        try {
            log.info("Method invoked " + className + " : " + methodName + "()" + "arguments : " + Arrays.toString(array));
            object = pjp.proceed();
            log.info(className + " : " + methodName + "()" + "Response : " + object.toString());
        } catch (Throwable e) {
            throw new ResourceException();
        }
        return object;
    }

    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..))")
    public void pointCut() {
    }
}
