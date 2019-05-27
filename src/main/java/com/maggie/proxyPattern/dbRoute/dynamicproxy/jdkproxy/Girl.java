package com.maggie.proxyPattern.dbRoute.dynamicproxy.jdkproxy;

import com.maggie.proxyPattern.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-19
 * Time:20:25
 */
public class Girl implements Person {
     private static final Logger LOGGER = LoggerFactory.getLogger(Girl.class);
    @Override
    public void findLove() {

        LOGGER.info("高富帅");
        LOGGER.info("身高180");
        LOGGER.info("6块腹肌");
    }
}
