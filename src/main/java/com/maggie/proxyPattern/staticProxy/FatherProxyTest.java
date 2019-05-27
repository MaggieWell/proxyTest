package com.maggie.proxyPattern.staticProxy;

/**
 * Description:相亲
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:32
 */
public class FatherProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }

    //大家每天都在用的一种静态代理
    //三层架构
    //对数据进行分库分表
    //ThreadLocal实现数据源的动态切换
}
