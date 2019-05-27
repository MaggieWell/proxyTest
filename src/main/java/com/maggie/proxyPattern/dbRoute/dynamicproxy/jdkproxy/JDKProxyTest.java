package com.maggie.proxyPattern.dbRoute.dynamicproxy.jdkproxy;


import com.maggie.proxyPattern.Person;

/**
 * Description:媒婆
 * author:MaggieHao
 * Date:2019-05-19
 * Time:20:25
 */
public class JDKProxyTest{
    public static void main(String[] args) {

        Person obj=(Person)new JDBCMatchmaker().getInstance(new Girl());
        obj.findLove();
    }


}
