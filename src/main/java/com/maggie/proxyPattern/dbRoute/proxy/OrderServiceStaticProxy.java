package com.maggie.proxyPattern.dbRoute.proxy;
import com.maggie.proxyPattern.dbRoute.Order;
import com.maggie.proxyPattern.dbRoute.OrderService;
import com.maggie.proxyPattern.dbRoute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:54
 */
public class OrderServiceStaticProxy implements OrderService {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private OrderService orderService;
    public OrderServiceStaticProxy(OrderService orderService){
        this.orderService = orderService;
    }

    public int createOrder(Order order){
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(time));

        System.out.println("静态代理类分配到【DB_"+dbRouter +"]数据源处理数据");

        DynamicDataSourceEntity.set(dbRouter);

        this.orderService.createOrder(order);

        DynamicDataSourceEntity.restore();

        return 0;
    }
}
