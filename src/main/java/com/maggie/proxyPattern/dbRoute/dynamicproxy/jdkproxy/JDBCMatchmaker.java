package com.maggie.proxyPattern.dbRoute.dynamicproxy.jdkproxy;

import com.maggie.proxyPattern.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:媒婆
 * author:MaggieHao
 * Date:2019-05-19
 * Time:20:25
 */
public class JDBCMatchmaker implements InvocationHandler {

     private static final Logger LOGGER = LoggerFactory.getLogger(JDBCMatchmaker.class);

    private Person person;
    public Object getInstance(Person person){
        this.person = person;
        Class<?> clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.person,args);
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
