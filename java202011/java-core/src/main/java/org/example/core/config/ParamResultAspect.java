package org.example.core.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.core.annotation.MethodAnnotation;
import org.example.core.protocol.CommonResult;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/22 0:13
 */
@Slf4j
@Aspect
@Component
public class ParamResultAspect {

    ParamResultAspect() {
        log.info("initialize ParamResultAspect");
    }

    @Pointcut("@annotation(org.example.core.annotation.MethodAnnotation)")
    public void methodParamPointCut() {
    }

    @Around("methodParamPointCut()")
    public  Object around(ProceedingJoinPoint point){
        Object object = null;
        CommonResult result = null;
        Class<?> aClass = point.getTarget().getClass();
        String name = point.getSignature().getName();

        try {
            Method method = aClass.getMethod(name);
            MethodAnnotation annotation = method.getAnnotation(MethodAnnotation.class);
            object = point.proceed();
            if(object instanceof CommonResult){
                result = (CommonResult) object;
                result.setCommand(annotation.name());
                result.setTimestamp(System.currentTimeMillis());
                return  result;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }

}
