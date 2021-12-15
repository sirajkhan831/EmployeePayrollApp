package com.bridgelabz.employeepayrollapp.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdviceAOP {

    Logger log = LoggerFactory.getLogger(LoggingAdviceAOP.class);

    @Around("pointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("Method invoked " + className + " : " + methodName + "()" + "arguments : " + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
        return object;
    }

    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..))")
    public void pointCut() {
    }
}
