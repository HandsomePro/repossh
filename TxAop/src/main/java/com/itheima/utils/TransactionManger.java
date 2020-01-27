package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("txManager")
@Aspect
public class TransactionManger {
	@Autowired
	private ConnectU connectionUtils;

	@Pointcut("execution(* com.itheima.service.impl.*.*(..))")
	public void pt() {

	}

	public void beginTransaction() {
		try {
			connectionUtils.getThreadLocalConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			connectionUtils.getThreadLocalConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void roolback() {
		try {
			connectionUtils.getThreadLocalConnection().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void release() {
		try {
			connectionUtils.getThreadLocalConnection().close();
			connectionUtils.removeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Around("pt()")
	public Object objectAround(ProceedingJoinPoint pjp) {
		Object reValue = null;
		try {
			this.beginTransaction();
			Object[] args = pjp.getArgs();
			reValue = pjp.proceed(args);
			this.commit();
			return reValue;
		} catch (Throwable t) {
			this.roolback();
			throw new RuntimeException("环绕通知异常");
		} finally {
			this.release();
		}
	}
}
