package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Description:媒婆
 * author:MaggieHao
 * Date:2019-05-19
 * Time:20:25
 */
public class MaggieMatchmaker implements MaggieInvocationHandler {

     private static final Logger LOGGER = LoggerFactory.getLogger(MaggieMatchmaker.class);

    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return MaggieProxy.newProxyInstance(new MaggieClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target,args);
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
