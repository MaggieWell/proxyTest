package com.maggie.proxyPattern.dbRoute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:43
 */
public class OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);

    public int insert(Order order) {

        LOGGER.info("OrderDao 创建Order成功！");
        return 1;
    }
}
