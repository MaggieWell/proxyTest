package com.maggie.proxyPattern.dbRoute.proxy;

import com.maggie.proxyPattern.dbRoute.db.DynamicDataSourceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-26-08:28:25
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceDynamicProxy.class);

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private Object proxyObj;

    public Object getInstance(Object proxyObj) {

        this.proxyObj = proxyObj;
        Class<?> clazz = proxyObj.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);

        Object object = method.invoke(proxyObj,args);

        after();
        return object;
    }

    /**
     *
     * @param target 订单对象order
     */
    private void before(Object target) {

        //还原默认的数据源
        System.out.println("Proxy before method");

        //约定优于配置
        try {
            long time = (long)target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter = Integer.valueOf((yearFormat.format(new Date())));
            System.out.println("动态代理类自动分配到【BD_"+dbRouter+"]数据源处理数据。");
            DynamicDataSourceEntity.set(dbRouter);
        } catch (Exception e) {
           LOGGER.error("",e);
        }
    }

    private void after() {
        //进行数据源切换
        LOGGER.info("Proxy after method");

        DynamicDataSourceEntity.restore();

    }
}
