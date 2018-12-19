package com.nf.lc.aop.jdbc_aop;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AnimalDAO {

    public void save(Animal animal) throws SQLException {
        //添加动物操作
        PreparedStatement statement = ConnM.getConnection().prepareStatement("insert into animal values(?,?,?)");
        statement.setObject(1,animal.getName());
        statement.setObject(2,animal.getAge());
        statement.setObject(3,animal.getDescribe());
        statement.executeUpdate();

    }

}
