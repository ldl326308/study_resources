package com.nf.lc.aop.study.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnimalAdvice {


    /**
     * 为了复用
     */
    @Pointcut("execution(* *.play(..))")
    public void playTemp() {
    }

    /**
     * 被代理对象的方法前调用
     */
    @Before("playTemp()")
    public void beforePlay() {
        System.out.println("方法调用前执行。。。");
    }

    /**
     * 被代理对象的方法后调用
     */
    @After("execution(* *.play(..))")
    public void afterPlay() {
        System.out.println("方法调用后执行。。。");
    }

    /**
     * 被代理对象的方法正常返回时执行
     */
    @AfterReturning("execution(* *.play(..))")
    public void afterReturningPlay() {
        System.out.println("正常返回执行。。。");
    }

    /**
     * 被代理对象的方法发生异常时执行
     */
    @AfterThrowing("execution(* *.play(..))")
    public void afterThrowingPlay() {
        System.out.println("出现异常了。。。");
    }

    @Around("playTemp()")
    public Object aroundPlay(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知开始");

        Object result = null;

        try {
            //得到方法返回值，如果是void则返回null
            result = joinPoint.proceed();

            //获得参数的方法
            //joinPoint.getArgs()[0]

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知异常");
        }

        System.out.println("环绕通知结束");

        return result;
    }

}
