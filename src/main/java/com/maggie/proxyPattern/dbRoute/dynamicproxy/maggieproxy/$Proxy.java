package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;

import com.maggie.proxyPattern.Person;

import java.lang.reflect.*;

public class $Proxy implements com.maggie.proxyPattern.Person {

    MaggieInvocationHandler h;

    public $Proxy(MaggieInvocationHandler h) {
        this.h = h;
    }

    public void findLove() {
        try {
            Method m = com.maggie.proxyPattern.Person.class.getMethod("findLove", new Class[]{});
            this.h.invoke(this, m, new Object[]{});
        } catch (Error _ex) {
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
