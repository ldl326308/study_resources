package com.nf.lc.aop.jdbc_aop;

import java.sql.Connection;

public class ConnM {

    public static ThreadLocal<Connection> threadLocal = null;

    public static Connection getConnection(){
        return threadLocal.get();
    }
}
