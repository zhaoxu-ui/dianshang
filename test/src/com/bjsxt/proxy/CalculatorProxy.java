package com.bjsxt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {
    public static Calculator getProxy(final Calculator calculator){
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = null;
                try {
                    invoke = method.invoke(calculator, args);
                } catch (Exception e) {
                   e.printStackTrace();
                }
                return invoke;
            }
        };
        ClassLoader loader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        Calculator proxy = (Calculator) Proxy.newProxyInstance(loader,interfaces,h);
        return proxy;


    }
}
