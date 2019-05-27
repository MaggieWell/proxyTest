package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;

import java.lang.reflect.Method;

/**
 * Description: 自己实现jdk的invoke方法
 * author:MaggieHao
 * Date:2019-05-25
 * Time:22:09
 */
public interface MaggieInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}
