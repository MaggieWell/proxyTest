package com.maggie.proxyPattern.dbRoute.dynamicproxy.cglib;

import com.maggie.proxyPattern.dbRoute.dynamicproxy.jdkproxy.Girl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: CGLib 测试类
 * author:MaggieHao
 * Date:2019-05-26
 * Time:14:04
 */
public class CGlibTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CGlibTest.class);

    public static void main(String[] args) {
        try {

            //JDK是采用读取接口的信息
            //CGlib覆盖父类的方法
            //目的都是生成一个新的类，去实现增强代码逻辑的功能

            //JDK Proxy对于用户而言，必须要有一个接口实现，目标类相对来说复杂

            //CGlib 生成代理逻辑更复杂，但是滴啊用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用

            //JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用

            //CGlib，有个坑，不能代理final的方法

//            Customer obj = (Customer)new CGlibMatcher().getInstance(Customer.class);
//            obj.findLove();

            Girl obj1 = (Girl)new CGlibMatcher().getInstance(Girl.class);
            obj1.findLove();
        } catch (Exception e) {
            LOGGER.error("",e);
        }


    }
}
