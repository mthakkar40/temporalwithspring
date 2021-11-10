package com.example.domain;

import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;

@Component
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i)    ❷
            throws SQLException {
        Account a = new Account();                         ❸
        a.setId(resultSet.getInt("id"));                   ❸
        a.setName(resultSet.getString("name"));            ❸
        a.setAmount(resultSet.getBigDecimal("amount"));    ❸
        return a;                                          ❹
    }
}
