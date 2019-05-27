package com.maggie.proxyPattern.dbRoute.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Description:使用cglib动态代理
 * author:MaggieHao
 * Date:2019-05-26
 * Time:13:46
 */
public class CGlibMatcher implements MethodInterceptor {

     private static final Logger LOGGER = LoggerFactory.getLogger(CGlibMatcher.class);

    public Object getInstance(Class<?> clazz) throws Exception {
        //相当于Proxy，代理工具类
        Enhancer enhancer = new Enhancer();
        //要把哪个设置为即将生成的心类父类

        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //业务的增强
        before();
        Object obj = methodProxy.invokeSuper(o,objects);
        after();
        return obj;

    }

    private void before(){
        LOGGER.info("我是媒婆，我要给你找对象，现在已经确认你的需求。");

    }

    private void after(){
        LOGGER.info("ok的话就准备办事吧。");
    }
}
