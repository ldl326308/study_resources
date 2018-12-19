package com.nf.lc.aop.jdbc_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Aspect
public class JDBCAdvice {

    @Around("execution(* *.*(..))")
    public Object wrapDAO(ProceedingJoinPoint joinPoint) throws Throwable {
        Connection connection = null;


            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/animaldb","root","root");
                connection.setAutoCommit(false);

                ConnM.threadLocal = new ThreadLocal<>();
                ConnM.threadLocal.set(connection);

                Object o = joinPoint.proceed();

                connection.commit();

                return o;


            }catch (Throwable throwable) {
                if(connection != null){
                    try {
                        connection.rollback(); //出现异常，回滚操作
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                throwable.printStackTrace();
                throw throwable;
            }finally {
                if(connection != null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

}
