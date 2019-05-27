package com.maggie.proxyPattern.staticProxy;


import com.maggie.proxyPattern.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:32
 */
public class Son implements Person {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Son.class);

    @Override
    public void findLove() {
        LOGGER.info("儿子要求 肤白貌美，大长腿");
    }
}
