package com.eutopia.aop;

import com.eutopia.domain.ResultBean;
import com.eutopia.exception.CheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestfulAspect {

    private static final Logger logger = LoggerFactory.getLogger(RestfulAspect.class);

    @Pointcut("execution(* com.eutopia.restful.*.*(..))")
    public void returnHandler() {}

    @Around("returnHandler()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + " use time: " + (System.currentTimeMillis() - startTime));
        } catch (Throwable throwable) {
            result = handlerException(pjp, throwable);
        }
        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint php, Throwable e) {
        ResultBean<?> result = new ResultBean<>();
        if (e instanceof CheckException) {
            result.setCode(ResultBean.FAIL);
            result.setMsg(e.getMessage());
        } else {
            logger.error(php.getSignature() + " error " , e);
            result.setCode(ResultBean.FAIL);
            result.setMsg(e.toString());
        }
        return result;
    }
}
