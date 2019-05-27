package com.maggie.proxyPattern.dbRoute;

import com.maggie.proxyPattern.dbRoute.proxy.OrderServiceDynamicProxy;
import com.maggie.proxyPattern.dbRoute.proxy.OrderServiceStaticProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-19
 * Time:18:01
 */
public class DBRouterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBRouterTest.class);

     /* ---------------- 代码分隔注释 -------------- */

    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        staticProxyTest(order);
        dynamicProxyTest(order);

    }

    /**
     * 使用动态代理实现
     */
    private static void dynamicProxyTest(Order order) {

        OrderService orderServiceDynamicProxy = (OrderService) new OrderServiceDynamicProxy().getInstance(new OrderServiceImpl());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = simpleDateFormat.parse("2019-05-19");
            order.setCreateTime(date.getTime());
            orderServiceDynamicProxy.createOrder(order);

        } catch (ParseException e) {
            LOGGER.error("",e);
        }

    }

    /**
     * 使用静态代理实现
     */
    private static void staticProxyTest(Order order) {


        OrderService orderService = new OrderServiceStaticProxy(new OrderServiceImpl());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse("2017-05-19");
            order.setCreateTime(date.getTime());
            orderService.createOrder(order);

        } catch (ParseException e) {
            LOGGER.error("",e);
        }
    }
}
