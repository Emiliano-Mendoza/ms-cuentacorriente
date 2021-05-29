package com.dan.cuentacorriente.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.dan.cuentacorriente.service.MedioPagoService.*(..))")
	private void MedioPagoServiceMetodos() {};
	
	@Pointcut("execution(* com.dan.cuentacorriente.service.PagoService.*(..))")
	private void PagoServiceMetodos() {};
	
	@Before("MedioPagoServiceMetodos() || PagoServiceMetodos()")
	public void hacerAntes(JoinPoint jp) {
		System.out.println("\n");
		
		//TRACE -> DEBUG -> INFO -> WARN -> ERROR -> FATAL
		logger.info("A continuacion se ejecutara el metodo: " + jp.getSignature().getName());
		logger.info("Metodo de la clase: " + jp.getTarget().getClass());
		logger.info("Argumentos: " + Arrays.toString(jp.getArgs()));
	}
	
	@After("MedioPagoServiceMetodos() || PagoServiceMetodos()")
	public void hacerDespues(JoinPoint jp) {
		logger.info("Se ha ejecutado el metodo: "+ jp.getSignature().getName());
	}
}	
