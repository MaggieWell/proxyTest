package com.maggie.proxyPattern.staticProxy;


import com.maggie.proxyPattern.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 父亲代理儿子物色对象
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:32
 */
public class Father {
    private static final Logger LOGGER = LoggerFactory.getLogger(Father.class);
    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    public void findLove(){
        LOGGER.info("父亲物色对象");
        this.person.findLove();
        LOGGER.info("双方父母同意，确定关系");
    }
}
