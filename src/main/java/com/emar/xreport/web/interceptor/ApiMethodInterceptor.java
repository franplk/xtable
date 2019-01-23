package com.emar.xreport.web.interceptor;

import com.emar.xreport.exception.BusinessException;
import com.emar.xreport.exception.PageException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

public class ApiMethodInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiMethodInterceptor.class);
    private static final String DEFAULT_ERROR_MSG = "Server Exception";

    @Override
    public Object invoke(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        String interName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        StopWatch stopWatch = new StopWatch(interName);
        try {
            stopWatch.start();
            logger.debug("Entering {} And Start to Run..", interName);
            return invocation.proceed();
        } catch (Throwable ex) {
            String exMsg = deployException(ex);
            logger.debug("Run {} throw Exception[{}].", interName, exMsg);
            // 判断接口是数据还是页面接口
            RestController restAnnotation = method.getDeclaringClass().getAnnotation(RestController.class);
            if (restAnnotation == null) {
                ResponseBody annotation = method.getAnnotation(ResponseBody.class);
                if (annotation == null) {
                    throw new PageException(exMsg);
                }
            }
            throw new PageException(exMsg);
        } finally {
            if(stopWatch.isRunning()) {
                stopWatch.stop();
            }
            logger.debug("Performing {} completed in [{}] ms.", interName, stopWatch.getTotalTimeMillis());
        }
    }

    private String deployException(Throwable ex) {
        ex.printStackTrace();
        if (ex instanceof BusinessException) {
            return ex.getMessage();
        }
        return DEFAULT_ERROR_MSG;
    }
}
