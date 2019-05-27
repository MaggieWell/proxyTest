package com.maggie.proxyPattern.dbRoute.db;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:48
 */
public class DynamicDataSourceEntity {

    public static final String DEFAULT_SOURCE=null;

    private final static ThreadLocal<String> local = new ThreadLocal<>();
    private DynamicDataSourceEntity(){}


    public static String get(){
        return local.get();
    }

    public static void set(String source){
        local.set(source);
    }

    public static void restore(){
        local.set(DEFAULT_SOURCE);
    }


    //db_2018
    //db_2019
    public static void set(int year){
        local.set("db_"+year);
    }
}
