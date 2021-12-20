package com.bridgelabz.employeepayrollapp.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Purpose: Logging Advice for handling loggers.
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@Component
@Aspect
public class LoggingAdviceAOP {

    Logger log = LoggerFactory.getLogger(LoggingAdviceAOP.class);

    /**
     * Purpose : ApplicationLogger method to format the logs.
     *
     * @param pjp : pjp provides all static information about the method
     * @return : returns object with formatted logs
     * @throws Throwable : Exception
     */
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

    /**
     * Purpose : Method used for pointcut
     */
    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*.*(..))")
    public void pointCut() {
    }
}
