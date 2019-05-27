package com.maggie.proxyPattern.dbRoute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:43
 */
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;

    public OrderServiceImpl() {
        orderDao = new OrderDao();
    }

    /**
     * 如果使用Spring，应该是自动注入的
     * 我们为了使用方便，在构造方法中将OrderDao直接初始化了
     *
     * @param order
     * @return
     */
    public int insert(Order order) {

        LOGGER.info("OrderDao 创建Order成功！");
        return 1;
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.insert(order);
    }
}
