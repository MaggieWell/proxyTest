package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;


import com.maggie.proxyPattern.Person;
import com.maggie.proxyPattern.dbRoute.dynamicproxy.jdkproxy.Girl;

/**
 * Description:媒婆类测试
 * author:MaggieHao
 * Date:2019-05-19
 * Time:20:25
 */
public class MaggieProxyTest {
    public static void main(String[] args) {

        Person obj=(Person)new MaggieMatchmaker().getInstance(new Girl());
        obj.findLove();
    }


}
