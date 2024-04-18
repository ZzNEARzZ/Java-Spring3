package com.jhon.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {
    private Logger logger= LoggerFactory.getLogger(this.getClass());    

    @Before("GreetingServicePointcuts.greetingFooLoggerPointCut()") //se puede utilizar el tipo de variable de la clase o el interceptor como tambien poner * para que acepte todos los tipos
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes Primero: " + method + " Invocado con los parametros " + args);
    }

    @After("GreetingServicePointcuts.greetingFooLoggerPointCut()") //se puede utilizar el tipo de variable de la clase o el interceptor como tambien poner * para que acepte todos los tipos
    /*Tambien recordar que al inicio del execution se puede poner * para que te devuelva cualquier tipo de valor o si no especificar que tipo de variable retorna el metodo */
    public void loggerAfter(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despuess Primero: " + method + " con los parametros " + args);
    }

}
