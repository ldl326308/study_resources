package com.nf.lc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class JdkProxyMath implements InvocationHandler {

    Object targetObject;

    public Object getProxyObject(Object object) {
        this.targetObject = object;
        return Proxy.newProxyInstance(
                targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法：" + method.getName());
        long startTime = System.currentTimeMillis();
        lazy();
        Object result = null;
        try {
            result = method.invoke(targetObject, args);
        } catch (Exception e) {
            System.err.println("出现异常了。。。。。。。。。。。。。。。。");
        }
        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;
        System.out.println("共用时间：" + time);

        return result;
    }


    /**
     * 模拟时间延迟
     */
    public void lazy() {
        try {
            Thread.sleep((int) new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
